package tedtalks.db;

import java.util.Scanner;

import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class updateSection {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter profile ID: ");
		int profID = keyboard.nextInt();
		keyboard.nextLine();
		System.out.print("Enter new section");
		String section = keyboard.nextLine();
		IDatabase db = DatabaseProvider.getInstance();
		String initialStatus = db.getSection(profID);
		int found = db.updateSection(profID, section);
		if (found == -1){
			System.out.println("Load error");
		}
		else if (found == 1) {
			String newStatus = db.getSection(profID);
			if(newStatus == null) {
				System.out.println("Section load error");
			}
			else {
				System.out.println("found: " + initialStatus + " changed to " + newStatus);
			}
		}
		keyboard.close();
	}
}