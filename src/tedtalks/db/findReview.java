package tedtalks.db;

import java.util.ArrayList;
import java.util.Scanner;

import tedtalkDB.model.Account;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class findReview {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a review title: ");
		String keyword= keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Review> found = db.findReview(keyword);
		if (found.size() == 0){
			System.out.println("Load error");
		}
		else {
			System.out.println("Review found");
			System.out.println(found.get(0).getName() + "\nWritten by:" + found.get(0).getProfID() + "  \nTalk by:" + found.get(0).getPres());
			}			
		}
}