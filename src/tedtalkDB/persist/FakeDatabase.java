package tedtalkDB.persist;

import java.util.ArrayList;
import java.util.List;

import tedtalkDB.model.IDatabase;
import tedtalkDB.model.Review;
import tedtalkDB.model.Account;

public class FakeDatabase implements IDatabase{
	// IMPORTANT: userList and reviewList .get starts at 0, profID and revID starts at 1
	private List<Account> userList; 
	private List<Review> reviewList;
	
	public FakeDatabase() {
		userList = new ArrayList<Account>();
		reviewList = new ArrayList<Review>();
		
		// Add initial data
		userList.addAll(InitialData.getUsers());
		reviewList.addAll(InitialData.getReviews());
		
		System.out.println(userList.size() + " userList");
		System.out.println(reviewList.size() + " reviewList");
	}
	
	// checks credentials of login
	public boolean checkCredentials(String user, String pass) {
		for(Account use: userList) {
			if(use.getUser().equals(user)) {
				if(use.getPassword().equals(pass)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// gets user from userList list to return to model
	public Account setLogin(String user) {
		for(Account use: userList) {
			if(use.getUser() == user) {
				return use;
			}
		}
		return null;
	}
	
	// adding a new user into the userList (temp)
	public void createUser(String user, String pass, String email, String section, int role) {
		Account use = new Account();
		use.createUser(user, pass, email, section, InitialData.ID(), role);
		userList.add(use);
	}
	
	// gets all reviews by a certain profile and returns it as an array list
	public ArrayList<Review> getProfIDreviewList(int profID){
		ArrayList<Review> profileReviews = new ArrayList<Review>();
		for(Review rev : reviewList) {
			if(rev.getProfID() == profID) {
				profileReviews.add(rev);
			}
		}
		return profileReviews;
	}
	
	// find total count of reviews by a certain profile
	public int getReviewTotal(int profID) {
		int count = 0;
		for(Review rev : reviewList) {
			if(rev.getProfID() == profID) {
				count ++;
			}
		}
		return count;
	}
	
	// will be used as general lookup by keyword, topic, name
	public ArrayList<Review> findReview(String keyword) {
		ArrayList<Review> revs = new ArrayList<Review>();
		for(Review review : reviewList){
			if(review.getName().toLowerCase().equals(keyword)) {
				revs.add(review);
			}
		}
		return revs;
	}
}
