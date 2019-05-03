package tedtalkDB.model;

public class Student extends Account{
	private String section;
	private String major;
	private int studentID;
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
	
	public void setSection(String section) {
		this.section = section;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	public int getStudentID() {
		return studentID;
	}
}
