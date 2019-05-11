package tedtalkDB.Controller;
import static org.junit.jupiter.api.Assumptions.assumingThat;

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
	
	public int[] loadLeaders(){
		int[] leaders = new int[3];
		ArrayList<Integer> IDS = derby.getStudents();
		int first, second, third;
		first = second = third = 0;
		for(int i = 0; i < IDS.size(); i++) {
			int total = derby.getReviewTotal(IDS.get(i));
			int ID = IDS.get(i);
			System.out.println(ID + " " + total);
			if (total > first) 
			{ 
				third = second; 
				second = first; 
				first = ID; 
			} 
   
			/* If arr[i] is in between first and second then update second  */
			else if (total > second) 
			{ 
				third = second; 
				second = ID; 
			} 
   
			else if (total > third) 
				third = ID; 
		}
		leaders[0] = first;
		System.out.println(first);
		leaders[1] = second;
		System.out.println(second);
		leaders[2] = third;
		System.out.println(third);
		return leaders;
	}
	
	public String getUserName(int profID) {
		return derby.studentByProfID(profID).getUserName();
	}
}
