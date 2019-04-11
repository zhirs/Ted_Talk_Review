package tedtalkDB.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tedtalk.model.*;
import tedtalkDB.model.Account;
import tedtalkDB.model.AccountReview;
import tedtalkDB.model.Review;


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
			
				try {
					stmt1 = conn.prepareStatement(
						"create table accounts(" +
						"	prof_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	username varchar(40)," +
						"	password varchar(40), " +
						"   email varchar(40)" +
						")"
					);	
					stmt1.executeUpdate();
					
					System.out.println("Accounts table created");
					
					stmt2 = conn.prepareStatement(
							"create table reviews(" +
							"	rev_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
//							"	author_id integer constraint author_id references authors, " +  	// this is now in the BookAuthors table
							"	name varchar(100)," +
							"	pres varchar(100)," + 
							"	topic varchar(100)," +
							"	desc varchar(1000)," +
							"   status int" + //MISSING COMMA. 
							//"   subDate date" +
							")"
					);
					stmt2.executeUpdate();
					
					System.out.println("Reviews table created");					
					
					stmt3 = conn.prepareStatement(
							"create table accountReviews(" +
							"	prof_id integer constraint prof_id references accounts, " +
							"	rev_id integer constraint rev_id references reviews " +
							")"
					);
					stmt3.executeUpdate();
					
					System.out.println("ReviewAuthors table created");					
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Account> accountList;
				List<Review> reviewList;
				List<AccountReview> accountReviewList;
				
				try {
					accountList     = InitialData.getUsers();
					reviewList       = InitialData.getReviews();
					accountReviewList = InitialData.getAccountReviews();					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAccount     = null;
				PreparedStatement insertReview       = null;
				PreparedStatement insertAccountReview = null;

				try {
					// must completely populate Authors table before populating BookAuthors table because of primary keys
					insertAccount = conn.prepareStatement("insert into accounts (username, password, email) values (?, ?, ?)");
					for (Account account: accountList) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
						insertAccount.setString(1, account.getUserName());
						insertAccount.setString(2, account.getPassword());
						insertAccount.setString(3, account.getEmail());
						insertAccount.addBatch();
					}
					insertAccount.executeBatch();
					
					System.out.println("Account table populated");
					
					// must completely populate Books table before populating BookAuthors table because of primary keys
					insertReview = conn.prepareStatement("insert into reviews (name, pres, topic, desc, status) values (?, ?, ?, ?, ?)");
					for (Review review : reviewList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
//						insertBook.setInt(1, book.getAuthorId());	// this is now in the BookAuthors table
						insertReview.setString(1, review.getName());
						insertReview.setString(2, review.getPres());
						insertReview.setString(3, review.getTopic());
						insertReview.setString(4, review.getDesc());
						insertReview.setInt(5, review.getStatus());
						//insertReview.setDate(6, review.getDate());
						
						insertReview.addBatch();
					}
					insertReview.executeBatch();
					
					System.out.println("Reviews table populated");					
					
					// must wait until all Books and all Authors are inserted into tables before creating BookAuthor table
					// since this table consists entirely of foreign keys, with constraints applied
					insertAccountReview = conn.prepareStatement("insert into accountReviews (prof_id, rev_id) values (?, ?)");
					for (AccountReview accountReview : accountReviewList) {
						insertAccountReview.setInt(1, accountReview.getProfId());
						insertAccountReview.setInt(2, accountReview.getRevId());
						insertAccountReview.addBatch();
					}
					insertAccountReview.executeBatch();	
					
					System.out.println("AuthorReviews table populated");					
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertReview);
					DBUtil.closeQuietly(insertAccount);
					DBUtil.closeQuietly(insertAccountReview);					
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}


	@Override
	public boolean checkCredentials(String user, String pass) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Account setLogin(String user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<Account> addUser(String user, String pass, String email, String section, int role) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<Review> getProfIDReviewList(int profID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getReviewTotal(int profID) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ArrayList<Review> findReview(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}
