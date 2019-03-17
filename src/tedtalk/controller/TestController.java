package tedtalk.controller;

import tedtalk.model.TestModel;

public class TestController {
	private TestModel model;
	
	public void setModel(TestModel model) {
		this.model = model;
	}
	
	public void loginLoad() {
		model.setPlacePass("toor");
		model.setPlaceUser("root");
	}
	
	public void testLogin() {
		model.verifiedCred();
	}
}
