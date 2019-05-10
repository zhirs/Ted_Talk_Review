package tedtalkDB.Controller;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.DerbyDatabase;
import tedtalkDB.persist.IDatabase;

public class NetworkAdminControllerTest {
	private NetworkAdminController controllerHandle = new NetworkAdminController();
	private NetworkAdmin NAModel = new NetworkAdmin();
	private IDatabase db;
	ArrayList<Student> students;
	ArrayList<Professor> professors;
	ArrayList<Review> reviews;
	ArrayList<NetworkAdmin> admins;
	
	@Before
	public void init() {
		//ASSOCIATING MODEL WITH CONTROLLER:
		controllerHandle.setModel(NAModel);
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
		students = new ArrayList<Student>();
		professors = new ArrayList<Professor>();
		admins = new ArrayList<NetworkAdmin>();
	}

	@Test
	public void Test_Verified_Existing_User0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("dhill22");
		NAModel.setPassword("banana");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("jlandau2");
		NAModel.setPassword("tree");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified()); //used to be
		if(!controllerHandle.verified()) {
		fail("expected result: true" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: true");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
		
	}
	@Test
	public void Test_Verified_Existing_User2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("zhirs");
		NAModel.setPassword("monkey");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified()); //used to be
		if(!controllerHandle.verified()) {
		fail("expected result: true" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: true");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
		
	}
	@Test
	public void Test_Verified_Existing_User3() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("acastro7");
		NAModel.setPassword("wizard");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified()); //used to be
		if(!controllerHandle.verified()) {
		fail("expected result: true" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: true");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
		
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("dhill22");
		NAModel.setPassword("Monkey");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified() == false); //used to be
		if(controllerHandle.verified()) {
		fail("expected result: false" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: false");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("acastro7");
		NAModel.setPassword("Monkey");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified() == false); //used to be
		if(controllerHandle.verified()) {
		fail("expected result: false" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: false");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("jlandau2");
		NAModel.setPassword("wizard");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified() == false); //used to be
		if(controllerHandle.verified()) {
		fail("expected result: false" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: false");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("Jlandau2");
		NAModel.setPassword("Tree");
		//TESTING VERIFIED():
		
		//assertTrue(controllerHandle.verified() == false); //used to be
		if(controllerHandle.verified()) {
		fail("expected result: false" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: false");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("Dhill22");
		NAModel.setPassword("banana");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified() == false); //used to be
		if(controllerHandle.verified()) {
		fail("expected result: false" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: false");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("acastrO7");
		NAModel.setPassword("Wizard");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified() == false); //used to be
		if(controllerHandle.verified()) {
		fail("expected result: false" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: false");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
	}
	@Test
	public void Test_Verified_Nonexisting_User0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("_dhill22");
		NAModel.setPassword("nana");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified() == false); //used to be
		if(controllerHandle.verified()) {
		fail("expected result: false" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: false");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
	}
	@Test
	public void Test_Verified_Nonexisting_User1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("jlandau20");
		NAModel.setPassword("tree");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified() == false); //used to be
		if(controllerHandle.verified()) {
		fail("expected result: false" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: false");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
	}
	@Test
	public void Test_Verified_Nonexisting_User2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("jlandau20");
		NAModel.setPassword("tree");
		//TESTING VERIFIED():
		//assertTrue(controllerHandle.verified() == false); //used to be
		if(controllerHandle.verified()) {
		fail("expected result: false" + "\n your result: " + controllerHandle.verified());
		//System.out.println("expected result: false");	//combined all the print statements into one
		//System.out.println("your result: " + controllerHandle.verified());
		}
	}
	@Test
	public void testAddAdmin() {
		String user  = "testAdmin";
		String pass  = "testpass";
		String email = "jjrocks@ycp.edu";
		int modStat = 0;
				
		// insert new admin into DB
		admins = db.addAdmin(user, pass, email, modStat);

		// method should returned a value for the array
		if (admins.size() >  0)
		{
			// try to profile id from the DB	
			if (db.getProfID(user)== null) {
				System.out.println("Failed to insert new admin" + user);
				fail("Failed to insert new admin<" + user + ">");
			}
		}
		else
		{
			System.out.println("Failed to insert new admin into admin table: <" + user + ">");
			fail("Failed to insert new admin <" + user + "> ");
		}
	}
	@Test
	public void testAddProfessor() {
		String user  = "testProfessor";
		String pass  = "testpass";
		String email = "profZ@ycp.edu";
		int mod = 0;
				
		// insert new professor into DB
		professors = db.addProfessor(user, pass, email, mod);

		//method should have added a class to the array
		if (professors.size() >  0)
		{
			if (db.getProfID(user) == null) {
				System.out.println("Failed to insert new professor" + user);
				fail("Failed to insert new professor<" + user + ">");
			}
		}
		else
		{
			System.out.println("Failed to insert new professor into professor table: <" + user + ">");
			fail("Failed to insert new professor <" + user + "> ");
		}
	}
	@Test
	public void testAddStudent() {
		String user  = "newStudent";
		String pass  = "testpass";
		String email = "profZ@ycp.edu";
		String section = "CS320";
		String major = "Computer Science";
  
				
		// insert new student into DB
		students = db.addStudent(user, pass, email, section, major);

		// methods should add a class into the array list
		if (students.size() >  0)
		{	
			if (db.getProfID(user) == null) {
				System.out.println("Failed to insert new student" + user);
				fail("Failed to insert new student<" + user + ">");
			}
		}
		else
		{
			System.out.println("Failed to insert new student into student table: <" + user + ">");
			fail("Failed to insert new student<" + user + "> ");
		}
	}
	
	@Test
	public void promoteDemoteTest() {
		String user  = "newProfessor";
		String pass  = "testpass";
		String email = "profP@ycp.edu";
		int mod = 0;
		
		db.addProfessor(user, pass, email, mod);
		
		int profID = db.getProfID("newProfessor");
		controllerHandle.promoteDemote(true, "newProfessor");
		
		assertTrue(db.getRole("newProfessor") == 0);
		assertTrue(db.getProfID("newProfessor") == profID);
		
		String user1  = "testAdmin";
		String pass1  = "testpass";
		String email1 = "jjrocks@ycp.edu";
		int modStat = 0;
		
		db.addAdmin(user1, pass1, email1, modStat);
		
		profID = db.getProfID(user1);
		controllerHandle.promoteDemote(false, user1);
		
		assertTrue(db.getRole(user1) == 1);
		assertTrue(db.getProfID(user1) == profID);
		
		String user2  = "newStudent";
		String pass2  = "testpass";
		String email2 = "profZ@ycp.edu";
		String section = "CS320";
		String major = "Computer Science";
		
		db.addStudent(user2, pass2, email2, section, major);
		
		profID = db.getProfID(user2);
		controllerHandle.promoteDemote(true, user2);
		
		assertTrue(db.getRole(user2) == 1);
		assertTrue(db.getProfID(user2) == profID);
		
		controllerHandle.removeAccount(user2);
		controllerHandle.removeAccount(user1);
		controllerHandle.removeAccount(user);
	}
	//***************************************************************     17 TEST CASES     *******************************************************************
	@Test
	public void testLoadUnapproveds() {
		ArrayList<Student> newbs = controllerHandle.approveAllStudents();
		System.out.println(newbs.size());
		ArrayList<Student> news = controllerHandle.loadUnapproveds();
		System.out.println(news.size());
		assertTrue(news.size() == 0);
	}
}
