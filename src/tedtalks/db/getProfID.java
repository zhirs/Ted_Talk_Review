package tedtalks.db;

import java.util.ArrayList;
import java.util.Scanner;

import tedtalkDB.model.Review;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class getProfID {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a username to search for ");
		String prof = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		int foundProf= db.getProfID(prof);
		if (foundProf == -1){
			System.out.println("No reviews found");
		}
		else {
			System.out.println(foundProf);
			}
		keyboard.close();
	}
}