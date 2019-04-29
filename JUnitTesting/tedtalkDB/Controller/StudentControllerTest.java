package tedtalkDB.Controller;
import tedtalkDB.model.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
public class StudentControllerTest {
	private	Student modelHandler;
	private StudentController controllerHandler = new StudentController();
	@Before
	public void init() {
		controllerHandler.setModel(modelHandler);
	}
	@Test
	public void verify_Student_Exist_In_Database_Test() {
		//SET STUDENT ATTRIBUTES TO A USER IN THE DATABASE:
		modelHandler.setUsername("dhill22");
		modelHandler.setPassword("banana");
		//TEST CONTROLLER METHOD:
		//derbyHandle.checkCredentials(modelHandler.getUserName(), modelHandler.getPassword())
		assertTrue( controllerHandler.verified());
	}
	

}

