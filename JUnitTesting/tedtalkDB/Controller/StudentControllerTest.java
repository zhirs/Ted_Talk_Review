package tedtalkDB.Controller;
import tedtalkDB.model.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StudentControllerTest {
	private StudentController controllerHandle = new StudentController();
	private Student studentHandle = new Student();
	@Before
	public void init() {
		//ASSOCIATING MODEL WITH CONTROLLER:
		controllerHandle.setModel(studentHandle);
	}
	@Test
	public void Test_Verified_Existing_User0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("student3");
		studentHandle.setPassword("muspi");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Existing_User1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("student2");
		studentHandle.setPassword("rab");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Existing_User2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("student1");
		studentHandle.setPassword("oof");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("student1");
		studentHandle.setPassword("ooF");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("student2");
		studentHandle.setPassword("Rab");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("student3");
		studentHandle.setPassword("wizard");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("Student3");
		studentHandle.setPassword("plzno");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("stuDent1");
		studentHandle.setPassword("oof");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("Student2");
		studentHandle.setPassword("rab");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Nonexisting_User0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("Student3l22");
		studentHandle.setPassword("nana");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Nonexisting_User1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("tudent3");
		studentHandle.setPassword("plzno");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Nonexisting_User2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		studentHandle.setUsername("test321");
		studentHandle.setPassword("321tset");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	//***************************************************************     12 TEST CASES     *******************************************************************

}
