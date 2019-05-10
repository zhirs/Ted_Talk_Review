package tedtalkDB.model;

import java.util.ArrayList;

import tedtalkDB.persist.DerbyDatabase;

public class Professor extends Account{
	// moderator int determines who reviews reviews
	// 0 is not a mod, 1 is a mod
	private int mod;
	private int professorID;
	private DerbyDatabase derby;
	public Professor(String user, String pass, String email, int profID) {
		super(user, pass, email, profID);
	}
	// will soon implement new methods such as verifying reviews

	public Professor() {
		// TODO Auto-generated constructor stub
		derby= new DerbyDatabase();
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
	
	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}
	
	public int getProfessorID() {
		return professorID;
	}
}
