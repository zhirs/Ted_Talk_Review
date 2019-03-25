package tedtalkDB.persist;

import java.util.ArrayList;
import java.util.List;
import tedtalkDB.model.Username;

public class InitialData {
	public static List<Username> getUsers(){
		List<Username> user = new ArrayList<Username>();
		// four superadmins created
		Username zack = new Username();
		zack.createUser("zhirs", "monkey", "zhirs@ycp.edu", "ADMIN", 0, 2);
		user.add(zack);
		
		Username darnell = new Username();
		darnell.createUser("dhill22", "banana", "dhill22@ycp.edu", "ADMIN", 0, 2);
		user.add(darnell);
		
		Username joe = new Username();
		joe.createUser("jlandau2", "tree", "jlandau2@ycp.edu", "ADMIN", 0, 2);
		user.add(joe);
		
		Username adrian = new Username();
		adrian.createUser("acastro7", "forest", "acastro7@ycp.edu", "ADMIN", 0, 2);
		user.add(adrian);
		
		// test cases that are not superadmins
		Username professor = new Username();
		professor.createUser("profx", "professorX", "professorX@ycp.edu", "CS260", 1001, 1);
		user.add(professor);
		
		Username student = new Username();
		student.createUser("randp", "random", "randomPerson@ycp.edu", "CS320", 0, 0);
		user.add(student);
		
		return user;
	}
}
