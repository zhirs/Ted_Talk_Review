package tedtalkDB.persist;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tedtalkDB.model.Professor;
import tedtalkDB.model.Student;
import tedtalkDB.model.keywords;
import tedtalkDB.model.Review;
import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;

public class DerbyDatabaseTests {

	private IDatabase db = null;
	private int role;
	 
	ArrayList<Student> students = null;
	ArrayList<Professor> professors = null;
	ArrayList<Review> reviews = null;
	ArrayList<NetworkAdmin> admins= null;	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testAddAdmin() {
		System.out.println("\n*** Testing addAdmin***");

		String user  = "kidRock";
		String pass  = "testpass";
		String email = "jjrocks@ycp.edu";
		int modStat = 0;
  
				
		// insert new book (and possibly new author) into DB
		admins = db.addAdmin(user, pass, email, modStat);
		// check the return value - should be a book_id > 0
		if (admins.size() >  0)
		{
			// try to retrieve the book and author from the DB
			// get the list of (Author, Book) pairs from DB
			
			if (db.getProfID(user)== null) {
				System.out.println("Failed to insert new admin" + user);
				fail("Failed to insert new admin<" + user + ">");
			}
			// otherwise, the test was successful.  Now remove the book just inserted to return the DB
			// to it's original state, except for using an author_id and a book_id
			else {
				System.out.println("New admin (ID: " + db.getProfID(user)+ ") successfully added to admins table: <" + user + ">");
				
				// now delete Book (and its Author) from DB
				// leaving the DB in its previous state - except that an author_id, and a book_id have been use	
			}
		}
		else
		{
			System.out.println("Failed to insert new admin into admin table: <" + user + ">");
			fail("Failed to insert new admin <" + user + "> ");
		}
		db.removeAccount(user, db.getRole(user));
	}
	@Test
	public void testAddProfessor() {
		System.out.println("\n*** Testing addProfessor***");

		String user  = "newProf";
		String pass  = "testpass";
		String email = "profZ@ycp.edu";
		int mod = 0;
  
				
		// insert new book (and possibly new author) into DB
		System.out.println("adding now");
		professors = db.addProfessor(user, pass, email, mod);

		// check the return value - should be a book_id > 0
		if (professors.size() >  0)
		{
			// try to retrieve the book and author from the DB
			// get the list of (Author, Book) pairs from DB
			
			if (db.getProfID(user) == null) {
				System.out.println("Failed to insert new professor" + user);
				fail("Failed to insert new professor<" + user + ">");
			}
			// otherwise, the test was successful.  Now remove the book just inserted to return the DB
			// to it's original state, except for using an author_id and a book_id
			else {
				System.out.println("New professor (ID: " + db.getProfID(user)+ ") successfully added to professor table: <" + user + ">");
				
				// now delete Book (and its Author) from DB
				// leaving the DB in its previous state - except that an author_id, and a book_id have been use	
			}
		}
		else
		{
			System.out.println("Failed to insert new professor into professor table: <" + user + ">");
			fail("Failed to insert new professor <" + user + "> ");
		}
		db.removeAccount(user, db.getRole(user));
	}
	@Test
	public void testAddReview() {
		System.out.println("\n*** Testing addReview***");

		String URL  = "newReview.com";
		String name  = "testpass";
		int rate = 5;
		String pres = "The man";
		String desc = "So tired of databases";
		int profID = 9;
		String tag = "Mechanical";
		int status = 0;
  
				
		// insert new book (and possibly new author) into DB
		reviews = db.addReview(URL, name, rate, pres, desc, profID, tag, status);

		// check the return value - should be a book_id > 0
		if (reviews.size() >  0)
		{
			// try to retrieve the book and author from the DB
			// get the list of (Author, Book) pairs from DB
			
			if (reviews.get(0).getrevID() > 0) {
				System.out.println("New review (ID: " + reviews.get(0).getRevID() + ") successfully added to reviews table>");
			
			}
			// otherwise, the test was successful.  Now remove the book just inserted to return the DB
			// to it's original state, except for using an author_id and a book_id
			else {
				System.out.println("Failed to insert new review" + reviews.get(0).getRevID());
				fail("Failed to insert new admin<" + reviews.get(0).getRevID() + ">");
				// now delete Book (and its Author) from DB
				// leaving the DB in its previous state - except that an author_id, and a book_id have been use	
			}
		}
		else
		{
			System.out.println("Failed to insert new review into reviews table: <>");
			fail("Failed to insert new review");
		}
	}
	
