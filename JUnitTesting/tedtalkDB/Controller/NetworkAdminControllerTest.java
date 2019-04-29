package tedtalkDB.Controller;
import tedtalkDB.model.*;
import tedtalkDB.persist.*;

public class NetworkAdminControllerTest {
	private NetworkAdmin NAModel;
	private DerbyDatabase derby = new DerbyDatabase();
	
	//NOTE: JAVA CREATES A DEFAULT CONSTRUCTOR JUST LIKE IT DOES GARBAGE COLLECTION
	
	public void setModel(NetworkAdmin NAModel) {
		this.NAModel = NAModel;
	}
	boolean verified() {
		//USING DERBY'S CHECK CREDIT METHOD TO AUTHENTICATE USER:
		return (derby.checkCredentials(NAModel.getUserName(), NAModel.getPassword()));
	}
	public void createLogin(String user) {
		Account login = derby.setLogin(user);
		NAModel.setEmail(login.getEmail());
		NAModel.setPassword(login.getPassword());
		NAModel.setprofID(login.getprofID());
		NAModel.setUsername(login.getUserName());
		//ENABLE OR DISABLE MODERATING SETTINGS:
//******		NAModel.setModStat(derby.getModStat());//FUNCTIONALITY NOT COMPLETE YET
		
		//CREATES NEW ADMIN IN THE DATABASE:
		derby.addAdmin(NAModel.getUserName(),NAModel.getPassword(),NAModel.getEmail(), 0);
				
	}	
	
	public void verifyReview(Review rev, int approve) {
		if(approve == 1) {//INDICATES ADMIN APPROVED REVIEW
			//TOGGLE STATUS IN DB TO 1
		}
		//ELSE NOT NEEDED STATUS IS LEFT ON DENIED 			
	}
}
