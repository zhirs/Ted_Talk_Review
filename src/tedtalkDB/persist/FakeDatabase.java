package tedtalkDB.persist;

import java.util.ArrayList;
import java.util.List;

import tedtalkDB.model.IDatabase;
import tedtalkDB.model.Account;

public class FakeDatabase implements IDatabase{
	private List<Account> users;
	
	public FakeDatabase() {
		users = new ArrayList<Account>();
		
		// Add initial data
		users.addAll(InitialData.getUsers());
		
		System.out.println(users.size() + " users");
	}
	
	// checks credentials of login
	public boolean checkCredentials(String user, String pass) {
		for(Account use: users) {
			if(use.getUser().equals(user)) {
				if(use.getPassword().equals(pass)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// gets user from users list to return to model
	public Account setLogin(String user) {
		for(Account use: users) {
			if(use.getUser() == user) {
				return use;
			}
		}
		return null;
	}
	
	// adding a new user into the users list (temp)
	public void createUser(String user, String pass, String email, String section, int role) {
		Account use = new Account();
		use.createUser(user, pass, email, section, InitialData.ID(), role);
		users.add(use);
	}
}
