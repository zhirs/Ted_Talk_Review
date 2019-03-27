package tedtalkDB.model;

public interface IDatabase {
	// used for switching between derby and fake
	public boolean checkCredentials(String user, String pass);
	public Account setLogin(String user);
	public void createUser(String user, String pass, String email, String section, int role);
}