	@Test
	public void testAddStudent() {
		System.out.println("\n*** Testing addProfessor***");

		String user  = "newProf";
		String pass  = "testpass";
		String email = "profZ@ycp.edu";
		String section = "CS320";
		String major = "Civil Engineering";
  
				
		// insert new book (and possibly new author) into DB
		System.out.println("adding now");
		students = db.addStudent(user, pass, email, section, major);

		// check the return value - should be a book_id > 0
		if (students.size() >  0)
		{
			// try to retrieve the book and author from the DB
			// get the list of (Author, Book) pairs from DB
			
			if (db.getProfID(user) == null) {
				System.out.println("Failed to insert new student" + user);
				fail("Failed to insert new student<" + user + ">");
			}
			// otherwise, the test was successful.  Now remove the book just inserted to return the DB
			// to it's original state, except for using an author_id and a book_id
			else {
				System.out.println("New student(ID: " + db.getProfID(user)+ ") successfully added to student table: <" + user + ">");
				
				// now delete Book (and its Author) from DB
				// leaving the DB in its previous state - except that an author_id, and a book_id have been use	
			}
		}
		else
		{
			System.out.println("Failed to insert new student into student table: <" + user + ">");
			fail("Failed to insert new student<" + user + "> ");
		}
		db.removeAccount(user, db.getRole(user));
	}
	@Test
	public void testAdminByProfID() {
		System.out.println("\n*** Testing adminByProfID***");
		int profID = 1;
		//Should return jlandau2
		NetworkAdmin admin = db.adminByProfID(profID);
		if(admin.getUserName() == null) {
			System.out.println("Faiiled to retrieve an admin by profID");
			fail("Failed to retrieve an admin");
		}
		else {
			System.out.println(admin.getUserName());
			if(admin.getUserName().equals("jlandau2")){
				System.out.println("Admin found: " + admin.getUserName());
			}
			else {
				System.out.println("Incorrect admin username");
				fail("Incorrect admin username");
			}
		}
	}
	@Test
	public void testCheckCredentials() {
		System.out.println("\n*** Testing checkCredentials***");
		String user = "jlandau2";
		String pass = "tree";
		Boolean found = db.checkCredentials(user, pass);
		if (!found){
			System.out.println("No account found");
			fail("No account with those credentials found");
		}
		else {
			System.out.println("Account found");
			}	
	}
	
	@Test
	public void testFindReview() {
		System.out.println("\n*** Testing findReview***");
		String title = "Joseph Landau's Symposium";
		ArrayList<Review> found = db.findReview(title);
		
		if (found.size() == 0){
			System.out.println("No review found");
			fail("Review not loaded");

		}
		else {
			System.out.println("Review found");
			System.out.println(found.get(0).getName() + "\nWritten by:" + found.get(0).getProfID() + "  \nTalk by:" + found.get(0).getPres() + found.get(0).getDate());
			}
	}
	
	@Test
	public void testgetMod() {
		System.out.println("\n*** Testing getMod***");
		int profID = 5;
		if (db.getMod(profID) >= 0) {
			System.out.println("Mod found");
			System.out.print(db.getMod(profID));
		}
		else {
			System.out.println("Mod not found");
			fail("Mod not found");
		}
	}
	
	@Test
	public void testgetModStat() {
		System.out.println("\n*** Testing getModStat***");
		int profID = 1;
		if (db.getModStat(profID) >= 0) {
			System.out.println("Modstat found");
			System.out.print(db.getModStat(profID));
		}
		else {
			System.out.println("Modstat not found");
			fail("Mod not found");
		}
	}
	@Test
	public void testGetProfID() {
		System.out.println("\n*** Testing getProfID***");
		String username = "jlandau2";
		if (db.getProfID(username) >= 0) {
			if(db.getProfID(username)==1) {
				System.out.print(db.getProfID(username));
			}
			else {
				System.out.println(db.getProfID(username) + "is the wrong id");
				fail("Wrong id");
			}
		}
		else {
			System.out.println("Mod not found");
			fail("Mod not found"); 
		}
	}
	
	@Test
	public void testGetProfIDReviewList() {
		System.out.println("\n*** Testing getProfIDReviewList**");
		int profID = 9;
		reviews = db.getProfIDReviewList(profID, 0);
		if(reviews.size() <= 0) {
			System.out.println("No reviews found");
			fail("No reviews found");
		}
		else {
			System.out.println(reviews.get(0).getName() + reviews.get(0).getDesc());
		}
	}
	
