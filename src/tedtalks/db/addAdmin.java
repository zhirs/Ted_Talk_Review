package tedtalks.db;

import java.util.ArrayList;
import java.util.Scanner;

import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class addAdmin {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a username: ");
		String user = keyboard.nextLine();
		System.out.println("Enter a password: ");
		String pass = keyboard.nextLine();
		System.out.println("Enter an email address: ");
		String email = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<NetworkAdmin> found = db.addAdmin(user, pass, email, 0);
		if (found.size() == 0){
			System.out.println("Creation error");
		}
		else {
			System.out.println("Admin Account Made");
			System.out.println(found.get(0).getUserName() + " " + found.get(0).getprofID() + " " + found.get(0).getEmail());
			}
		keyboard.close();
		}
}