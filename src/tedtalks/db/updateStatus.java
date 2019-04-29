package tedtalks.db;

import java.util.Scanner;

import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class updateStatus {
	public static void main(String[] args) throws Exception {
		 
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter review id: ");
		int revID = keyboard.nextInt();
		keyboard.nextLine();
		System.out.print("Enter new status");
		int status = keyboard.nextInt();
		IDatabase db = DatabaseProvider.getInstance();
		int initialStatus = db.getStatus(revID);
		int found = db.updateStatus(revID, status);
		if (found == -1){
			System.out.println("Load error");
		}
		else if (found == 1) {
			int newStatus = db.getStatus(revID);
			System.out.println("found: " + initialStatus + " changed to " + newStatus);
		}
		keyboard.close();
	}
}