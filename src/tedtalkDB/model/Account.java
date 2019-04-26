package tedtalkDB.model;

public class Account {
	private String userName; // name displayed on profile
	private String password; // for logging in
	private String email; // email of profile
	private int profID; // iterates starting at 1

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
	
	public void setUsername(String username) {
		this.userName = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void changePassword(String pass) {
		password = pass;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String em) {
		this.email = em;
	}
	
	public int getprofID() {
		return profID;
	}
	public void setprofID(int prof_id) {
		this.profID = prof_id;
	}
}
