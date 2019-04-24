package tedtalks.db;

import java.util.ArrayList;
import java.util.Scanner;

import tedtalkDB.model.Account;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class getProfIDReviewList {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a profile ID to search for ");
		int prof_id= keyboard.nextInt();
		
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Review> found = db.getProfIDReviewList(prof_id);
		if (found.size() == 0){
			System.out.println("No reviews found");
		}
		else {
			for(int i = 0; i < found.size(); i++){
				System.out.println(found.get(i).getDesc() + "Title:" + found.get(i).getName() + "\nTalk by:" + found.get(0).getPres());
			}
			}
		}
}