package tedtalkDB.model;

public class Student extends Account{
	private String section;
	
	public Student(String user, String pass, String email, String section, int profID) {
		super(user, pass, email, profID);
		this.section = section;
	}
	
	public String getSection() {
		return section;
	}
}
