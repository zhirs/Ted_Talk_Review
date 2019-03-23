package tedtalkDB.persist;

import java.util.ArrayList;
import java.util.List;
import tedtalkDB.model.Username;

public class InitialData {
	public static List<Username> getUsers(){
		List<Username> user = new ArrayList<Username>();
		
		Username zack = new Username();
		zack.createUser("zhirs", "monkey");
		user.add(zack);
		
		Username darnell = new Username();
		darnell.createUser("dhill22", "banana");
		user.add(darnell);
		
		Username joe = new Username();
		joe.createUser("jlandau2", "tree");
		user.add(joe);
		
		Username adrian = new Username();
		adrian.createUser("acastro7", "forest");
		user.add(adrian);
		
		return user;
	}
}
