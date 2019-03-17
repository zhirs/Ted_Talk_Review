package tedtalk.model;

public class TestModel {
	private String placeUser;
	private String placePass;
	private String testUser;
	private String testPass;
	
	public TestModel() {
	}
	public void setTestUser(String testUser) {
		this.testUser = testUser;
	}
	public String getTestUser() {
		return testUser;
	}
	public void setTestPass(String testPass) {
		this.testPass = testPass;
	}
	public String getTestPass() {
		return testPass;
	}
	public void setPlaceUser(String placeUser) {
		this.placeUser = placeUser;
	}
	public String getPlaceUser(){
		return placeUser;
	}
	public void setPlacePass(String placePass) {
		this.placePass = placePass;
	}
	public String getPlacePass() {
		return placePass;
	}
	public boolean verifiedCred() {
		return (testPass == placePass && testUser == placeUser);
	}

}