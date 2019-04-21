package tedtalkDB.persist;

import java.util.ArrayList;
import java.util.List;
import tedtalkDB.model.Account;
import tedtalkDB.model.AccountReview;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;
import tedtalkDB.model.Tags;

public class InitialData {
	private static int iter;
	public static List<Account> getAdmins(){
		List<Account> user = new ArrayList<Account>();
		// four superadmins created
		iter = 0;
		// iter count added to create id tags for each user starting at 1
		NetworkAdmin zack = new NetworkAdmin("zhirs", "monkey", "zhirs@ycp.edu", ID());
		user.add(zack);
		
		NetworkAdmin darnell = new NetworkAdmin("dhill22", "banana", "dhill22@ycp.edu", ID());
		user.add(darnell);
		
		NetworkAdmin joe = new NetworkAdmin("jlandau2", "tree", "jlandau2@ycp.edu", ID());
		user.add(joe);
		
		NetworkAdmin adrian = new NetworkAdmin("acastro7", "forest", "acastro7@ycp.edu", ID());
		user.add(adrian);
		
		// test cases that are not superadmins
		Professor professor = new Professor("profx", "professorX", "professorX@ycp.edu", ID());
		user.add(professor);
		
		Student student = new Student("randp", "random", "randomPerson@ycp.edu", "CS320", ID());
		user.add(student);
		
		return user;
	}
	public static List<Account> getProfs(){
		
	}
	
	public static List<Account> getStudents(){
		
	}
	
	public static List<Review> getReviews(){
		iter = 0;
		
		List<Review> reviews = new ArrayList<Review>();
		
		// 5 reviews, 2 linked to same account 
		Review talk1 = new Review("tedTalk.com/world", "World will be gone by 2020", 3, "ProfessorX", "this will be the large"
				+ " part of the review taking up much room and containing a lot of information", 1, ID(), Tags.environmental);
		reviews.add(talk1);

		Review talk2 = new Review("tedTalk.com/PTSD", "a new class drug that could prevent depression and PTSD", 5, "Rebecca Brachman", ""
				+ "A crazy new drug development that could help cure severe mental issues with those who were in war", 2, ID(), Tags.health);
		reviews.add(talk2);

		Review talk3 = new Review("tedTalk.com/spaceDebris", "why we need to clean up our space debris", 1, "Moriba Jah", ""
				+ "a possible problem that pertains to all living people on earth by space debris blocking out sunlight", 3, ID(), Tags.space);
		reviews.add(talk3);
		
		// these two linked to same student account, one denied, one approved
		Review talk4 = new Review("tedTalk.com/solarPower", "the beautiful future of solar power", 4, "Marjan van Aubel", ""
				+ "Solar power is inefficient at the current moment in time, nowhere near that of oil, but it is slowly being"
				+ " researched and will one day be more efficient and more compact that it is today", 6, ID(), Tags.environmental);
		reviews.add(talk4);
		
		Review talk5 = new Review("tedTalk.com/robotsTakingOver", "How to keep human bias out of AI", 2, "Phil Plait", "keeping human bias out of robots"
				+ " will become a huge part of those creating this robots for some reason", 6, ID(), Tags.technology);
		reviews.add(talk5);
		
		return reviews;
	}
	
	private static int ID() {
		iter ++;
		return iter;
	}
}
