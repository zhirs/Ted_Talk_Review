package tedtalks.db;

import java.util.Scanner;

import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class adminByProfID {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a profile ID to search for in admins: \n");
		int profID = keyboard.nextInt();
		IDatabase db = DatabaseProvider.getInstance();
		NetworkAdmin found = db.adminByProfID(profID);
		if (found !=  null){
			System.out.println(found.getUserName());
			 
		}
		else {
			System.out.println("No Students Found");
			}
		keyboard.close();
	}
}