	@Test
	public void testGetReviewsBetweenDates() {
		System.out.println("\n*** Testing getReviewsBetweenDates***");
		int profID = 8;
		String date1 = "2019/04/26";
		java.util.Date utilDate1 = new java.util.Date(date1);
	    java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
	   
		String date2 = "2019/04/28";
		java.util.Date utilDate2 = new java.util.Date(date2);
	    java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
	    
		reviews = db.getReviewsBetweenDates(profID, sqlDate1, sqlDate2);
		if(reviews.size() <= 0) {
			System.out.println("No reviews found");
			fail("No reviews found");
		}
		else {
			System.out.println(reviews.get(0).getName() + reviews.get(0).getDesc());
		}
	}
	
	@Test
	public void testGetReviewTotal() {
		System.out.println("\n*** Testing getReviewTotal***");
		int profID = 8;
		int total = db.getReviewTotal(profID);
		if (total >= 0) {
			System.out.print(total);
		}
		else {
			System.out.println("wrong total");
			fail("load error"); 
		}
	}
	
	@Test
	public void testGetStatus() {
		System.out.println("\n*** Testing getStats***");
		int rev_id = 1;
		int stat = db.getStatus(rev_id);
		if(stat >= 0) {
			System.out.println("found status "+ stat);
		}
		else {
			System.out.println("load error");
			fail("load error");
		}
	}

	@Test
	public void testSetLogin() {
		System.out.println("\n*** Testin setLogin***");
		Account found = db.setLogin("jlandau2");
		if (found == null){
			System.out.println("account error");
			fail("account error");
		}
		else {
			System.out.println("Account set");
			if(found.getUserName().equals("jlandau2")) {
				System.out.println(found.getUserName() + " " + found.getprofID() + " " + found.getEmail());
			}
			else {
				System.out.println("Wrong user");
				fail("wrong user");
			}
		}			
	}
	
	@Test
	public void testStudentByMajor() {
		System.out.println("\n*** Testing studentsByMajor***");
		String major = "Psychology";
		ArrayList<Student> found = db.studentsByMajor(major);
		if (found.size() == 0){
			System.out.println("No Students Found");
			fail("No students found");
		}
		else {
			for(int i = 0; i < found.size(); i++){
				System.out.println(found.get(i).getUserName());
			}
		}
	}
	
	@Test
	public void testStudentByProfID() {
		System.out.println("\n*** Testing studentByProfID***");
		int profID = 9;
		Student found = db.studentByProfID(profID);
		if (found !=  null){
			if(found.getUserName().equals("student2")) {
				System.out.println("found correct student");
			}
			else {
				System.out.println(found.getUserName());
				fail("incorrect student");
			}
		}
		else {
			System.out.println("No Students Found");
			fail("no student found");
		}
	}
	
	@Test
	public void testUpdateMod() {
		System.out.println("\n*** Testing updateMod***");
		int input = 6;
		int found = db.getMod(input);
		if (found == -1){
			System.out.println("Load error");
			fail("load error");
		}
		else if (found == 1) {
			db.updateMod(input, 0);
			System.out.println("found: " + found + " changing to 0");
		}
		else if (found == 0) {
			db.updateMod(input, 1);
			System.out.println("found: " + found + " changing to 1");
		}
		int check1 = found;
		int check2 = db.getMod(input);
		
		if(check1 == 0) {
			if(check2 == 1) {
				System.out.println("Correctly updated");
			}
			else {
				System.out.println("Incorrectly updated");
				fail("incorrectly updated");
			}
		}
		else if(check1 == 1) {
			if(check2 == 0) {
				System.out.println("Correctly updated");
			}
			else {
				System.out.println("Incorrectly updated");
				fail("incorrectly updated");
			}
		}
	}
	@Test
	public void testUpdateModStat() {
		System.out.println("\n*** Testing updateModStat***");
		int input = 1;
		int found = db.getModStat(input);
		if (found == -1){
			System.out.println("Load error");
			fail("load error");
		}
		else if (found == 1) {
			db.updateModStat(input, 0);
			System.out.println("found: " + found + " changing to 0");
		}
		else if (found == 0) {
			db.updateModStat(input, 1);
			System.out.println("found: " + found + " changing to 1");
		}
		int check1 = found;
		int check2 = db.getModStat(input);
		
		if(check1 == 0) {
			if(check2 == 1) {
				System.out.println("Correctly updated");
			}
			else {
				System.out.println("Incorrectly updated");
				fail("incorrectly updated");
			}
		}
		else if(check1 == 1) {
			if(check2 == 0) {
				System.out.println("Correctly updated");
			}
			else {
				System.out.println("Incorrectly updated");
				fail("incorrectly updated");
			}
		}
	}
	
