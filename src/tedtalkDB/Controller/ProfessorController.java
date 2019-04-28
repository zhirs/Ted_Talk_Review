package tedtalkDB.Controller;
import tedtalkDB.model.*;
import tedtalkDB.persist.*;

public class ProfessorController {
	private Professor professorModel;
	private DerbyDatabase derby = new DerbyDatabase();
	
	//NOTE: JAVA CREATES A DEFAULT CONSTRUCTOR JUST AS IT DOES GARBAGE COLLECTION

	public void setModel(Professor professorModel) {
		this.professorModel = professorModel;
	}
	public void createLogin(String user) {
		Account login = derby.setLogin(user);
		professorModel.setEmail(login.getEmail());
		professorModel.setPassword(login.getPassword());
		professorModel.setprofID(login.getprofID());
		professorModel.setUsername(login.getUserName());		
		//ENABLE OR DISABLE MODERATING SETTINGS:
	//******	professorModel.setModStat(derby.getModStat());//FUNCTIONALITY NOT COMPLETE YET
		
		//CREATES NEW ADMIN IN THE DATABASE:
		derby.addProfessor(professorModel.getUserName(),professorModel.getPassword(),professorModel.getEmail());
				
	}	
	
	public void verifyReview(Review rev, int approve) {
		if(approve == 1) {//INDICATES ADMIN APPROVED REVIEW
			//TOGGLE STATUS IN DB TO 1
		}
		//ELSE NOT NEEDED STATUS IS LEFT ON DENIED 			
	}

}
