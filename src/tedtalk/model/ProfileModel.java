package tedtalk.model;

public class ProfileModel {
	private String user;
	private String pass;
	private String email;
	private String section;
	private String role;
	
	private String testUser = "root";
	private String testPass = "toor";
	private String testEmail = "jlandau2";
	private String testSection = "CS320-103";
	private String testRole = "User";
	
	public ProfileModel() {
	}
	
	public String getTestUser() {
		return testUser;
	}
	
	public String getTestPass() {
		return testPass;
	}
	public String getTestEmail() {
		return testEmail;
	}
	public String getTestSection() {
		return testSection;
	}
	public String getTestRole() {
		return testRole;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}