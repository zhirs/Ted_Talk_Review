package tedtalkDB.Controller;
import tedtalkDB.model.*;
import tedtalkDB.persist.*;

public class NetworkAdminController {
	private static NetworkAdmin NAModel = new NetworkAdmin();
	private DerbyDatabase derby = new DerbyDatabase();
	
	//NOTE: JAVA CREATES A DEFAULT CONSTRUCTOR JUST LIKE IT DOES GARBAGE COLLECTION
	
	public static void setModel(NetworkAdmin model) {
		NAModel = model;
	}
	boolean verified() {
		//USING DERBY'S CHECK CREDIT METHOD TO AUTHENTICATE USER:
		int count = 0;
		for(int i = 0; i < 9; i++) {
			if(derby.checkCredentials(NAModel.getUserName(), NAModel.getPassword())) {
				count++;
			};
			
		}
		if (count >= 9) {
			return true;
		}
		else {
			return false;
		}
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
	
	public void switchModStat() {
		if(NAModel.getModStat() == 0 ||NAModel.getModStat() == 1) {
			NAModel.setModStat(1);
		}
		else if(NAModel.getModStat() == 1) {
			NAModel.setModStat(2);
		}
	}
	
	public void newProfessor(String user, String pass, String email) {
		derby.addProfessor(user, pass, email, 0);
	}
	
	public void newNetworkAdmin(String user, String pass, String email) {
		derby.addAdmin(user, pass, email, 0);
	}
	
	public void demote(String user) {
		derby.demoteAccount(user);
	}
	
	public void promote(String user) {
		derby.promoteAccount(user);
	}
}
