package tedtalkDB.model;

public class Student extends Account{
	private String section;
	
	// simple student account, nothing else needed here
	public Student(String user, String pass, String email, String section, int profID) {
		super(user, pass, email, profID);
		this.section = section;
	}
	public Student() {
		
	}
	// will be used to get reviews of all profiles in a given section
	public String getSection() {
		return section;
	}
}
