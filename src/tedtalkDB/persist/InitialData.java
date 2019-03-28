package tedtalkDB.persist;

import java.util.ArrayList;
import java.util.List;
import tedtalkDB.model.Account;
import tedtalkDB.model.Review;

public class InitialData {
	private static int iter;
	public static List<Account> getUsers(){
		List<Account> user = new ArrayList<Account>();
		// four superadmins created
		iter = 0;
		// iter count added to create id tags for each user starting at 1
		Account zack = new Account();
		zack.createUser("zhirs", "monkey", "zhirs@ycp.edu", "ADMIN", ID(), 2);
		user.add(zack);
		
		Account darnell = new Account();
		darnell.createUser("dhill22", "banana", "dhill22@ycp.edu", "ADMIN", ID(), 2);
		user.add(darnell);
		
		Account joe = new Account();
		joe.createUser("jlandau2", "tree", "jlandau2@ycp.edu", "ADMIN", ID(), 2);
		user.add(joe);
		
		Account adrian = new Account();
		adrian.createUser("acastro7", "forest", "acastro7@ycp.edu", "ADMIN", ID(), 2);
		user.add(adrian);
		
		// test cases that are not superadmins
		Account professor = new Account();
		professor.createUser("profx", "professorX", "professorX@ycp.edu", "CS260", ID(), 1);
		user.add(professor);
		
		Account student = new Account();
		student.createUser("randp", "random", "randomPerson@ycp.edu", "CS320", ID(), 0);
		user.add(student);
		
		return user;
	}
	
	public static List<Review> getReviews(){
		iter = 0;
		
		List<Review> reviews = new ArrayList<Review>();
		
		// 5 reviews, 2 linked to same account 
		Review talk1 = new Review();
		talk1.createReview("World will be gone by 2020", 3, "Environmental Issues", "ProfessorX", "this will be the large"
				+ " part of the review taking up much room and containing a lot of information", 1, ID());
		reviews.add(talk1);

		Review talk2 = new Review();
		talk2.createReview("a new class drug that could prevent depression and PTSD", 5, "Mental Health", "Rebecca Brachman", ""
				+ "A crazy new drug development that could help cure severe mental issues with those who were in war", 2, ID());
		reviews.add(talk2);

		Review talk3 = new Review();
		talk3.createReview("why we need to clean up our space debris", 1, "Space", "Moriba Jah", ""
				+ "a possible problem that pertains to all living people on earth by space debris blocking out sunlight", 3, ID());
		reviews.add(talk3);

		Review talk4 = new Review();
		talk4.createReview("the beautiful future of solar power", 4, "Environmental Issues", "Marjan van Aubel", ""
				+ "Solar power is inefficient at the current moment in time, nowhere near that of oil, but it is slowly being"
				+ " researched and will one day be more efficient and more compact that it is today", 6, ID());
		reviews.add(talk4);

		Review talk5 = new Review();
		talk5.createReview("How to keep human bias out of AI", 2, "Technology", "Phil Plait", "keeping human bias out of robots"
				+ " will become a huge part of those creating this robots for some reason", 6, ID());
		reviews.add(talk5);
		
		return reviews;
	}
	
	public static int ID() {
		iter ++;
		return iter;
	}
}
