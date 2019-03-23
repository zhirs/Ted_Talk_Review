package tedtalk.controller;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import tedtalk.controller.ProfileController;
import tedtalk.model.ProfileModel;
public class ProfileControllerTest {
	private ProfileModel modelHandler = new ProfileModel();
	private ProfileController controllerHandler = new ProfileController();
	
	@Before
	public void init() {		
		controllerHandler.setModel(modelHandler);
	}
	@Test
	public void verifiedTest0() {
		System.out.println("initializing test case 0: ");
		modelHandler.setPass("toor");
		modelHandler.setUser("root");
		assertTrue(controllerHandler.verified() == true);
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandler.verified());
	}

	@Test
	public void verifiedTest1() {
		System.out.println("initializing case sensitivity test case: ");
		modelHandler.setPass("Toor");
		modelHandler.setUser("Root");
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	
	@Test
	public void verifiedTest2() {
		System.out.println("initializing empty fields test case: ");
		modelHandler.setPass(" ");
		modelHandler.setUser(" ");
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}

	@Test
	public void verifiedTest3() {
		System.out.println("initializing invalid user test case: ");
		modelHandler.setPass("toor");
		modelHandler.setUser(" ");
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}

	@Test
	public void verifiedTest4() {
		System.out.println("initializing invalid password test case: ");
		modelHandler.setPass(" ");
		modelHandler.setUser("root");
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}

	@Test
	public void verifiedTest5() {
		System.out.println("initializing accidental whitespace test case: ");
		modelHandler.setPass("toor");
		modelHandler.setUser(" root");//SPACE BEFORE " root"
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	//NO @AFTER NEEDED COMPLIMENTS OF JAVA GARBAGE COLLECTION :)
//***************************************************************     6 TEST CASES     *******************************************************************
}
