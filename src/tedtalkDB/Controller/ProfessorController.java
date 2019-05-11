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
	public ArrayList<Review> getReviewsBetweenDates(String searchName, String year1, String month1, String day1, String year2, String month2, String day2){
		ArrayList<Review> reviewDate = new ArrayList<Review>();
		
		int tempProfID = derby.getProfID(searchName);
		
		String date1 = year1+"/"+month1+"/"+day1;
		//String date1 = "2019/04/26";
		java.util.Date utilDate1 = new java.util.Date(date1);
	    java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
		
		String date2 = year2+"/"+month2+"/"+day2;
	    //String date2 = "2019/04/28";
		java.util.Date utilDate2 = new java.util.Date(date2);
	    java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
		
		return derby.getReviewsBetweenDates(tempProfID, sqlDate1, sqlDate2);
		
	}
	public ArrayList<Review> getReviewByStatus(int status){
		ArrayList<Review> reviewQueue = new ArrayList<Review>();
		reviewQueue.addAll(derby.getReviewByStatus(0));	//0 is the default has not been reviewed
		return reviewQueue;
	}
}
