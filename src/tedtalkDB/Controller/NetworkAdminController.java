package tedtalkDB.Controller;
import java.util.ArrayList;

import tedtalkDB.model.*;
import tedtalkDB.persist.*;

public class NetworkAdminController {
	private NetworkAdmin NAModel = new NetworkAdmin();
	private DerbyDatabase derby = new DerbyDatabase();
	
	//NOTE: JAVA CREATES A DEFAULT CONSTRUCTOR JUST LIKE IT DOES GARBAGE COLLECTION
	
	public void setModel(NetworkAdmin NAModel) {
		this.NAModel = NAModel; 
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
	
	public void addStudents(String user, String pass, String email, String section, String major) {
		derby.addStudent(user, pass, email, section, major);
	}
	
	public void addProfessors(String user, String pass, String email, int mod) {
		derby.addProfessor(user, pass, email, mod);
	}
	
	public void addNetworkAdmins(String user, String pass, String email, int modStat) {
		derby.addAdmin(user, pass, email, modStat);
	}
	
	public void promoteDemote(boolean promote, String user) {
		int role = derby.getRole(user);
		System.out.println(role);
		
		if(promote == true) {
			switch(role) {
			case 1:
				derby.addToAdmin(user);
				derby.updateRole(user, promote);
				break;
			case 2:
				derby.addToProfessor(user);
				derby.updateRole(user, promote);
			}
		}
		else {			
			switch(role) {
			case 0:
				derby.addToProfessor(user);
				derby.updateRole(user, promote);
				break;
			case 1:
				derby.addToStudent(user);
				derby.updateRole(user, promote);
			}
		}
		role = derby.getRole(user);
		System.out.println(role);
	}
	
	public void removeAccount(String user) {
		derby.removeAccount(user, 0);
	}
	
	public void verifyReview(Review rev, int approve) {
		if(approve == 1) {//INDICATES ADMIN APPROVED REVIEW
			//TOGGLE STATUS IN DB TO 1
		}
		//ELSE NOT NEEDED STATUS IS LEFT ON DENIED 			
	}
	
	public void addStudent(String user, String pass, String email, String section, String major) {
		derby.addNewStudent(user, pass, email, section, major);
	}
	
	public void approveStudent(String user) {
		derby.approveStudent(user);
	}
	public int checkUsername(String user) {
		int result = derby.checkUsername(user);
		return result;
	}
	public ArrayList<Student> loadUnapproveds(){
		ArrayList<Student> rejects = derby.unapprovedStudents();
		return rejects;
	}
	public ArrayList<Student> approveAllStudents(){
		ArrayList<Student> newbs = derby.unapprovedStudents();
		ArrayList<Student> joined = new ArrayList<Student>();
		for(Student stud : newbs) {
			joined.addAll(derby.approveStudent(stud.getUserName()));
		}
		return joined;
	}
	public ArrayList<Student> denyStudent(String user){
		derby.denyStudent(user);
		return derby.unapprovedStudents();
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
