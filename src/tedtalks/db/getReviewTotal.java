package tedtalks.db;

import java.util.Scanner;

import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class getReviewTotal {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a profile ID to search for ");
		int prof_id= keyboard.nextInt();
		
		IDatabase db = DatabaseProvider.getInstance();
		Integer found = db.getReviewTotal(prof_id);
		if (found == -1){
			System.out.println("No reviews found");
		}
		else {
			System.out.println(found);
			}
		keyboard.close();
		}
}