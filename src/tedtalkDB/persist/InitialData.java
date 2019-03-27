package tedtalkDB.persist;

import java.util.ArrayList;
import java.util.List;
import tedtalkDB.model.Account;

public class InitialData {
	private static int iter;
	public static List<Account> getUsers(){
		List<Account> user = new ArrayList<Account>();
		// four superadmins created
		iter = 0;
		// iter count added to create id tags for each user starting at 1
		Account zack = new Account();
		zack.createUser("zhirs", "monkey", "zhirs@ycp.edu", "ADMIN", ID(), 2);
		user.add(zack);
		
		Account darnell = new Account();
		darnell.createUser("dhill22", "banana", "dhill22@ycp.edu", "ADMIN", ID(), 2);
		user.add(darnell);
		
		Account joe = new Account();
		joe.createUser("jlandau2", "tree", "jlandau2@ycp.edu", "ADMIN", ID(), 2);
		user.add(joe);
		
		Account adrian = new Account();
		adrian.createUser("acastro7", "forest", "acastro7@ycp.edu", "ADMIN", ID(), 2);
		user.add(adrian);
		
		// test cases that are not superadmins
		Account professor = new Account();
		professor.createUser("profx", "professorX", "professorX@ycp.edu", "CS260", ID(), 1);
		user.add(professor);
		
		Account student = new Account();
		student.createUser("randp", "random", "randomPerson@ycp.edu", "CS320", ID(), 0);
		user.add(student);
		
		return user;
	}
	
	public static int ID() {
		iter ++;
		return iter;
	}
}
