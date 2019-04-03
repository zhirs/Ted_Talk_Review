package tedtalkDB.model;

public class Account {
	protected String userName;
	protected String password;
	protected String email;
	protected int profID; // iterates starting at 1
	
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
