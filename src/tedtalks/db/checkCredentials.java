package tedtalks.db;

import java.util.List;
import java.util.Scanner;

import tedtalkDB.model.Account;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class checkCredentials {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a username: ");
		String user = keyboard.nextLine();
		System.out.print("Enter a password: ");
		String pass = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		Boolean found = db.checkCredentials(user, pass);
		if (!found){
			System.out.println("No account found");
		}
		else {
			System.out.println("Account found");
			}			
		}
}