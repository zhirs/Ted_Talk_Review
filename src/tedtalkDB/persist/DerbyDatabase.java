package tedtalkDB.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Review;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Student;


public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	// TODO: Here is where you name and specify the location of your Derby SQL database
	// TODO: Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
	// TODO: DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Ted-Talk-Review-Lib/library.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	//  creates the Authors and Books tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;				
				PreparedStatement stmt4 = null;
				try {
					stmt1 = conn.prepareStatement(
						"create table admins(" +
						"	prof_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	username varchar(40)," +
						"	password varchar(40), " +
						"	email varchar(100), " +
						")"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"create table profs(" +
							"	prof_id integer primary key " +
							"		generated always as identity (start with 10000, increment by 1), " +									
							"	username varchar(40)," +
							"	password varchar(40), " +
							"	email varchar(100), " +
							")"
						);	
						stmt2.executeUpdate();
					System.out.println("Profs table created");
					
					stmt3 = conn.prepareStatement(
							"create table students(" +
							"	prof_id integer primary key " +
							"		generated always as identity (start with 20000, increment by 1), " +									
							"	username varchar(40)," +
							"	password varchar(40), " +
							"	email varchar(100), " +
							"	section varchar(1000), " +
							")"
						);	
					stmt3.executeUpdate();
					
					stmt4 = conn.prepareStatement(
							"create table reviews(" +
							"	rev_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
//							"	author_id integer constraint author_id references authors, " +  	// this is now in the BookAuthors table
							"	url varchar(100), " +
							"	name varchar(100)," +
							"   rate int, " +
							"	pres varchar(100)," + 
							"	desc varchar(1000)," +
							"   prof_ID int" +
							"	tag varchar(100)," +
							"   status int" + //MISSING COMMA. 
							//"   subDate date" +
							")"
					);
					stmt4.executeUpdate();
					
					System.out.println("Reviews table created");					
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<NetworkAdmin> adminList;
				List<Professor> profList;
				List<Student> studentList;
				List<Review> reviewList;
				
				try {
					adminList = InitialData.getAdmins();
					profList = InitialData.getProfs();
					studentList = InitialData.getStudents();
					reviewList = InitialData.getReviews();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAdmin     = null;
				PreparedStatement insertProf = null;
				PreparedStatement insertStudent = null;
				PreparedStatement insertReview       = null;

				try {
					// must completely populate Authors table before populating BookAuthors table because of primary keys
					insertAdmin = conn.prepareStatement("insert into admins (username, password, email) values (?, ?, ?)");
					for (NetworkAdmin admin: adminList) {
//						insertAdmin.setInt(1, account.getProfId());	// auto-generated primary key, don't insert this
						insertAdmin.setString(1, admin.getUserName());
						insertAdmin.setString(2, admin.getPassword());
						insertAdmin.setString(3, admin.getEmail());
						insertAdmin.addBatch();
					}
					insertAdmin.executeBatch();
					
					System.out.println("Admin table populated");
					
					insertProf = conn.prepareStatement("insert into professors (username, password, email) values (?, ?, ?)");
					for (Professor professor: profList) {
//						insertAdmin.setInt(1, account.getProfId());	// auto-generated primary key, don't insert this
						insertProf.setString(1, professor.getUserName());
						insertProf.setString(2, professor.getPassword());
						insertProf.setString(3,  professor.getEmail());
						insertProf.addBatch();
					}
					insertProf.executeBatch();
					
					System.out.println("Prof table populated");
					
					insertStudent = conn.prepareStatement("insert into students (username, password, email, section) values (?, ?, ?, ?)");
					for (Student student: studentList) {
//						insertAdmin.setInt(1, account.getProfId());	// auto-generated primary key, don't insert this
						insertStudent.setString(1, student.getUserName());
						insertStudent.setString(2, student.getPassword());
						insertStudent.setString(3, student.getEmail());
						insertStudent.setString(4, student.getSection());
						insertStudent.addBatch();
					}
					insertStudent.executeBatch();
					
					System.out.println("Student table populated");
					
					
					// must completely populate Books table before populating BookAuthors table because of primary keys
					insertReview = conn.prepareStatement("insert into reviews (url, name, rate, pres, desc, prof_ID, tag, status) values (?, ?, ?, ?, ?, ?, ?, ?)");
					for (Review review : reviewList) {
//						insertBook.setInt(1, book.getBookId());	
						insertReview.setString(1, review.getURL());
						insertReview.setString(2, review.getName());
						insertReview.setInt(3, review.getRate());
						insertReview.setString(4, review.getPres());
						insertReview.setString(5, review.getDesc());
						insertReview.setInt(6, review.getProfID());
						insertReview.setString(7, review.getTag());
						insertReview.setInt(8, review.getStatus());
						//insertReview.setDate(6, review.getDate());
						
						insertReview.addBatch();
					}
					insertReview.executeBatch();
					
					System.out.println("Reviews table populated");					
					
					// must wait until all Books and all Authors are inserted into tables before creating BookAuthor table
					// since this table consists entirely of foreign keys, with constraints applied				
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertReview);
					DBUtil.closeQuietly(insertProf);
					DBUtil.closeQuietly(insertStudent);
					DBUtil.closeQuietly(insertAdmin);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	

	public boolean checkCredentials(String user, String pass) {
		// TODO Auto-generated method stub
		return false;
	}


	public Account setLogin(String user) {
		// TODO Auto-generated method stub
		return null;
	}



	public ArrayList<Account> addUser(String user, String pass, String email, String section, int role) {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<Review> getProfIDReviewList(int profID) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getReviewTotal(int profID) {
		// TODO Auto-generated method stub
		return 0;
	}


	public ArrayList<Review> findReview(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}


}
