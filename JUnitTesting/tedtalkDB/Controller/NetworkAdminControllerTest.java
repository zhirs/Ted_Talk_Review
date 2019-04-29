package tedtalkDB.Controller;
import tedtalkDB.model.*;
import tedtalkDB.persist.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class NetworkAdminControllerTest {
	private NetworkAdminController controllerHandle = new NetworkAdminController();
	private NetworkAdmin NAModel = new NetworkAdmin();
	@Before
	public void init() {
		//ASSOCIATING MODEL WITH CONTROLLER:
		controllerHandle.setModel(NAModel);
	}
	@Test
	public void Test_Verified_Method_On_Existing_User() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("dhill22");
		NAModel.setPassword("banana");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified());
		
	}
	@Test
	public void Test_Verified_Method_On_Nonexisting_User() {
		//SETTING UN & PW TO EXISTING USER CREDITS:
		NAModel.setUsername("_dhill22");
		NAModel.setPassword("nana");
		//TESTING VERIFIED():
		assertTrue(controllerHandle.verified() == false);
	}
}
