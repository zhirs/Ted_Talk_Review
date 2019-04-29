package tedtalk.controller;

import java.util.ArrayList;

import tedtalk.model.ProfileModel;
import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;
import tedtalkDB.persist.DerbyDatabase;

public class ProfileController{
	private ProfileModel model;
	private DerbyDatabase derby = new DerbyDatabase();
	
	public ProfileController() {
		
	}
	
	public void setModel(ProfileModel model) {
		this.model = model;
		
	}
	
	// checks login info against fake database
	// calls fake to approve based on userList
	public Boolean verified() {
		return derby.checkCredentials(model.getUser(), model.getPass());
	}
	
	// creates login on fake database, sets model to all attributes of returned user
	public void createLogin(String user) {
		Account login = derby.setLogin(user);
		model.setEmail(login.getEmail());
		model.setPass(login.getPassword());
		model.setProfID(login.getprofID());
		model.setUser(login.getUserName());
		
		// finds whether reviewing reviews is on or off
		model.setModStat(derby.getModStat(0));
		// sets role based on account class
		// also if student sets section
		if(login instanceof NetworkAdmin) {
			model.setRole(2);
		}
		else if(login instanceof Professor){
			model.setRole(1);
			model.setMod(((Professor) login).getMod());
		}
		else {
			model.setSection(((Student) login).getSection());
			model.setRole(0);
		}
			
	}
	
	// adds a user to users list temporarily on the fake database, profID automatically created
	// role = 0 for students,  1 for professors, 2 for networkAdmins
	public ArrayList<Student> studentCreation(String user, String pass, String email, String section, String major) {
		ArrayList<Student> newUser = derby.addStudent(user, pass, email, section, major);
		
		return newUser;
	}
	
	
	//adds a professor to add into the database and the arraylist of Professors
	public ArrayList<Professor> professorCreation(String user, String pass, String email){
		ArrayList<Professor> newProfessor = derby.addProfessor(user, pass, email, 0);
		
		return newProfessor;
	}
	
	//add a network admin to an array list and the database
	public ArrayList<NetworkAdmin> adminCreation(String user, String pass, String email){
		ArrayList<NetworkAdmin> newAdmin = derby.addAdmin(user, pass, email, 0);
		
		return newAdmin;
	}
	
	// allows professors to verify reviews
		public void verifyReview(Review rev, int approve) {
			// checks role based on login
			if(model.getRole() == 1) {
				// checks if mod
				if(model.getMod() == 1) {
					// updates status on review
					rev.setStatus(approve);
				}
			}
		}
}
