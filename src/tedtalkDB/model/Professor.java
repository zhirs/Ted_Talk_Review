package tedtalkDB.model;

public class Professor extends Account{
	// no new methods yet, creates new professor
	public Professor(String user, String pass, String email, int profID) {
		super(user, pass, email, profID);
	}
	// will soon implement new methods such as verifying reviews

	public Professor() {
		// TODO Auto-generated constructor stub
	}
	
	
	/* possible moderator status may be added to be used to assign specific
	 * admins to become the only admins to verify reviews
	 */
}
