package tedtalks.db;

import java.util.Scanner;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class getStatus {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter review id: ");
		int input = keyboard.nextInt();
		IDatabase db = DatabaseProvider.getInstance();
		int found = db.getStatus(input);
		if (found == -1){
			System.out.println("Load error");
		}
		else {
			System.out.println("Review found");
			System.out.println(found);
			}			
		}
}