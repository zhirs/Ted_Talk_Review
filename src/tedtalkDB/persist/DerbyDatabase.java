package tedtalkDB.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import src.org.mindrot.jbcrypt.BCrypt;

import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Pair;
import tedtalkDB.model.Review;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Student;
import tedtalkDB.model.keywords;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); 
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	/*
	 * REVIEW:
	 * url, name, rate, pres, description, prof_id, tag, status, pubDate
	 */
	
	/*
	 * ACCOUNT:
	 * username, password, email, role
	 */
	
	/*
	 * ADMIN:
	 * prof_id, modstat
	 */
	
	/*
	 * PROFESSOR:
	 * prof_id, mod
	 */
	
	/*
	 * STUDENT:
	 * prof_id, section, major
	 */
	
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

	// Here is where you name and specify the location of your Derby SQL database
	// Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
	// DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
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
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				PreparedStatement stmt7 = null;
				try {
					stmt1 = conn.prepareStatement(
						"create table admins(" +
						"	admin_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	prof_id integer," + 
						"	modstat integer" +
						")"
					);	
					
					stmt1.executeUpdate();
					System.out.print("Admin table created");
					
					stmt2 = conn.prepareStatement(
							"create table professors(" +
							"	teacher_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	prof_id integer, " +
							"  	mod integer " +
							")"
						);	
						stmt2.executeUpdate();
					System.out.println("Profs table created");
					
					stmt3 = conn.prepareStatement(
							"create table students(" +
							"	student_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	prof_id integer," +
							"	section varchar(1000), "+ 
							"   major varchar(1000) " +
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
							"   status integer, " + //MISSING COMMA. 
							"   pubDate date " +
							")"
					);
					stmt4.executeUpdate();
					System.out.println("Reviews table created");
					
					stmt5 = conn.prepareStatement(
							"create table accounts(" + 
							"	prof_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " + 
							"	username varchar(100), " +
							" 	password varchar(100), " +
							"	email varchar(100), " + 
							" 	role integer" +
							")"
							);
					stmt5.executeUpdate();
					System.out.println("Accounts table created");		
					
					stmt6 = conn.prepareStatement(
							"create table keywords( " +
							"	keywords_id integer primary key " + 
							"		generated always as identity (start with 1, increment by 1), " +
							"	words varchar(200), " +
							" 	rev_id integer " + 
							")"
							);
					stmt6.executeUpdate();
					System.out.println("Keywords table created");
					
					stmt7 = conn.prepareStatement(
							"create table newStudents(" + 
							"	temp_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	username varchar(100), " +
							"	password varchar(64), "	+
							"	email varchar(200), " + 
							"	section varchar(200), " +
							" 	major varchar(200) " +
							")");
					stmt7.executeUpdate();
					System.out.println("newStudents table created");
				
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
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
				List<Account> accountList;
				List<keywords> keyList;
				List<Student> newStudentList;
				try {
					adminList = InitialData.getAdmins();
					profList = InitialData.getProfs();
					studentList = InitialData.getStudents();
					reviewList = InitialData.getReviews();
					accountList = InitialData.getAccounts();
					keyList = InitialData.getKeywords();
					newStudentList = InitialData.getNewStudents();
				} catch (IOException | ParseException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAdmin     = null;
				PreparedStatement insertProf = null;
				PreparedStatement insertStudent = null;
				PreparedStatement insertReview       = null;
				PreparedStatement insertAccount = null;
				PreparedStatement insertKeys = null;
				PreparedStatement insertNewStudents = null;
				try {
					insertAccount = conn.prepareStatement("insert into accounts(username, password, email, role) values (?, ?, ?, ?)");
					for (Account account: accountList) {
//						insertAdmin.setInt(1, account.getProfId());	// auto-generated primary key, don't insert this
						insertAccount.setString(1, account.getUserName());
						String accountPW = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
						insertAccount.setString(2, accountPW);
						insertAccount.setString(3, account.getEmail());
						insertAccount.setInt(4, account.getRole());
						insertAccount.addBatch();
					}
					insertAccount.executeBatch();
					System.out.println("Account table populated");
					// must completely populate Authors table before populating BookAuthors table because of primary keys
					
					insertAdmin = conn.prepareStatement("insert into admins (prof_id, modstat) values (?, ?)");
					for (NetworkAdmin admin: adminList) {
//						insertAdmin.setInt(1, account.getProfId());	// auto-generated primary key, don't insert this
						insertAdmin.setInt(1, admin.getprofID());
						insertAdmin.setInt(2, admin.getModStat());
						insertAdmin.addBatch();
					}
					insertAdmin.executeBatch();
					System.out.println("Admin table populated");
					
					
					insertProf = conn.prepareStatement("insert into professors (prof_id, mod) values (?, ?)");
					for (Professor professor: profList) {
//						insertAdmin.setInt(1, account.getProfId());	// auto-generated primary key, don't insert this
						insertProf.setInt(1, professor.getprofID());
						insertProf.setInt(2, professor.getMod());
						insertProf.addBatch();
					}
					insertProf.executeBatch();
					System.out.println("Prof table populated");
					
				
					insertStudent = conn.prepareStatement("insert into students (prof_id, section, major) values (?, ?, ?)");
					for (Student student: studentList) {
//						insertAdmin.setInt(1, account.getProfId());	// auto-generated primary key, don't insert this
						insertStudent.setInt(1,  student.getprofID());
						insertStudent.setString(2, student.getSection());
						insertStudent.setString(3, student.getMajor());
						insertStudent.addBatch();
					}
					insertStudent.executeBatch();
					System.out.println("Student table populated");
					
					
					// must completely populate Books table before populating BookAuthors table because of primary keys
					insertReview = conn.prepareStatement("insert into reviews (url, name, rate, pres, description, prof_ID, tag, status, pubDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
						insertReview.setDate(9, review.getDate());
						
						insertReview.addBatch();
					}
					insertReview.executeBatch();					
					System.out.println("Reviews table populated");					
					
					insertKeys = conn.prepareStatement("insert into keywords (words, rev_id) values (?, ?)");
					for(keywords key : keyList) {
						insertKeys.setString(1, key.getKeyWord());
						insertKeys.setInt(2, key.getReviewID());
						
						insertKeys.addBatch();
					}
					insertKeys.executeBatch();
					System.out.println("Keywords table populated");
					
					insertNewStudents = conn.prepareStatement("insert into newStudents (username, password, email, section, major) values (?, ?, ?, ?, ?)");
					for(Student newStudent : newStudentList) {
						insertNewStudents.setString(1, newStudent.getUserName());
						insertNewStudents.setString(2, newStudent.getPassword());
						insertNewStudents.setString(3, newStudent.getEmail());
						insertNewStudents.setString(4, newStudent.getSection());
						insertNewStudents.setString(5, newStudent.getMajor());
						insertNewStudents.addBatch();
					}
					insertNewStudents.executeBatch();
					System.out.println("NewStudents table populated");
					System.out.println("All tables populated");
					// must wait until all Books and all Authors are inserted into tables before creating BookAuthor table
					// since this table consists entirely of foreign keys, with constraints applied				
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertReview);
					DBUtil.closeQuietly(insertProf);
					DBUtil.closeQuietly(insertStudent);
					DBUtil.closeQuietly(insertAdmin);
					DBUtil.closeQuietly(insertAccount);
					DBUtil.closeQuietly(insertKeys);
					DBUtil.closeQuietly(insertNewStudents);
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
				// Auto-generated method stub
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						" select password "+
						" from accounts " +
						" where username = ?"
					);
					stmt1.setString(1, user);
					resultSet1 = stmt1.executeQuery();
					String storedStudentPass =null;
					while(resultSet1.next()) { 
						storedStudentPass = resultSet1.getString(1);
					}
					if(storedStudentPass != null) {
						if(BCrypt.checkpw(pass, storedStudentPass)){
							//System.out.println("Found Account");
							return true;
						}
					}
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
				return false;
			}
		});
	}
	

	@Override
	public Account setLogin(String user) {
		return executeTransaction(new Transaction<Account>() {
			@Override
			public Account execute(Connection conn) throws SQLException {
				// Auto-generated method stub
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						" select * "+
						" from accounts " +
						" where username = ? "
					);
					stmt1.setString(1, user);
					resultSet1 = stmt1.executeQuery();
					List<Account> accounts = new ArrayList<Account>();
					while(resultSet1.next()) {
						Account account = new Account();
						loadAccount(account, resultSet1, 1);
						accounts.add(account);
					}
					if(accounts.size() == 1) {
						//System.out.println("Found Account");
						return accounts.get(0);
					}
					return null;
			}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}


	public ArrayList<Review> getProfIDReviewList(int profID, int status) {
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
							+ "where prof_id = ? and status = ?");
					stmt1.setInt(1, profID);
					stmt1.setInt(2, status);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						Review review = new Review();
						loadReview(review, resultSet1, 1);
						reviews.add(review);
					}
					if(reviews.size() >= 1) {
						//System.out.println("Found reviews");
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

	public Integer getReviewTotal(int profID) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
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
						//System.out.println("Found reviews");
						return reviews.size();
					}
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
				return -1;
			}
		}
		);
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
	public Integer getModStat(int profID) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select modstat "
							+ "from admins "
							+ "where prof_id = ? ");
					stmt1.setInt(1, profID);
					resultSet1 = stmt1.executeQuery();
					int i = 1;
					int test;
					while(resultSet1.next()) {
						test = resultSet1.getInt(i++);
						//System.out.println(test);
						return test;
					}
					return -1;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}
	@Override
	public Integer updateModStat(int profID, int modStat) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				try {
					stmt1 = conn.prepareStatement(
							"update admins "
							+ "set modstat = ? "
							+ "where prof_id = ? ");
					stmt1.setInt(1, modStat);
					stmt1.setInt(2, profID);
					stmt1.execute();
					return 1;
				}
				catch(SQLException e){
					return -1;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}
	@Override
	public Integer updateMod(int profID, int mod) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				try {
					stmt1 = conn.prepareStatement(
							"update professors "
							+ "set mod = ? "
							+ "where prof_id = ? ");
					stmt1.setInt(1, mod);
					stmt1.setInt(2, profID);
					stmt1.execute();
					return 1;
				}
				catch(SQLException e){
					return -1;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}
	@Override
	public Integer getMod(int profID) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select mod "
							+ "from professors "
							+ "where prof_id = ? ");
					stmt1.setInt(1, profID);
					resultSet1 = stmt1.executeQuery();
					int i = 1;
					int test;
					while(resultSet1.next()) {
						test = resultSet1.getInt(i++);
						return test;
					}
					return -1;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}
	@Override
	public ArrayList<Professor> addProfessor(String user, String pass, String email, int mod) {
		return executeTransaction(new Transaction<ArrayList<Professor>>() {
			@Override
			public ArrayList<Professor> execute(Connection conn) throws SQLException {
				// Auto-generated method stub
				ArrayList<Professor> professors= new ArrayList<Professor>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into accounts (username, password, email, role) "+
						"values(?, ?, ?, ?)"
					);
					stmt1.setString(1, user);
					String hashPass = BCrypt.hashpw(pass, BCrypt.gensalt());
					stmt1.setString(2, hashPass);
					stmt1.setString(3, email);
					stmt1.setInt(4, 1);
					stmt1.executeUpdate();
				
					stmt2 = conn.prepareStatement(
							"select prof_id "
							+ "from accounts "
							+ "where username = ? and email = ? and password = ?"
							);
					stmt2.setString(1, user);
					stmt2.setString(2, email);
					stmt2.setString(3, hashPass);
					resultSet1 = stmt2.executeQuery();
					int i = 1;
					int newID = -1;
					while(resultSet1.next()) {
						newID = resultSet1.getInt(i++);
					}
					if(newID == -1) {
						System.out.println("Account not made");
						return null;
					}
	
					stmt3 = conn.prepareStatement(
							"insert into professors (prof_id, mod) "+
							"values(?, ?)"
					);
					stmt3.setInt(1, newID);
					stmt3.setInt(2, mod);
					stmt3.executeUpdate();
					
					stmt4 = conn.prepareStatement(
							"select professors.prof_id, username, password, email, professors.mod "
							+ "from professors, accounts "
							+ "where professors.prof_id = accounts.prof_id "
							+ "and professors.prof_id = ? ");
					
					stmt4.setInt(1, newID);
					resultSet2 = stmt4.executeQuery();
					while(resultSet2.next()) {
						Professor account = loadProfessor(resultSet2, 1);
						professors.add(account);
					}
					return professors;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
				}
			}
		});
	}

	@Override
	public ArrayList<Student> addStudent(String user, String pass, String email, String section, String major) {
		return executeTransaction(new Transaction<ArrayList<Student>>() {
			@Override
			public ArrayList<Student> execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				ArrayList<Student> students= new ArrayList<Student>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into accounts (username, password, email, role) "+
						"values(?, ?, ?, ?)"
					);
					stmt1.setString(1, user);
					String hashPass = BCrypt.hashpw(pass, BCrypt.gensalt());
					stmt1.setString(2, hashPass);
					stmt1.setString(3, email);
					stmt1.setInt(4, 2);
					stmt1.executeUpdate();
				
					stmt2 = conn.prepareStatement(
							"select prof_id "
							+ "from accounts "
							+ "where username = ? and email = ? and password = ?"
							);
					stmt2.setString(1, user);
					stmt2.setString(2, email);
					stmt2.setString(3, hashPass);
					resultSet1 = stmt2.executeQuery();
					int i = 1;
					int newID = -1;
					while(resultSet1.next()) {
						newID = resultSet1.getInt(i++);
					}
					if(newID == -1) {
						System.out.println("Account not made");
						return null;
					}
	
					stmt3 = conn.prepareStatement(
							"insert into students (prof_id, section, major) "+
							"values(?, ?, ?)"
					);
					stmt3.setInt(1, newID);
					stmt3.setString(2, section);
					stmt3.setString(3, major);
					stmt3.executeUpdate();
					
					stmt4 = conn.prepareStatement(
							"select students.prof_id, username, password, email, students.section, students.major "
							+ "from students, accounts "
							+ "where students.prof_id = accounts.prof_id "
							+ "and students.prof_id = ?");
					stmt4.setInt(1, newID);
					resultSet2 = stmt4.executeQuery();
					while(resultSet2.next()) {
						Student account = loadStudent(resultSet2, 1);
						students.add(account);
					}
					return students;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
				}
			}
		});
	}

	@Override
	public ArrayList<NetworkAdmin> addAdmin(String user, String pass, String email, int modstat) {
		return executeTransaction(new Transaction<ArrayList<NetworkAdmin>>() {
			@Override
			public ArrayList<NetworkAdmin> execute(Connection conn) throws SQLException {
				// Auto-generated method stub
				ArrayList<NetworkAdmin> admins= new ArrayList<NetworkAdmin>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into accounts (username, password, email, role) "+
						"values(?, ?, ?, ?)"
					);
					stmt1.setString(1, user);
					String hashPass = BCrypt.hashpw(pass, BCrypt.gensalt());
					stmt1.setString(2, hashPass);
					stmt1.setString(3, email);
					stmt1.setInt(4, 0);
					stmt1.executeUpdate();
				
					stmt2 = conn.prepareStatement(
							"select prof_id "
							+ "from accounts "
							+ "where username = ? and email = ? and password = ?"
							);
					stmt2.setString(1, user);
					stmt2.setString(2, email);
					stmt2.setString(3, hashPass);
					resultSet1 = stmt2.executeQuery();
					int i = 1;
					int newID = -1;
					while(resultSet1.next()) {
						newID = resultSet1.getInt(i++);
					}
					if(newID == -1) {
						System.out.println("Account not made");
						return null;
					}
	
					stmt3 = conn.prepareStatement(
							"insert into admins (prof_id, modstat) "+
							"values(?, ?)"
					);
					stmt3.setInt(1, newID);
					stmt3.setInt(2, modstat);
					stmt3.executeUpdate();
					
					stmt4 = conn.prepareStatement(
							"select admins.prof_id, username, password, email, admins.modstat "
							+ "from admins, accounts "
							+ "where admins.prof_id = accounts.prof_id "
							+ "and admins.prof_id = ?");
					stmt4.setInt(1, newID);
					resultSet2 = stmt4.executeQuery();
					while(resultSet2.next()) {
						NetworkAdmin account = loadAdmin(resultSet2, 1);
						admins.add(account);
					}
					return admins;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
				}
			}
		});
	}

	protected Professor loadProfessor(ResultSet resultSet, int index) throws SQLException {
		// Auto-generated method stub
		int profID = resultSet.getInt(index++);
		String username = resultSet.getString(index++);
		String password = resultSet.getString(index++);
		String email = resultSet.getString(index++);
		int mod = resultSet.getInt(index++);
		Professor profX = new Professor(username, password, email, profID);
		profX.setMod(mod);
		return profX;
	}
	
	protected NetworkAdmin loadAdmin(ResultSet resultSet, int index) throws SQLException {
		// Auto-generated method stub
		int profID = resultSet.getInt(index++);
		System.out.print(profID);
		String username = resultSet.getString(index++);
		System.out.print(username);
		String password = resultSet.getString(index++);
		
		String email = resultSet.getString(index++);
		System.out.print(email);
		int modStat = resultSet.getInt(index++);
		NetworkAdmin adminX = new NetworkAdmin(username, password, email, profID);
		adminX.setModStat(modStat);
		return adminX;
	}

	protected Student loadStudent(ResultSet resultSet, int index) throws SQLException {
		// Auto-generated method stub
		int profID = resultSet.getInt(index++);
		String username = resultSet.getString(index++);
		String password = resultSet.getString(index++);
		String email = resultSet.getString(index++);
		String section = resultSet.getString(index++);
		String major = resultSet.getString(index++);
		Student studentX = new Student(username, password, email, section, profID, major);
		return studentX;
	}
	protected void loadReview(Review review, ResultSet resultSet1, int i) throws SQLException {
		review.setRevID(resultSet1.getInt(i++));
		review.setURL(resultSet1.getString(i++));
		review.setName(resultSet1.getString(i++));
		review.setRate(resultSet1.getInt(i++));
		review.setPres(resultSet1.getString(i++));
		review.setDesc(resultSet1.getString(i++));
		review.setProfID(resultSet1.getInt(i++));
		review.setTag(resultSet1.getString(i++));
		review.setStatus(resultSet1.getInt(i++));
		review.setDate(resultSet1.getDate(i++));
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
				// Auto-generated method stub
				ArrayList<Review> review= new ArrayList<Review>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into reviews (url, name, rate, pres, description, prof_id, tag, status, pubDate) "+
						"values(?, ?, ?, ?, ?, ?, ?, ?, ?) " 
					);
					stmt1.setString(1, URL);
					stmt1.setString(2, name);
					stmt1.setInt(3, rate);
					stmt1.setString(4, pres);
					stmt1.setString(5, desc);
					stmt1.setInt(6, profID);
					stmt1.setString(7, tag);
					stmt1.setInt(8, status);
				
					java.util.Date date = new java.util.Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					stmt1.setDate(9, sqlDate);
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
					System.out.println("Review saved");
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

	@Override
	public ArrayList<Review> getReviewsBetweenDates(int profID, Date date1, Date date2) {
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
							+ "where prof_id = ? and "
							+ "pubDate between ? and ?");
					stmt1.setInt(1, profID);
					stmt1.setDate(2, date1);
					stmt1.setDate(3, date2);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						Review review = new Review();
						loadReview(review, resultSet1, 1);
						reviews.add(review);
					}
					if(reviews.size() >= 1) {
						//System.out.println("Found reviews");
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
	public Integer updateStatus(int revID, int status) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				try {
					stmt1 = conn.prepareStatement(
							"update reviews "
							+ "set status = ? "
							+ "where rev_id = ? ");
					stmt1.setInt(1, status);
					stmt1.setInt(2, revID);
					stmt1.execute();
					return 1;
				}
				catch(SQLException e){
					return -1;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}

	@Override
	public Integer getStatus(int revID) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select status "
							+ "from reviews "
							+ "where rev_id = ? ");
					stmt1.setInt(1, revID);
					resultSet1 = stmt1.executeQuery();
					int i = 1;
					int test;
					while(resultSet1.next()) {
						test = resultSet1.getInt(i++);
						return test;
					}
					return -1;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}

	@Override
	public ArrayList<Student> studentsByMajor(String major) {
		return executeTransaction(new Transaction<ArrayList<Student>>() {
			@Override
			public ArrayList<Student> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				ArrayList<Student> students = new ArrayList<Student>();
				try {
					stmt1 = conn.prepareStatement(
							"select students.prof_id, username, password, email, students.section, students.major "
							+ "from students, accounts "
							+ "where students.prof_id = accounts.prof_id and "
							+ "students.major  = ? ");
					stmt1.setString(1, major);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						Student student = new Student();
						student = loadStudent(resultSet1, 1);
						students.add(student);
					}
					if(students.size() >= 1) {
						System.out.println("Found students");
						return students;
					}
					
				}
				finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(conn);
				}
				return students;
			}
			
		}
		);
		
	}
	public Student studentByProfID(int profID) {
		return executeTransaction(new Transaction<Student>() {
			@Override
			public Student execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				ArrayList<Student> students = new ArrayList<Student>();
				try {
					stmt1 = conn.prepareStatement(
							"select students.prof_id, username, password, email, students.section, students.major "
							+ "from students, accounts "
							+ "where students.prof_id = accounts.prof_id and "
							+ "students.prof_id  = ? ");
					stmt1.setInt(1, profID);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						Student student = new Student();
						student = loadStudent(resultSet1, 1);
						students.add(student);
					}
					if(students.size() == 1) {
						System.out.println("Found student");
						return students.get(0);
					}
					
				}
				finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(conn);
				}
				return null;
			}
			
		}
		);
		
	}
	public Professor professorByProfID(int profID) {
		return executeTransaction(new Transaction<Professor>() {
			@Override
			public Professor execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				ArrayList<Professor> professors= new ArrayList<Professor>();
				try {
					stmt1 = conn.prepareStatement(
							"select *  "
							+ "from professors, accounts "
							+ "where professors.prof_id  = accounts.prof_id and "
							+ "professors.prof_id = ? ");
					stmt1.setInt(1, profID);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						Professor professor= new Professor();
						professor= loadProfessor(resultSet1, 1);
						professors.add(professor);
					}
					if(professors.size() == 1) {
						System.out.println("Found student");
						return professors.get(0);
					}
					
				}
				finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(conn);
				}
				return null;
			}
			
		}
		);
		
	}
	public NetworkAdmin adminByProfID(int profID) {
		return executeTransaction(new Transaction<NetworkAdmin>() {
			@Override
			public NetworkAdmin execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				ArrayList<NetworkAdmin> admins= new ArrayList<NetworkAdmin>();
				try {
					stmt1 = conn.prepareStatement(
							"select admins.prof_id, username, password, email, admins.modstat  "
							+ "from admins, accounts "
							+ "where (accounts.prof_id = admins.prof_id) and "
							+ "admins.prof_id  = ? ");
					stmt1.setInt(1, profID);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						NetworkAdmin admin = new NetworkAdmin();
						admin = loadAdmin(resultSet1, 1);
						admins.add(admin);
					}
					if(admins.size() == 1) {
						System.out.println("Found admin");
						return admins.get(0);
					}
					
				}
				finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(conn);
				}
				return null;
			}
			
		}
		);
		
	}
	@Override
	public Integer updateSection(int profID, String section) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				try {
					stmt1 = conn.prepareStatement(
							"update students "
							+ "set section = ? "
							+ "where prof_id = ? ");
					stmt1.setString(1, section);
					stmt1.setInt(2, profID);
					stmt1.execute();
					return 1;
				}
				catch(SQLException e){
					return -1;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}

	@Override
	public String getSection(int profID) {
		return executeTransaction(new Transaction<String>() {
			@Override
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select section "
							+ "from students  "
							+ "where prof_id = ? ");
					stmt1.setInt(1, profID);
					resultSet1 = stmt1.executeQuery();
					int i = 1;
					String test = null;
					while(resultSet1.next()) {
						test = resultSet1.getString(i++);
					}
					return test;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}
	@Override
	public Integer getProfID(String user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						" select prof_id "+
						" from accounts " +
						" where username = ?"
					);
					stmt1.setString(1, user);
					resultSet1 = stmt1.executeQuery();
					int foundProfID = -1;
					while(resultSet1.next()) { 
						foundProfID = resultSet1.getInt(1);
					}
					if(foundProfID != -1) {
						//System.out.println("Found Account");
						return foundProfID;
					}
					return null;
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
	public Integer getRole(String user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						" select role "+
						" from accounts " +
						" where username = ?"
					);
					stmt1.setString(1, user);
					resultSet1 = stmt1.executeQuery();
					int foundProfID = -1;
					while(resultSet1.next()) { 
						foundProfID = resultSet1.getInt(1);
					}
					if(foundProfID != -1) {
						return foundProfID;
					}
					return null;
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
	public Integer addToAdmin(String user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				// finds role of account
				int profID = getProfID(user);
				
				PreparedStatement stmt1 = null;
				
				try {					
					// removes sub role account
					switch(getRole(user)) {
					case 0:
						removeFromAdmin(user);
						break;
					case 1: 
						removeFromProfessor(user);
						break;
					default:
						removeFromStudent(user);	
					}
					
					// inserts in blank student
					stmt1 = conn.prepareStatement(
							"insert into admins (prof_id, modStat) "+
							"values(?, ?)"
					);
						stmt1.setInt(1, profID);
						stmt1.setString(2, "0");
						stmt1.executeUpdate();
					}
				
					finally {		
						DBUtil.closeQuietly(conn);
						DBUtil.closeQuietly(stmt1);
					}
					
					return null;
				}
		});
	}
	@Override
	public Integer addToProfessor(String user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				// finds role of account
				int profID = getProfID(user);
				
				PreparedStatement stmt1 = null;
				
				
				try {					
					// removes sub role account
					switch(getRole(user)) {
					case 0:
						removeFromAdmin(user);
						break;
					case 1: 
						removeFromProfessor(user);
						break;
					default:
						removeFromStudent(user);	
					}
					
					// inserts in blank student
					stmt1 = conn.prepareStatement(
							"insert into professors (prof_id, mod) "+
							"values(?, ?)"
					);
						stmt1.setInt(1, profID);
						stmt1.setString(2, "0");
						stmt1.executeUpdate();
					}
				
					finally {		
						DBUtil.closeQuietly(conn);
						DBUtil.closeQuietly(stmt1);
					}
					
					return null;
				}
		});
	}
	@Override
	public Integer addToStudent(String user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				// finds role of account
				int profID = getProfID(user);
				
				PreparedStatement stmt1 = null;
				
				try {					
					// removes sub role account
					switch(getRole(user)) {
					case 0:
						removeFromAdmin(user);
						break;
					case 1: 
						removeFromProfessor(user);
						break;
					default:
						removeFromStudent(user);	
					}
					
					// inserts in blank student
					stmt1 = conn.prepareStatement(
							"insert into students (prof_id, section, major) "+
							"values(?, ?, ?)"
					);
						stmt1.setInt(1, profID);
						stmt1.setString(2, "");
						stmt1.setString(3, "");
						stmt1.executeUpdate();
					}
				
					finally {		
						DBUtil.closeQuietly(conn);
						DBUtil.closeQuietly(stmt1);
					}
					
					return null;
				}
		});
	}
	@Override
	// deletes from sub branch
	public Integer removeFromAdmin(String user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				int profID = getProfID(user);
				
				try {
					// deletes from sub branch
					stmt1 = conn.prepareStatement(
						" delete "+
						" from admins " +
						" where prof_id = ? "
					);
					stmt1.setInt(1, profID);
					stmt1.executeUpdate();
					
					return null;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	@Override
	// deletes from sub branch
	public Integer removeFromProfessor(String user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				int profID = getProfID(user);
				
				
				try {
					// deletes from sub branch
					stmt1 = conn.prepareStatement(
						" delete "+
						" from professors " +
						" where prof_id = ? "
					);
					stmt1.setInt(1, profID);
					stmt1.executeUpdate();
					
					return null;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	@Override
	// deletes from sub branch
	public Integer removeFromStudent(String user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				int profID = getProfID(user);
				
				
				try {
					// deletes from sub branch
					stmt1 = conn.prepareStatement(
						" delete "+
						" from students " +
						" where prof_id = ? "
					);
					stmt1.setInt(1, profID);
					stmt1.executeUpdate();
					
					return null;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	@Override
	// deletes from all branches
	public Integer removeAccount(String user, int role) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				if(role == 0) {
					switch(getRole(user)) {
					case 0:
						removeFromAdmin(user);
						break;
					case 1: 
						removeFromProfessor(user);
						break;
					default:
						removeFromStudent(user);	
					}
				}
				else {
					removeFromStudent(user);
				}
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				
				try {
					// deletes from main branch
					stmt1 = conn.prepareStatement(
						" delete "+
						" from accounts " +
						" where username = ?"
					);
					stmt1.setString(1, user);
					stmt1.executeUpdate();
					
					return null;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	@Override
	public Integer updateRole(String user, boolean promo) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				int role = getRole(user);
				switch(role) {
				case 0:
					removeFromAdmin(user);
					break;
				case 1: 
					removeFromProfessor(user);
					break;
				default:
					removeFromStudent(user);	
				}
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				int newRole = 0;
				
				// checks type of role change
				if(promo == true) {
					// subtracts since account role integers are backwards
					newRole = role - 1;
				}
				else {
					// 0 is admin, 2 is student
					newRole = role + 1;
				}
				
				try {
					// updates main branch role 
					stmt1 = conn.prepareStatement(
						" update accounts "+
						" set role = ? " +
						" where username = ?"
					);
					stmt1.setInt(1, newRole);
					stmt1.setString(2, user);
					stmt1.executeUpdate();
				
					return null;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	@Override
	public Integer getGlobalMod() {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				// count of modStat, 0 or negative if reviewing off, positive if reviewing on
				int countStat = 0;
				
				try { 
					stmt1 = conn.prepareStatement(
						" select modstat "+
						" from admins "
					);
					resultSet1 = stmt1.executeQuery();
					
					while(resultSet1.next()) {
						// gets next mod from admin
						int modStat = resultSet1.getInt(1);
						switch(modStat) {
						case 1:
							// if off, subract, otherwise add
							countStat --;
							break;
						case 2: 
							countStat ++;
						}
					}
					return countStat;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
				}
			}
		});
	}
	
	@Override
	public Integer removeReview(String user, String title) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				int profID = getProfID(user);			
				try { 
					stmt1 = conn.prepareStatement(
						" delete "
						+ " from reviews "
						+ "where prof_id=? and name=? "
					);
					stmt1.setInt(1, profID);
					stmt1.setString(2, title);
					stmt1.executeUpdate();
					
					return null;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	@Override
	public ArrayList<Integer> getRevID(String keyword) {
		return executeTransaction(new Transaction<ArrayList<Integer>>() {
			@Override
			public ArrayList<Integer>execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				ArrayList<Integer> revIDS = new ArrayList<Integer>();
				try {
					stmt1 = conn.prepareStatement(
						" select rev_id "+
						" from keywords " +
						" where words = ?"
					);
					stmt1.setString(1, keyword);
					resultSet1 = stmt1.executeQuery();
					int foundRevID = -1;
				
					while(resultSet1.next()) { 
						int i = 1;
						foundRevID = resultSet1.getInt(i++);
						revIDS.add(foundRevID);
					}
					if(revIDS.size() != 0) {
						System.out.println("Found review");
						return revIDS;
					}
					else {
						System.out.println("No reviews found");
						return revIDS;
					}
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
	public ArrayList<String> parseTitle(String title){
		ArrayList<String> keys = new ArrayList<String>();
		if(title != null) {
		String[] parsed = title.split(" ");
		for(int i = 0; i < parsed.length - 1; i++) {
			keys.add(parsed[i]);
			if(parsed.length > 1) {
				keys.add(parsed[i] + " " + parsed[i+1]);
			}
		}
		}
		return keys;
	}
	
	@Override
	public ArrayList<String> addandParse(String title, int rev_id){
		ArrayList<String> keys = new ArrayList<String>();
		String[] parsed = title.split(" ");
		for(int i = 0; i < parsed.length - 1; i++) {
			addKeyword(parsed[i], rev_id);
			keys.add(parsed[i]);
			if(parsed.length > 1) {
				addKeyword(parsed[i] + " " + parsed[i+1], rev_id);
				keys.add(parsed[i] + " " + parsed[i+1]);
			}
		}
		return keys;
	}
	
	@Override
	public ArrayList<keywords> addKeyword(String keyword, int rev_id) {
		return executeTransaction(new Transaction<ArrayList<keywords>>() {
			@Override
			public ArrayList<keywords> execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				ArrayList<keywords> keys= new ArrayList<keywords>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into keywords(words, rev_id) "+
						"values(?, ?)"
					);
					stmt1.setString(1, keyword);
					stmt1.setInt(2, rev_id);
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
						"select * "
						+ "from keywords "
						+ "where words = ? ");
					stmt2.setString(1, keyword);
					resultSet1 = stmt2.executeQuery();
					while(resultSet1.next()) {
						int i = 1;
						keywords key = new keywords();
						key.setkeywordID(resultSet1.getInt(i++)); //FIX
						key.setkeyWord(resultSet1.getString(i++));
						keys.add(key);
					}
					if(keys.size() < 0) {
						System.out.println("Keyword not added correctly");
					}
					else {
						System.out.println("Keyword found");
					}
					return keys;
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
	public ArrayList<Review> getReviews(int rev_id) {
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
							+ "where rev_id = ? " );
					stmt1.setInt(1, rev_id);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						Review review = new Review();
						loadReview(review, resultSet1, 1);
						reviews.add(review);
					}
					if(reviews.size() >= 1) {
						//System.out.println("Found reviews");
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
	public ArrayList<Student> approveStudent(String user) {
		return executeTransaction(new Transaction<ArrayList<Student>>() {
			@Override
			public ArrayList<Student> execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				ArrayList<Student> students= new ArrayList<Student>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				try {
					stmt1 = conn.prepareStatement(
					"select username, password, email, section, major " 
					+ "from newStudents " 
					+ "where username = ? " 
					);
					stmt1.setString(1, user);
					
					resultSet1 = stmt1.executeQuery();
				
					String username = null;
					String password = null;
					String email = null;
					String section = null;
					String major = null;
					while(resultSet1.next()) {
						int i = 1;
						username = resultSet1.getString(i++);
						password = resultSet1.getString(i++);
						email = resultSet1.getString(i++);
						section = resultSet1.getString(i++);
						major = resultSet1.getString(i++);
					}
					addStudent(username, password, email, section, major);
					stmt2 = conn.prepareStatement(
							"delete " +
							"from newStudents " +
							"where username = ? and password = ? and email = ? and section = ? and major = ?");
					stmt2.setString(1, username);
					stmt2.setString(2, password);
					stmt2.setString(3, email);
					stmt2.setString(4, section);
					stmt2.setString(5, major);
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
							"select accounts.username, accounts.email, students.section, students.major "
							+ "from students, accounts "
							+ "where accounts.prof_id = students.prof_id "
							+ "and accounts.username = ? and email = ? and section = ? and major = ?"
							);
					
					stmt3.setString(1, user);
					stmt3.setString(2, email);
					stmt3.setString(3, section);
					stmt3.setString(4, major);
					resultSet2 = stmt3.executeQuery();
					int j = 1;
					while(resultSet2.next()) {
						Student student = new Student();
						student.setUsername(resultSet2.getString(j++));
						student.setEmail(resultSet2.getString(j++));
						student.setSection(resultSet2.getString(j++));
						student.setMajor(resultSet2.getString(j++));
						students.add(student);
					}
					return students;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
				}
			}
		});
	}
	@Override 
	public Integer checkUsername(String user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException{
				int result = 0;
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				stmt1 = conn.prepareStatement(
						"select email " +
						"from accounts " +
						"where username = ?");
				stmt1.setString(1, user);
				resultSet1 = stmt1.executeQuery();
				ArrayList<Student> students = new ArrayList<Student>();
				int i = 1;
				while(resultSet1.next()) {
					Student student = new Student();
					student.setEmail(resultSet1.getString((i++)));
					students.add(student);
				}
				if(!students.isEmpty()) {
					result = 1;
					return result;
				}
				return result;
			}
		});
	}
	@Override
	public ArrayList<Student> addNewStudent(String user, String pass, String email, String section, String major) {
		return executeTransaction(new Transaction<ArrayList<Student>>() {
			@Override
			public ArrayList<Student> execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				ArrayList<Student> students= new ArrayList<Student>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						"insert into newStudents (username, password, email, section, major) "+
						"values(?, ?, ?, ?, ?)"
					);
					stmt1.setString(1, user);
					stmt1.setString(2, pass);
					stmt1.setString(3, email);
					stmt1.setString(4, section);
					stmt1.setString(5, major);
					stmt1.executeUpdate();
				
					stmt2 = conn.prepareStatement(
							"select username, password, email, section, major "
							+ "from newStudents "
							+ "where username = ? and email = ? and password = ? and section = ? and major = ?"
							);
					stmt2.setString(1, user);
					stmt2.setString(2, email);
					stmt2.setString(3, pass);
					stmt2.setString(4, section);
					stmt2.setString(5, major);
					resultSet1 = stmt2.executeQuery();
					while(resultSet1.next()) {
						int i = 1;
						Student student = new Student();
						student.setUsername(resultSet1.getString(i++));
						student.setPassword(resultSet1.getString(i++));
						student.setEmail(resultSet1.getString(i++));
						student.setSection(resultSet1.getString(i++));
						student.setMajor(resultSet1.getString(i++));
						students.add(student);
					}
					return students;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	@Override
	public ArrayList<Student> unapprovedStudents() {
		return executeTransaction(new Transaction<ArrayList<Student>>() {
			@Override
			public ArrayList<Student> execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				ArrayList<Student> students= new ArrayList<Student>();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						"select username, password, email, section, major " +
						"from newStudents "
					);
					resultSet1 = stmt1.executeQuery();
					
					while(resultSet1.next()) {
						int i = 1;
						Student student = new Student();
						student.setUsername(resultSet1.getString(i++));
						student.setPassword(resultSet1.getString(i++));
						student.setEmail(resultSet1.getString(i++));
						student.setSection(resultSet1.getString(i++));
						student.setMajor(resultSet1.getString(i++));
						students.add(student);
					}
					return students;
				}
				finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}

	@Override
	public Integer resetPassword(String username, String password) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				try {
					stmt1 = conn.prepareStatement(
							"update accounts "
							+ "set password = ? "
							+ "where username = ? ");
					stmt1.setString(1, password);
					stmt1.setString(2, username);
					stmt1.execute();
					return 1;
				}
				catch(SQLException e){
					return -1;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);	
	}

	@Override
	public Integer averageReviewRating(String url) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				int avrgSum = 0;
				int counter=0;
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select * "
							+ "from reviews "
							+ "where url = ? ");
					stmt1.setString(1, url);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						
						Review review = new Review();
						loadReview(review, resultSet1, 1);
						avrgSum+= review.getRate();
						counter++;
					}
					avrgSum = avrgSum/counter;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
				return avrgSum;
			}
		}
		);
	}

	@Override
	public Integer changeReviewStatus(int status, int rev_id) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				try {
					stmt1 = conn.prepareStatement(
							"update reviews "
							+ "set status = ? "
							+ "where rev_id = ? ");
					stmt1.setInt(1, status);
					stmt1.setInt(2, rev_id);
					stmt1.execute();
					return 1;
				}
				catch(SQLException e){
					return -1;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}


	@Override
	public ArrayList<Student> denyStudent(String user) {
		return executeTransaction(new Transaction<ArrayList<Student>>() {
			@Override
			public ArrayList<Student> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ArrayList<Student> temp = new ArrayList<Student>();
				stmt1 = conn.prepareStatement(
						"delete "
						+ "from newStudents "
						+ "where username = ?" );
				stmt1.setString(1, user);
				stmt1.executeUpdate();
				return temp;
			}
		}
		);
	}

	@Override
	public ArrayList<Review> getReviewByStatus(int status) {
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
							+ "where status = ? ");
					stmt1.setInt(1, status);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						Review review = new Review();
						loadReview(review, resultSet1, 1);
						reviews.add(review);
					}
					if(reviews.size() >= 1) {
						//System.out.println("Found reviews");
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
	public ArrayList<String> getMajors() {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet results = null;
				ArrayList<String> temp = new ArrayList<String>();
				stmt1 = conn.prepareStatement(
						"select major "
						+ "from students " );
				results = stmt1.executeQuery();
				while(results.next()) {
					temp.add(results.getString(1)); 
				}
				return temp;
			}
		}
		);
	}
	
	@Override
	public ArrayList<String> getStudentUserNames() {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				ArrayList<String> students = new ArrayList<String>();
				
				stmt1 = conn.prepareStatement(
						"select prof_id "
						+ "from students");
				resultSet1 = stmt1.executeQuery();
				while(resultSet1.next()) {
					students.add(getUser(resultSet1.getInt(1)));
				}
				return students;
			}
		}
		);
	}
	@Override
	public String getUser(int profID) {
		return executeTransaction(new Transaction<String>() {
			@Override
			public String execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
						" select username "+
						" from accounts " +
						" where prof_id = ?"
					);
					stmt1.setInt(1, profID);
					resultSet1 = stmt1.executeQuery();
					String foundUser = null;
					while(resultSet1.next()) { 
						foundUser = resultSet1.getString(1);
					}
					if(foundUser != null) {
						//System.out.println("Found Account");
						return foundUser;
					}
					return null;
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
	public ArrayList<Integer> getStudents() {
		return executeTransaction(new Transaction<ArrayList<Integer>>() {
			@Override
			public ArrayList<Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet results = null;
				ArrayList<Integer> temp = new ArrayList<Integer>();
				stmt1 = conn.prepareStatement(
						"select prof_id "
						+ "from students " );
				results = stmt1.executeQuery();
				while(results.next()) {
					temp.add(results.getInt(1)); 
				}
				return temp;
			}
		});
	}
	@Override
	public ArrayList<Integer> getReviewTop() {
		return executeTransaction(new Transaction<ArrayList<Integer>>() {
			@Override
			public ArrayList<Integer> execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				ArrayList<String> urls = new ArrayList<String>();
				try {
					stmt1 = conn.prepareStatement(
						" select url "+
						" from reviews ");
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) { 
						urls.add(resultSet1.getString(1));
					}
					ArrayList<String> unique = new ArrayList<String>();
					unique.addAll(getReviewUnique());
				
					ArrayList<Integer> top = new ArrayList<Integer>();
					
					for(int i = 0; i < unique.size(); i ++) {
						top.add(0);
					}
					
					for(int x = 0; x < top.size(); x ++) {
						for(int y = 0; y < urls.size(); y ++) {
							if(urls.get(y).equals(unique.get(x))) {
								top.set(x, top.get(x) + 1);
							}
						}
					}
					return top;
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
	public ArrayList<String> getReviewUnique() {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				//  Auto-generated method stub
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				ArrayList<String> unique = new ArrayList<String>();
				try {
					stmt1 = conn.prepareStatement(
						" select url "+
						" from reviews ");
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) { 
						boolean count = true;
						for(int i = 0; i < unique.size(); i ++) {
							if(resultSet1.getString(1).equals(unique.get(i))){
								count = false;
							}
						}
						if(count) {
							unique.add(resultSet1.getString(1));
						}
					}				
					return unique;
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
	public ArrayList<String> getReviewNameByURL(String URL) {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				ArrayList<String> names = new ArrayList<String>();
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select name "
							+ "from reviews "
							+ "where url = ? ");
					stmt1.setString(1, URL);
					resultSet1 = stmt1.executeQuery();
					while(resultSet1.next()) {
						names.add(resultSet1.getString(1));
					}
					if(names.size() >= 1) {
						//System.out.println("Found reviews");
						return names;
					}
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
				return names;
			}
		}
		);
	}
	@Override
	public ArrayList<String> getURLfromReview(int revID) {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String>  execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select prof_id "
							+ "from reviews "
							+ "where url = ? ");
					stmt1.setInt(1, revID);
					resultSet1 = stmt1.executeQuery();
					int i = 1;
					ArrayList<String> profIDS = new ArrayList<String>();
					while(resultSet1.next()) {
						profIDS.add(resultSet1.getString(i++));
					}
					return profIDS;
				}
				finally {
					DBUtil.closeQuietly(conn);
				}
			}
		}
		);
	}

}	

