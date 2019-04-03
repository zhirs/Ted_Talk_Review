package tedtalkDB.model;

import java.util.ArrayList;

public interface IDatabase {
	// used for switching between derby and fake
	public boolean checkCredentials(String user, String pass);
	public Account setLogin(String user);
	public void addUser(String user, String pass, String email, String section, int role);
	public ArrayList<Review> getProfIDReviewList(int profID);
	public int getReviewTotal(int profID);
	public ArrayList<Review> findReview(String keyword);
	
}