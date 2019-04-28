package tedtalkDB.Controller;
import tedtalkDB.model.*;
import tedtalkDB.persist.*;

public class StudentController {
	private Student studentModel;
	private DerbyDatabase derby ;
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
}
