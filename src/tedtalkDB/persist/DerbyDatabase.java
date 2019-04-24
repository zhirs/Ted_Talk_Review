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
import tedtalk.model.Pair;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
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
		Connection conn = DriverManager.getConnection("jdbc:derby:library.db;create=true");		
		
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
						"	email varchar(100) " +
						")"
					);	
					stmt1.executeUpdate();
					System.out.print("Admin table created");
					
					stmt2 = conn.prepareStatement(
							"create table professors(" +
							"	prof_id integer primary key " +
							"		generated always as identity (start with 10000, increment by 1), " +									
							"	username varchar(40)," +
							"	password varchar(40), " +
							"	email varchar(100) " +
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
							"	section varchar(1000) " +
							")"
						);	
					stmt3.executeUpdate();
					System.out.println("Students table created");
					
					stmt4 = conn.prepareStatement(
							"create table reviews(" +
							"	rev_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
//							"	author_id integer constraint author_id references authors, " +  	// this is now in the BookAuthors table
							"	url varchar(100), " +
							"	name varchar(100)," +
							"   rate integer, " +
							"	pres varchar(100), " + 
							"	description varchar(4000), " +
							"   prof_ID integer, " +
							"	tag varchar(100), " +
							"   status integer" + //MISSING COMMA. 
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
					insertReview = conn.prepareStatement("insert into reviews (url, name, rate, pres, description, prof_ID, tag, status) values (?, ?, ?, ?, ?, ?, ?, ?)");
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
	
	@Override
	public boolean checkCredentials(String user, String pass) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				ResultSet resultSet3 = null;
				try {
					stmt1 = conn.prepareStatement(
						" select * "+
						" from students " +
						" where username = ? and password = ?"
					);
					stmt1.setString(1, user);
					stmt1.setString(2, pass);
					resultSet1 = stmt1.executeQuery();
					List<Account> students = new ArrayList<Account>();
					while(resultSet1.next()) {
						Account account = new Account();
						loadAccount(account, resultSet1, 1);
						students.add(account);
					}
					if(students.size() == 1) {
						System.out.println("Found Account");
						return true;
					}
	
					stmt2 = conn.prepareStatement(
							" select * "+
							" from professors " +
							" where username = ? and password = ?"
						);
					stmt2.setString(1, user);
					stmt2.setString(2, pass);
					resultSet2 = stmt2.executeQuery();
					List<Account> professors = new ArrayList<Account>();
					while(resultSet2.next()) {
						Account account = new Account();
						loadAccount(account, resultSet2, 1);
						professors.add(account);
					}
					if(professors.size() == 1) {
						System.out.println("Found Account");
						return true;
					}
			
				
					stmt3 = conn.prepareStatement(
						" select * "+
						" from admins " +
						" where username = ? and password = ?"
					);
					stmt3.setString(1, user);
					stmt3.setString(2, pass);
					resultSet3 = stmt3.executeQuery();
					List<Account> admins = new ArrayList<Account>();
					while(resultSet3.next()) {
						Account account = new Account();
						loadAccount(account, resultSet3, 1);
						admins.add(account);
					}
					if(admins.size() == 1) {
						System.out.println("Found Account");
						return true;
					}
					return false;
			}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}
			}
		});
	}
	

	@Override
	public Account setLogin(String user) {
		return executeTransaction(new Transaction<Account>() {
			@Override
			public Account execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				ResultSet resultSet3 = null;
				try {
					stmt1 = conn.prepareStatement(
						" select * "+
						" from students " +
						" where username = ? "
					);
					stmt1.setString(1, user);
					resultSet1 = stmt1.executeQuery();
					List<Account> students = new ArrayList<Account>();
					while(resultSet1.next()) {
						Account account = new Account();
						loadAccount(account, resultSet1, 1);
						students.add(account);
					}
					if(students.size() == 1) {
						System.out.println("Found Account");
						return students.get(0);
					}
	
					stmt2 = conn.prepareStatement(
							" select * "+
							" from professors " +
							" where username = ? "
						);
					stmt2.setString(1, user);
					resultSet2 = stmt2.executeQuery();
					List<Account> professors = new ArrayList<Account>();
					while(resultSet2.next()) {
						Account account = new Account();
						loadAccount(account, resultSet2, 1);
						professors.add(account);
					}
					if(professors.size() == 1) {
						System.out.println("Found Account");
						professors.get(0);
					}
			
				
					stmt3 = conn.prepareStatement(
						" select * "+
						" from admins " +
						" where username = ?"
					);
					stmt3.setString(1, user);
					resultSet3 = stmt3.executeQuery();
					List<Account> admins = new ArrayList<Account>();
					while(resultSet3.next()) {
						Account account = new Account();
						loadAccount(account, resultSet3, 1);
						admins.add(account);
					}
					if(admins.size() == 1) {
						System.out.println("Found Account");
						return admins.get(0);
					}
					return null;
			}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}
			}
		});
	}


	public ArrayList<Review> getProfIDReviewList(int profID) {
		return executeTransaction(new Transaction<ArrayList<Review>>() {
			@Override
			public ArrayList<Review> execute(Connection conn) throws SQLException {
				ArrayList<Review> reviews = new ArrayList<Review>();
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select * "
							+ "from reviews "
							+ "where prof_id = ? ");
					stmt1.setInt(1, profID);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						Review review = new Review();
						loadReview(review, resultSet1, 1);
						reviews.add(review);
					}
					if(reviews.size() >= 1) {
						System.out.println("Found reviews");
						return reviews;
					}
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
				return reviews;
			}
		}
		);
	}

	public int getReviewTotal(int profID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Review> findReview(String keyword) {
		return executeTransaction(new Transaction<ArrayList<Review>>() {
			@Override
			public ArrayList<Review> execute(Connection conn) throws SQLException {
				ArrayList<Review> reviews = new ArrayList<Review>();
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select * "
							+ "from reviews "
							+ "where name = ? ");
					stmt1.setString(1, keyword);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						Review review = new Review();
						loadReview(review, resultSet1, 1);
						reviews.add(review);
					}
					if(reviews.size() == 1) {
						System.out.println("Found review");
						return reviews;
					}
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
				return reviews;
			}
		}
		);
	}

	@Override
	public ArrayList<Professor> addProfessor(String user, String pass, String email) {
		return executeTransaction(new Transaction<ArrayList<Professor>>() {
			@Override
			public ArrayList<Professor> execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				ArrayList<Professor> professor= new ArrayList<Professor>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into professors (username, password, email) "+
						"values(?, ?, ?)"
					);
					stmt1.setString(1, user);
					stmt1.setString(2, pass);
					stmt1.setString(3, email);
					stmt1.executeUpdate();
				
					stmt2 = conn.prepareStatement(
							"select * "
							+ "from professors "
							+ "where username = ?"
							);
					stmt2.setString(1, user);
					resultSet1 = stmt2.executeQuery();
					
					ArrayList<Professor> profs = new ArrayList<Professor>();
					while(resultSet1.next()) {
						Professor account = loadProfessor(resultSet1, 1);
						profs.add(account);
					}
					System.out.println("Account made");
					return profs;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	@Override
	public ArrayList<Student> addStudent(String user, String pass, String email, String section) {
		return executeTransaction(new Transaction<ArrayList<Student>>() {
			@Override
			public ArrayList<Student> execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				ArrayList<Student> student= new ArrayList<Student>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into students (username, password, email, section) "+
						"values(?, ?, ?, ?)"
					);
					stmt1.setString(1, user);
					stmt1.setString(2, pass);
					stmt1.setString(3, email);
					stmt1.setString(4, section);
					stmt1.executeUpdate();
				
					stmt2 = conn.prepareStatement(
							"select * "
							+ "from students "
							+ "where username = ?"
							);
					stmt2.setString(1, user);
					resultSet1 = stmt2.executeQuery();
				
					while(resultSet1.next()) {
						Student account = loadStudent(resultSet1, 1);
						student.add(account);
					}
					System.out.println("Account made");
					return student;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	@Override
	public ArrayList<NetworkAdmin> addAdmin(String user, String pass, String email) {
		return executeTransaction(new Transaction<ArrayList<NetworkAdmin>>() {
			@Override
			public ArrayList<NetworkAdmin> execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				ArrayList<NetworkAdmin> admins= new ArrayList<NetworkAdmin>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into admins (username, password, email) "+
						"values(?, ?, ?)"
					);
					stmt1.setString(1, user);
					stmt1.setString(2, pass);
					stmt1.setString(3, email);
					stmt1.executeUpdate();
				
					stmt2 = conn.prepareStatement(
							"select * "
							+ "from admins "
							+ "where username = ?"
							);
					stmt2.setString(1, user);
					resultSet1 = stmt2.executeQuery();
					
					while(resultSet1.next()) {
						NetworkAdmin account = loadAdmin(resultSet1, 1);
						admins.add(account);
					}
					if(admins.isEmpty()) {
						System.out.println("Account not made");
					}
					return admins;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	protected Professor loadProfessor(ResultSet resultSet, int index) throws SQLException {
		// TODO Auto-generated method stub
		int profID = resultSet.getInt(index++);
		String username = resultSet.getString(index++);
		String password = resultSet.getString(index++);
		String email = resultSet.getString(index++);
		Professor profX = new Professor(username, password, email, profID);
		return profX;
	}
	protected NetworkAdmin loadAdmin(ResultSet resultSet, int index) throws SQLException {
		// TODO Auto-generated method stub
		int profID = resultSet.getInt(index++);
		String username = resultSet.getString(index++);
		String password = resultSet.getString(index++);
		String email = resultSet.getString(index++);
		NetworkAdmin adminX = new NetworkAdmin(username, password, email, profID);
		return adminX;
	}

	protected Student loadStudent(ResultSet resultSet, int index) throws SQLException {
		// TODO Auto-generated method stub
		int profID = resultSet.getInt(index++);
		String username = resultSet.getString(index++);
		String password = resultSet.getString(index++);
		String email = resultSet.getString(index++);
		String section = resultSet.getString(index++);
		Student studentX = new Student(username, password, email, section, profID);
		return studentX;
	}
	protected void loadReview(Review review, ResultSet resultSet1, int i) throws SQLException {
		int revID = resultSet1.getInt(i++);
		String URL = resultSet1.getString(i++);
		review.setURL(URL);
			
		review.setName(resultSet1.getString(i++));
		review.setRate(resultSet1.getInt(i++));
		review.setPres(resultSet1.getString(i++));
		review.setDesc(resultSet1.getString(i++));
		review.setProfID(resultSet1.getInt(i++));
		review.setTag(resultSet1.getString(i++));
		review.setStatus(resultSet1.getInt(i++));
	}
	private void loadAccount(Account account, ResultSet resultSet, int index) throws SQLException{
		account.setprofID(resultSet.getInt(index++));
		account.setUsername(resultSet.getString(index++));
		account.setPassword(resultSet.getString(index++));
		account.setEmail(resultSet.getString(index++));
	}

	@Override
	public ArrayList<Review> addReview(String URL, String name, int rate, String pres, String desc, int profID,
			String tag, int status) {
		return executeTransaction(new Transaction<ArrayList<Review>>() {
			@Override
			public ArrayList<Review> execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				ArrayList<Review> review= new ArrayList<Review>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into reviews (url, name, rate, pres, description, prof_id, tag, status) "+
						"values(?, ?, ?, ?, ?, ?, ?, ?)"
					);
					stmt1.setString(1, URL);
					stmt1.setString(2, name);
					stmt1.setInt(3, rate);
					stmt1.setString(4, pres);
					stmt1.setString(5, desc);
					stmt1.setInt(6, profID);
					stmt1.setString(7, tag);
					stmt1.setInt(8, status);
					stmt1.executeUpdate();
				
					stmt2 = conn.prepareStatement(
							"select * "
							+ "from reviews "
							+ "where URL = ?"
							);
					stmt2.setString(1, URL);
					resultSet1 = stmt2.executeQuery();
					
					while(resultSet1.next()) {
						Review rev = new Review();
						loadReview(rev, resultSet1, 1);
						review.add(rev);
					}
					System.out.println("Account made");
					return review;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
		
}
