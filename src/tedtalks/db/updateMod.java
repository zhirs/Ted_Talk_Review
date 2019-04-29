package tedtalks.db;

import java.util.Scanner;

import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class updateMod {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter professor id: ");
		int input = keyboard.nextInt();
		IDatabase db = DatabaseProvider.getInstance();
		int found = db.getMod(input);
		if (found == -1){
			System.out.println("Load error");
		}
		else if (found == 1) {
			db.updateMod(input, 0);
			System.out.println("found: " + found + " changing to 0");
		}
		else if (found == 0) {
			db.updateMod(input, 1);
			System.out.println("found: " + found + " changing to 1");
		}
		int check = db.getMod(input);
		System.out.println("Double check : " + check);
	}
}