package tedtalk.model;

public class ProfileModel {
	private String user;
	private String pass;
	private String email;
	private String section;
	private int role;
	private int profID;
	private int mod;
	private int modStat;
	
	public ProfileModel() {
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
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getProfID() {
		return profID;
	}
	public void setProfID(int profID) {
		this.profID = profID;
	}
	public int getMod() {
		return mod;
	}
	public void setMod(int mod) {
		this.mod = mod;
	}
	public int getModStat() {
		return modStat;
	}
	public void setModStat(int modStat) {
		this.modStat = modStat;
	}
}