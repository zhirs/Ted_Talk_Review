package tedtalkDB.Controller;
import java.util.ArrayList;

import tedtalkDB.model.*;
import tedtalkDB.persist.*;

public class StudentController {
	private Student studentModel;
	private DerbyDatabase derby = new DerbyDatabase();
	
	//NOTE: JAVA CREATES A DEFAULT CONSTRUCTOR JUST AS IT DOES GARBAGE COLLECTION
	public void setModel(Student studentModel) {
		this.studentModel = studentModel;
	}
	boolean verified() { 
		//USING DERBY'S CHECK CREDIT METHOD TO AUTHENTICATE USER:
		return (derby.checkCredentials(studentModel.getUserName(), studentModel.getPassword()));
	}
	public void createLogin(String user) {
		Account login = derby.setLogin(user);
		studentModel.setEmail(login.getEmail());
		studentModel.setPassword(login.getPassword());
		studentModel.setprofID(login.getprofID());
		studentModel.setUsername(login.getUserName());
		
		//CREATES A NEW ACCOUNT BASED ON THE TYPE:				
		derby.addStudent(studentModel.getUserName(),studentModel.getPassword(),studentModel.getEmail(),studentModel.getSection(), studentModel.getMajor());
	}
	public Integer getRevTotal(int profID) {
		return derby.getReviewTotal(profID);
	}
	public ArrayList<String> getMajors(){
		return derby.getMajors();
	}
	public ArrayList<Student> getStudentsbyMajor(String major) {
		return derby.studentsByMajor(major);
	}
}
