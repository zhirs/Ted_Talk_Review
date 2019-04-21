package tedtalks.db;

import java.util.ArrayList;
import java.util.Scanner;

import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class addReview {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("URL: ");
		String URL = keyboard.nextLine();
		System.out.println("Title: ");
		String name = keyboard.nextLine();
		System.out.println("Rate: ");
		int rate = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Pres: ");
		String pres = keyboard.nextLine();
		System.out.println("Description: ");
		String desc = keyboard.nextLine();
		System.out.println("ProfID: ");
		int prof_id = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Tag: ");
		String tag = keyboard.nextLine();
		System.out.println("Status: ");
		int status = keyboard.nextInt();
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Review> found = db.addReview(URL, name, rate, pres, desc, prof_id, tag, status);
		if (found.size() == 0){
			System.out.println("Creation error");
		}
		else {
			System.out.println("Review Made");
			System.out.println(found.get(0).getURL() + " " + found.get(0).getName()+ " " + found.get(0).getRate() + " " + found.get(0).getPres() + " " + found.get(0).getDesc() + " " + found.get(0).getProfID());
			}			
		}
}