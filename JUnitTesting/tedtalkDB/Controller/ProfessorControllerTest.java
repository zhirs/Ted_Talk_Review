package tedtalkDB.Controller;
import tedtalkDB.model.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ProfessorControllerTest {
	Professor professorHandle = new Professor();
	ProfessorController controllerHandle = new ProfessorController();
	@Before
	public void init() {
		controllerHandle.setModel(professorHandle);
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
	//***************************************************************     12 TEST CASES     *******************************************************************

}

