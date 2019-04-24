package tedtalkDB.model;

public class NetworkAdmin extends Account{
	// selects whether mods are off or on
	// this determines whether reviews are reviewed or not
	// set to 0 for not assigned, 1 for off (auto reviewed by system), 2 for on (by mod)
	private int modStat;
  
	public NetworkAdmin(String user, String pass, String email, int profID) {
		super(user, pass, email, profID);
		modStat = 0;
	}
	// will soon implement new methods
	// once such being a method to turn on and off the need to verify reviews
	// will also be able to assign moderators possibly

	public void setModStat(int modStat) {
		this.modStat = modStat;
	}
	
	public int getModStat() {
		return modStat;
	}
	
	// switch status of professors from on to off or off to on
	// professors are all not moderators until promoted by a NetworkAdmin
	public void setMod(Professor prof) {
		if(prof.getMod() == 0) {
			prof.setMod(1);
		}
		else {
			prof.setMod(0);
		}
	}
}