	@Test
	public void testUpdateSection() {
		System.out.println("\n*** Testing updateSection***");
		int profID = 8;
		String section = "testSection";
		String initialState = db.getSection(profID);
		int found = db.updateSection(profID, section);
		if (found == -1){
			System.out.println("Load error");
			fail("section failed to load");
		}
		else if (found == 1) {
			String newStatus = db.getSection(profID);
			if(newStatus.equals(section)) {
				System.out.println("Section loaded correctly");
				System.out.println("found: " + initialState + " changed to " + newStatus);
			}
			else {
				System.out.println("section loaded incorrectly");
				fail("section loaded incorrectly");
			}
		}
	}
	@Test
	public void testGetSection() {
		System.out.println("\n*** Testing getSection***");
		int profID= 8;
		String currentSection = db.getSection(profID);
		if(currentSection.isEmpty()){
			System.out.println("load error");
			fail("load error"); 
		}
		else {
			System.out.println("found section " + currentSection);
		}
	}
	
	@Test
	public void testUpdateStatus() {
		System.out.println("\n*** Testing updateStatus***");
		int revID = 1;
		int status = 2;
		int initialStatus = db.getStatus(revID);
		int found = db.updateStatus(revID, status);
		if (found == -1){
			System.out.println("Load error");
			fail("load error");
		}
		else if (found == 1) {
			int newStatus = db.getStatus(revID);
			System.out.println("found: " + initialStatus + " changed to " + newStatus);
		}
	}
	@Test
	public void testGetRole() {
		String username = "jlandau2";
		role = db.getRole(username);
		if (role >= 0) {	//zero role number should be for network admin
			if(role!=0) {
				System.out.println(db.getProfID(username) + "is the wrong id");
				fail("Wrong id");
			}
		}
		else {
			System.out.println("Account role not found");
			fail("Account not found"); 
		}
	}
	
	@Test
	public void testGetRevID() {
		String key = "Endgame";
		
		ArrayList<Integer> revs = db.getRevID(key);
		if(revs.get(0) != 3) {
			fail("Incorrect review found");
		}
		
		ArrayList<Integer> revs2 = db.getRevID("Hill");
		if(revs2.get(0) != 2) {
			fail("Incorrect review found");
		}
		
		ArrayList<Integer> revs3 = db.getRevID("Symposium");
		if(revs3.get(0) != 1) {
			fail("Incorrect review found");
		}
	}
	
	
	@Test
	public void testaddandParse() {
		String URL = "placeholder.com";
		String name = "One hundred dollars would be nice";
		int rate = 5;
		String pres = "Brendan Frasier";
		String desc = "I bet the big B.F. could make a great database";
		int profID = 9;
		String tag = "Civil Engineering";
		int status = 0;
		ArrayList<Review> revs = db.addReview(URL, name, rate, pres, desc, profID, tag, status);
		int rev_id = revs.get(0).getrevID();
		System.out.print(rev_id);
		ArrayList<String> keys =  db.addandParse(name, rev_id);
		if(!keys.contains("hundred")){
			fail("nothing added");
		}
	}
	
	@Test
	public void testApproveStudent() {
		
		String user = "dhill23";
		String pass = "test";
		String email= "jlandau68@ycp.edu";
		String section = "CS365";
		String major = "Civil Engineering";
		ArrayList<Student> addStudent = db.addNewStudent(user, pass, email, section, major);
		if(addStudent.isEmpty()) {
			fail("No new student added");
		}
		ArrayList<Student> approved = db.approveStudent(user);
		if(approved.isEmpty()) {
			fail("No students approved");
		}
		db.removeAccount(user, db.getRole(user));
	}
	
	@Test
	public void testCheckUsername() {
		int check = db.checkUsername("jlandau2");
		if(check == 0) {
			fail("failed existing username hit");
		}
	}
	
	@Test
	public void testGetGlobalModStat() {
		for(int x = 1; x <= 4; x ++) {
			db.updateModStat(x, 1);
		}
		
		int global = db.getGlobalMod();
		assertTrue(global < 1);
		
		for(int x = 1; x <= 4; x ++) {
			db.updateModStat(x, 0);
		}
	}
	@Test
	public void testDenyStudent() {
		int preSize = db.unapprovedStudents().size();
		db.addNewStudent("user", "pass", "email", "section", "major");
		db.denyStudent("user");
		ArrayList<Student> postt = db.unapprovedStudents();
		int postSize = postt.size();
		if(postSize != preSize) {
			fail("Deny incorrect.");
		}
	}

}
	

	