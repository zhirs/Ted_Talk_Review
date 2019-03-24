package tedtalk.controller;

import tedtalk.model.ProfileModel;
import tedtalkDB.persist.FakeDatabase;

public class ProfileController {
	private ProfileModel model;
	private FakeDatabase fake = new FakeDatabase();
	
	public void setModel(ProfileModel model) {
		this.model = model;
		
	}
	
	public Boolean verified() {
		return fake.checkCredentials(model.getUser(), model.getPass());
	}
}
