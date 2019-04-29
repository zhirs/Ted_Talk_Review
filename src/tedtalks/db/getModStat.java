package tedtalks.db;

import java.util.ArrayList;
import java.util.Scanner;

import tedtalkDB.model.Account;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class getModStat {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter admin id: ");
		int input = keyboard.nextInt();
		IDatabase db = DatabaseProvider.getInstance();
		int found = db.getModStat(input);
		if (found == -1){
			System.out.println("Load error");
		}
		else {
			System.out.println("ModStat found");
			System.out.println(found);
			}			
		}
}