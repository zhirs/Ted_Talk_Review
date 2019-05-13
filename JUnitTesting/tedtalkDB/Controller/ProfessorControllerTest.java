package tedtalkDB.Controller;
import tedtalkDB.model.*;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.DerbyDatabase;
import tedtalkDB.persist.IDatabase;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ProfessorControllerTest {
	Professor professorHandle = new Professor();
	ProfessorController controllerHandle = new ProfessorController();
	private IDatabase db;
	ArrayList<Student> students;
	@Before
	public void init() {
		controllerHandle.setModel(professorHandle);
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
		students = new ArrayList<Student>();
	}
	@Test
	public void Test_Verified_Existing_User0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("profX");
		professorHandle.setPassword("profx");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Existing_User1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("profY");
		professorHandle.setPassword("profy");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Existing_User2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("profZ");
		professorHandle.setPassword("profz");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("profz");
		professorHandle.setPassword("professorY");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("profy");
		professorHandle.setPassword("professorZ");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("profx");
		professorHandle.setPassword("wizard");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("profX");
		professorHandle.setPassword("professorX");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("PROFY");
		professorHandle.setPassword("professorY");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("pRofz");
		professorHandle.setPassword("professorZ");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Nonexisting_User0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("Professor3l22");
		professorHandle.setPassword("hello");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Nonexisting_User1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("prof");
		professorHandle.setPassword("professor");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Nonexisting_User2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		professorHandle.setUsername("profxyz");
		professorHandle.setPassword("prefeso");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
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
		db.removeAccount(user, 2);
	}
	
	@Test
	public void testSwitchMod() {
		String user  = "newProfessor";
		String pass  = "testpass";
		String email = "profP@ycp.edu";
		int mod = 0;
		
		db.addProfessor(user, pass, email, mod);
		assertTrue(db.getMod(db.getProfID(user)) == 0);
		controllerHandle.switchMod(user);
		assertTrue(db.getMod(db.getProfID(user)) != 0);
		db.removeAccount(user, 1);
	}
	
	@Test
	public void testSearch() {
		String name = "Endgame spoilers suck";
		ArrayList<Review> revs = new ArrayList<Review>();
		revs.addAll(controllerHandle.search(name));
		assertTrue(revs.size() > 0);
	}
	//***************************************************************     12 TEST CASES     *******************************************************************

}

