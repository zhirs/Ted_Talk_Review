package tedtalks.db;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tedtalkDB.model.Review;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;


public class getReviewsBetweenDates {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a start date, format yyyy/mm/dd");
		String date1 = keyboard.nextLine();
		if(!isValidDate(date1)) {
			System.out.println("Please enter in yyyy/mm/dd format");
			System.exit(0);
		} 
		java.util.Date utilDate1 = new java.util.Date(date1);
	    java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
	    
	    System.out.println("Enter an end date, format yyyy/mm/dd");
		String date2 = keyboard.nextLine();
		java.util.Date utilDate2 = new java.util.Date(date2);
	    java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
	    
	    System.out.print("Enter prof ID");
		int prof_id= keyboard.nextInt();
		
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Review> found = db.getReviewsBetweenDates(prof_id, sqlDate1, sqlDate2);
		if (found.size() == 0){
			System.out.println("No reviews found");
		}
		else {
			for(int i = 0; i < found.size(); i++){
				System.out.println(found.get(i).getDesc() + "Title:" + found.get(i).getName() + "\nTalk by:" + found.get(0).getPres());
			}
			}
		keyboard.close();
		}
	public static boolean isValidDate(String d) 
	{ 
	    String regex = "^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]"
	                   + "|[12][0-9]|0[1-9])$"; 
	    Pattern pattern = Pattern.compile(regex); 
	    Matcher matcher = pattern.matcher((CharSequence)d); 
	    return matcher.matches(); 
	} 

}