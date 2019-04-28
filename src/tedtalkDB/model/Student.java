package tedtalkDB.model;

public class Student extends Account{
	private String section;
	private String major;
	// simple student account, nothing else needed here
	public Student(String user, String pass, String email, String section, int profID, String major) {
		super(user, pass, email, profID);
		this.section = section;
		this.major = major;
	}
	public Student() {
		
	}
	// will be used to get reviews of all profiles in a given section
	public String getSection() {
		return section;
	}
	public String getMajor() {
		return major;
	}
}
