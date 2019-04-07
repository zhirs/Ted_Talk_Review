package tedtalk.controller;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
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
		modelHandler.setUser("zhirs");
		modelHandler.setPass("monkey");
		assertTrue(controllerHandler.verified() == true);
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandler.verified());
	}

	@Test
	public void verifiedTest1() {
		System.out.println("initializing test case 1: ");
		modelHandler.setUser("dhill22");
		modelHandler.setPass("banana");
		assertTrue(controllerHandler.verified() == true);
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandler.verified());
	}
	
	@Test
	public void verifiedTest2() {
		System.out.println("initializing test case 2: ");
		modelHandler.setUser("jlandau2");
		modelHandler.setPass("tree");
		assertTrue(controllerHandler.verified() == true);
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandler.verified());
	}

	@Test
	public void verifiedTest3() {
		System.out.println("initializing test case 3: ");
		modelHandler.setUser("acastro7");
		modelHandler.setPass("forest");
		assertTrue(controllerHandler.verified() == true);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}

	@Test
	public void verifiedTest4() {
		System.out.println("initializing invalid password test case: ");
		modelHandler.setUser("Acastro7");
		modelHandler.setPass("Forest");
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}

	@Test
	public void verifiedTest5() {
		System.out.println("initializing accidental whitespace test case: ");
		modelHandler.setUser(" dhill22");//ADDED WHITESPACE
		modelHandler.setPass("banana");
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Test
	public void verifiedTest6() {
		System.out.println("initializing accidental whitespace test case: ");
		modelHandler.setUser("zhirs");
		modelHandler.setPass(" monkey");//ADDED WHITESPACE
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Test
	public void verifiedTest7() {
		System.out.println("initializing missing field test case: ");
		modelHandler.setUser(" ");//MISSING
		modelHandler.setPass(" ");//MISSING
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Test
	public void verifiedTest8() {
		System.out.println("initializing missing field test case: ");
		modelHandler.setUser(" ");//MISSING
		modelHandler.setPass("monkey");//ZACHS PASSWORD
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Test
	public void verifiedTest9() {
		System.out.println("initializing missing field test case: ");
		modelHandler.setUser("jlandau2 ");
		modelHandler.setPass(" ");//MISSING PASSWORD
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Test
	public void verifiedTest10() {
		System.out.println("initializing invalid password test case: ");
		modelHandler.setUser("jlandau2");
		modelHandler.setPass("monkey");//INVALID PASSWORD
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Test
	public void verifiedTest11() { 
		System.out.println("initializing invalid password test case: ");
		modelHandler.setUser("acastro7");
		modelHandler.setPass("tree");//INVALID PASSWORD
		assertTrue(controllerHandler.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandler.verified());
	}
	@Test
	public void createLoginTest() {
		//setup
		controllerHandler.createLogin("zhirs");
		
		assertTrue(modelHandler.getEmail().equals("zhirs@ycp.edu"));
		assertTrue(modelHandler.getPass().equals("monkey"));
		assertTrue(modelHandler.getProfID() == 1);
	}
	//NO @AFTER NEEDED COMPLIMENTS OF JAVA GARBAGE COLLECTION :)
//***************************************************************     6 TEST CASES     *******************************************************************
}
