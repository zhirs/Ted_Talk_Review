package tedtalkDB.model;

public class Username {
	private String user;
	private String password;
	private String email;
	private String section; // set to ADMIN if not student / professor
	private int profID; // is set to 0 if role is not professor
	private int role; // set to 0 for students, 1 for professors, and 2 for superadmins
	
	public void createUser(String user, String pass, String email, String section, int profID, int role) {
		this.user = user;
		password = pass;
		this.email = email;
		this.section = section;
		this.profID = profID;
		this.role = role;
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
	
	public String getEmail() {
		return email;
	}
	
	public String getSection() {
		return section;
	}
	
	public int getprofID() {
		return profID;
	}
	
	public int getRole() {
		return role;
	}
}
