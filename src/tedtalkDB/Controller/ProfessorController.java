package tedtalkDB.Controller;
import java.util.ArrayList;

import tedtalkDB.model.*;
import tedtalkDB.persist.*;

public class ProfessorController {
	private Professor professorModel;
	private DerbyDatabase derby = new DerbyDatabase();
	
	//NOTE: JAVA CREATES A DEFAULT CONSTRUCTOR JUST AS IT DOES GARBAGE COLLECTION

	public void setModel(Professor professorModel) {
		this.professorModel = professorModel;
	}
	boolean verified() {
		//USING DERBY'S CHECK CREDIT METHOD TO AUTHENTICATE USER:
		return (derby.checkCredentials(professorModel.getUserName(), professorModel.getPassword()));
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
		derby.addProfessor(professorModel.getUserName(),professorModel.getPassword(),professorModel.getEmail(), derby.getMod(professorModel.getMod()));
				
	}
	public void addStudents(String user, String pass, String email, String section, String major) {
		derby.addStudent(user, pass, email, section, major);
	}
	
	public void verifyReview(Review rev, int approve) {
		if(approve == 1) {//INDICATES ADMIN APPROVED REVIEW
			//TOGGLE STATUS IN DB TO 1
		}
		//ELSE NOT NEEDED STATUS IS LEFT ON DENIED 			
	}
	public void removeAccount(String student) {
		// TODO Auto-generated method stub
		derby.removeAccount(student, 1);
	}
	public void addStudent(String user, String pass, String email, String section, String major) {
		derby.addNewStudent(user, pass, email, section, major);
	}
	
	public void approveStudent(String user) {
		derby.approveStudent(user);
	}

	public ArrayList<Review> getReviewQueue(){
		ArrayList<Review> reviews = new ArrayList<Review>();
		System.out.println(professorModel.getMod());
		if((professorModel.getMod() == 1) || (professorModel.getMod()==0)) {
			reviews.addAll(derby.getReviewByStatus());
		}
		else {
			System.out.println("Error you are not a moderator ");
		}
		return reviews;
	}


}
