package tedtalk.controller;

import tedtalk.model.ProfileModel;
import tedtalkDB.model.Account;
import tedtalkDB.model.Student;
import tedtalkDB.persist.FakeDatabase;

public class ProfileController {
	private ProfileModel model;
	private FakeDatabase fake = new FakeDatabase();
	
	public ProfileController() {
		
	}
	
	public void setModel(ProfileModel model) {
		this.model = model;
		
	}
	
	// checks login info against fake database
	public Boolean verified() {
		return fake.checkCredentials(model.getUser(), model.getPass());
	}
	
	// creates login on fake database, sets model to all attributes of returned user
	public void createLogin(String user) {
		Account login = fake.setLogin(user);
		model.setEmail(login.getEmail());
		model.setPass(login.getPassword());
		model.setProfID(login.getprofID());
		model.setUser(login.getUserName());
		if(login instanceof Student) {
			model.setSection(((Student) login).getSection());
		}
	}
	
	// adds a user to users list temporarily on the fake database, profID automatically created
	// role = 0 for students,  1 for professors, 2 for networkAdmins
	public void userCreation(String user, String pass, String email, String section, int role) {
		fake.addUser(user, pass, email, section, role);
	}
}
