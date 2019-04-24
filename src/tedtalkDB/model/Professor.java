package tedtalkDB.model;

public class Professor extends Account{
	// moderator int determines who reviews reviews
	// 0 is not a mod, 1 is a mod
	private int mod;
	
	public Professor(String user, String pass, String email, int profID) {
		super(user, pass, email, profID);
		mod = 0;
	}
	// will soon implement new methods such as verifying reviews

	public Professor() {
		// TODO Auto-generated constructor stub
	}
	
	
	/* possible moderator status may be added to be used to assign specific
	 * admins to become the only admins to verify reviews
	 */
	
	// returns mod status
	public int getMod(){
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}
}
