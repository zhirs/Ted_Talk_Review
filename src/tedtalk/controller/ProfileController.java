package tedtalk.controller;

import tedtalk.model.ProfileModel;
import tedtalkDB.model.Account;
import tedtalkDB.persist.FakeDatabase;

public class ProfileController {
	private ProfileModel model;
	private FakeDatabase fake = new FakeDatabase();
	
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
		model.setRole(login.getRole());
		model.setSection(login.getSection());
		model.setUser(login.getUser());
	}
	
	// adds a user to users list temporarily on the fake database, profID automatically created
	public void userCreation(String user, String pass, String email, String section, int role) {
		fake.createUser(user, pass, email, section, role);
	}
}
