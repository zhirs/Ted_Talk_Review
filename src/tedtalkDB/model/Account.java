package tedtalkDB.model;

public class Account {
	protected String userName; // name displayed on profile
	protected String password; // for logging in
	protected String email; // email of profile
	protected int profID; // iterates starting at 1
	
	public Account() {
		
	}
	
	public Account(String user, String pass, String email, int profID) {
		this.userName = user;
		password = pass;
		this.email = email;
		this.profID = profID;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void changePassword(String pass) {
		password = pass;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getprofID() {
		return profID;
	}
}
