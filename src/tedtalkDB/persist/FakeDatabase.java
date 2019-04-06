package tedtalkDB.persist;

import java.util.ArrayList;
import java.util.List;

import tedtalkDB.model.IDatabase;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;
import tedtalkDB.model.Account;

public class FakeDatabase implements IDatabase{
	// IMPORTANT: userList and reviewList .get starts at 0, profID and revID starts at 1
	private ArrayList<Account> userList; 
	private ArrayList<Review> reviewList;
	
	public FakeDatabase() {
		userList = new ArrayList<Account>();
		reviewList = new ArrayList<Review>();
		
		// Add initial data
		userList.addAll(InitialData.getUsers());
		reviewList.addAll(InitialData.getReviews());
	}
	
	public ArrayList<Account> getUserList(){
		return userList;
	}
	
	public ArrayList<Review> getReviewList(){
		return reviewList;
	}
	
	// checks credentials of login
	public boolean checkCredentials(String user, String pass) {
		for(Account use: userList) {
			if(use.getUserName().equals(user)) {
				if(use.getPassword().equals(pass)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// gets account from userList list to return to model based on userName
	public Account setLogin(String user) {
		for(Account use: userList) {
			if(use.getUserName().equals(user)) {
				return use;
			}
		}
		return null;
	}
	
	// adding a new account into the userList (temp)
	// role 0 = student, 1 = professor, 2 = networkAdmin
	public void addUser(String user, String pass, String email, String section, int role) {
		Account newUser = null;
		switch(role) {
		case 1: 
			newUser = new Professor(user, pass, email, userList.size() + 1);
			break;
		case 2:
			newUser = new NetworkAdmin(user, pass, email, userList.size() + 1);
			break;
		default:
			newUser = new Student(user, pass, email, section, userList.size() + 1);
		}
		userList.add(newUser);
	}
	
	// gets all reviews by a certain profile and returns it as an array list
	public ArrayList<Review> getProfIDReviewList(int profID){
		ArrayList<Review> profileReviews = new ArrayList<Review>();
		for(Review rev : reviewList) {
			if(rev.getProfID() == profID) {
				profileReviews.add(rev);
			}
		}
		return profileReviews;
	}
	
	// creates new review
	public void createReview(String name, int rate, String topic, String pres, String desc, int profID, int revID) {
		reviewList.add(new Review(name, rate, topic, pres, desc, profID, reviewList.size()+1));
	}
		
	
	// find total count of reviews by a certain profile
	public int getReviewTotal(int profID) {
		return getProfIDReviewList(profID).size();
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
