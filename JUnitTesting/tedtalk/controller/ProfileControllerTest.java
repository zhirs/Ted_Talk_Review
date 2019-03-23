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
	public void setup0() {//PASSABLE
		System.out.println("initializing test case 0: ");
		controllerHandler.setModel(modelHandler);

	}
	@Test
	public void verifiedTest0() {
		modelHandler.setPass("toor");
		modelHandler.setUser("root");
		assertTrue(controllerHandler.verified() == true);
	}
	@After
	public void verifiedTest0Result() {
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandler.verified());
	}
	
	@Before
	public void setup1() {//CASE SENSITIVITY TEST		
		System.out.println("initializing case sensitivity test case: ");
		controllerHandler.setModel(modelHandler);

	}
	@Test
	public void verifiedTest1() {
		modelHandler.setPass("Toor");
		modelHandler.setUser("Root");
		assertTrue(controllerHandler.verified() == false);
	}
	@After
	public void verifiedTest1Result() {
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	
	@Before
	public void setup2() {//EMPTY SET TEST	
		System.out.println("initializing empty fields test case: ");
		controllerHandler.setModel(modelHandler);

	}
	@Test
	public void verifiedTest2() {
		modelHandler.setPass(" ");
		modelHandler.setUser(" ");
		assertTrue(controllerHandler.verified() == false);
	}
	@After
	public void verifiedTest2Result() {
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Before
	public void setup3() {//VALID PASS INVALID USER	
		System.out.println("initializing invalid user test case: ");
		controllerHandler.setModel(modelHandler);

	}
	@Test
	public void verifiedTest3() {
		modelHandler.setPass("toor");
		modelHandler.setUser(" ");
		assertTrue(controllerHandler.verified() == false);
	}
	@After
	public void verifiedTest3Result() {
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Before
	public void setup4() {//INVALID PASS VALID USER	
		System.out.println("initializing invalid password test case: ");
		controllerHandler.setModel(modelHandler);

	}
	@Test
	public void verifiedTest4() {
		modelHandler.setPass(" ");
		modelHandler.setUser("root");
		assertTrue(controllerHandler.verified() == false);
	}
	@After
	public void verifiedTest4Result() {
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Before
	public void setup5() {//VALID PASS & VALID USER ACCIDENTAL ADDED WHITESPACE
		System.out.println("initializing accidental whitespace test case: ");
		controllerHandler.setModel(modelHandler);

	}
	@Test
	public void verifiedTest5() {
		modelHandler.setPass("toor");
		modelHandler.setUser(" root");//SPACE BEFORE " root"
		assertTrue(controllerHandler.verified() == false);
	}
	@After
	public void verifiedTest5Result() {
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	//***************************************************************     6 TEST CASES     *******************************************************************

}
