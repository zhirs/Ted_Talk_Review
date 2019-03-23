package tedtalkDB.model;

public class Username {
	private String user;
	private String password;
	
	public void createUser(String user, String pass) {
		this.user = user;
		password = pass;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void changePassword(String pass) {
		password = pass;
	}
}
