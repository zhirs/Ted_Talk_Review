package tedtalkDB.persist;

import java.util.ArrayList;
import java.util.List;

import tedtalkDB.model.IDatabase;
import tedtalkDB.model.Username;

public class FakeDatabase implements IDatabase{
	private List<Username> users;
	
	public FakeDatabase() {
		users = new ArrayList<Username>();
		
		// Add initial data
		users.addAll(InitialData.getUsers());
		
		System.out.println(users.size() + " users");
	}
	
	public boolean checkCredentials(String user, String pass) {
		for(Username use: users) {
			if(use.getUser().equals(user)) {
				if(use.getPassword().equals(pass)) {
					return true;
				}
			}
		}
		return false;
	}
}
