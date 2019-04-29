package tedtalkDB.Controller;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import tedtalkDB.model.NetworkAdmin;

public class NetworkAdminControllerTest {
	private NetworkAdminController controllerHandle = new NetworkAdminController();
	private NetworkAdmin NAModel = new NetworkAdmin();
	@Before
	public void init() {
		//ASSOCIATING MODEL WITH CONTROLLER:
		controllerHandle.setModel(NAModel);
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
		assertTrue(controllerHandle.verified());
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Existing_User2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("zhirs");
		NAModel.setPassword("monkey");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Existing_User3() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("acastro7");
		NAModel.setPassword("wizard");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
		System.out.println("expected result: true");
		System.out.println("your result: " + controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("dhill22");
		NAModel.setPassword("Monkey");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("acastro7");
		NAModel.setPassword("Monkey");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Invalid_PW2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("jlandau2");
		NAModel.setPassword("wizard");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("Jlandau2");
		NAModel.setPassword("Tree");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("Dhill22");
		NAModel.setPassword("banana");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Existing_User_Case_Sensitivity2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("acastrO7");
		NAModel.setPassword("Wizard");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Nonexisting_User0() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("_dhill22");
		NAModel.setPassword("nana");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Nonexisting_User1() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("jlandau20");
		NAModel.setPassword("tree");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	@Test
	public void Test_Verified_Nonexisting_User2() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("jlandau20");
		NAModel.setPassword("tree");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
		System.out.println("expected result: false");
		System.out.println("your result: " + controllerHandle.verified());
	}
	//***************************************************************     13 TEST CASES     *******************************************************************


}
