package tedtalkDB.persist;

import java.io.IOException;
import java.util.ArrayList;

import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;
import tedtalkDB.model.Tags;
import tedtalkDB.model.Account;

public class FakeDatabase implements IDatabase{
	// IMPORTANT: userList and reviewList .get starts at 0, profID and revID starts at 1
	private ArrayList<Account> userList; 
	private ArrayList<Review> reviewList;
	
	// every time called, recreates userList and reviewList
	public FakeDatabase(){
		userList = new ArrayList<Account>();
		reviewList = new ArrayList<Review>();
		
		// Add initial data
		try {
			userList.addAll(InitialData.getAdmins());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			userList.addAll(InitialData.getProfs());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			userList.addAll(InitialData.getStudents());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			reviewList.addAll(InitialData.getReviews());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Account> getUserList(){
		return userList;
	}
	
	public ArrayList<Review> getReviewList(){
		return reviewList;
	}
	
	// checks credentials of login
	// pulls login info from input and compares it against database data
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
	// used to send profile data to profileModel to store session info
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
	public ArrayList<Account> addUser(String user, String pass, String email, String section, int role) {
		Account newUser = null;
		// based on role int, new user is created in database
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
		return userList;
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
	public ArrayList<Review> createReview(String url, String name, int rate, String pres, String desc, int profID, Tags tag) {
		// creates new review with inputed info
		Review rev = new Review();
		// adds new review to list
		reviewList.add(rev);
		// returns the new full reviewList
		return reviewList;
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
	
	// finds all professor accounts
	// used to display on NetworkAdmin profile page
	// so that prof mod status can be switched
	public ArrayList<Professor> findAllProfessors(){
		ArrayList<Professor> professors = new ArrayList<Professor>();
		for(Account use : userList) {
			if(use instanceof Professor) {
				professors.add((Professor) use);
			}
		}
		return professors;
	}
	
	// finds whether reviewing is on or off for entire website 
	public int getModStat() {
		for(Account use : userList) {
			if(use instanceof NetworkAdmin) {
				if(((NetworkAdmin) use).getModStat() != 0) {
					return ((NetworkAdmin) use).getModStat();
				}
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Professor> addProfessor(String user, String pass, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Student> addStudent(String user, String pass, String email, String section) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NetworkAdmin> addAdmin(String user, String pass, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Review> addReview(String URL, String name, int rate, String pres, String desc, int profID,
			String tag, int status) {
		// TODO Auto-generated method stub
		return null;
	}
}
