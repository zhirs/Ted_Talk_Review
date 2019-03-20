package tedtalk.controller;

import tedtalk.model.ProfileModel;

public class ProfileController {
	private ProfileModel model;
	
	public void setModel(ProfileModel model) {
		this.model = model;
	}
	
	public Boolean verified() {
		return model.getTestPass().equals(model.getPass()) && model.getTestUser().equals(model.getUser());
	}
}
