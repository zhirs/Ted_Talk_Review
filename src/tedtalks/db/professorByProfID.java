package tedtalks.db;

import java.util.Scanner;

import tedtalkDB.model.Professor;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class professorByProfID {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a profile ID to search for in professors: \n");
		int profID = keyboard.nextInt();
		IDatabase db = DatabaseProvider.getInstance();
		Professor found = db.professorByProfID(profID);
		if (found !=  null){
			System.out.println(found.getUserName());
			
		}
		else {
			System.out.println("No professors Found");
			}
		keyboard.close();
	}
}