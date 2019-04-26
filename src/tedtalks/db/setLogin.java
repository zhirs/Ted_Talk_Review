package tedtalks.db;

import java.util.Scanner;

import tedtalkDB.model.Account;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class setLogin {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a username: ");
		String user = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		Account found = db.setLogin(user);
		if (found == null){
			System.out.println("account error");
		}
		else {
			System.out.println("Account set");
			System.out.println(found.getUserName() + " " + found.getprofID() + " " + found.getEmail());
			}			
		}
}