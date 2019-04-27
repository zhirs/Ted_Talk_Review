package tedtalk.controller;

import java.util.ArrayList;

import tedtalkDB.model.Review;
import tedtalkDB.persist.FakeDatabase;
import tedtalkDB.persist.DerbyDatabase;

public class ReviewController {
	private Review reviewModel;
	private FakeDatabase fake;
	private DerbyDatabase derby;
	public ReviewController() {
		 fake = new FakeDatabase();
		 derby = new DerbyDatabase();
	}

	public void setModel(Review modelHandler) {
		this.reviewModel = modelHandler;
	}
	
	// creates new review, does same thing as database method
	public ArrayList<Review> newReview(String url, String name, int rate, String pres, String desc, int profID, String tag) {
		ArrayList<Review>result = derby.addReview(url, name, rate, pres, desc, profID, tag, 0);
		// if mods are turned off, review is automatically approved and added
		if(fake.getModStat() == 1) {
			result.get(result.size() - 1).setStatus(1);
		}
		return result;
	}
	
	// fetches all reviews for a specific person within the database
	// used primarily for printing all reviews by the user within the profile page
	public ArrayList<Review> fetchReviews(int profID){
		ArrayList<Review> result = new ArrayList<Review>();
		result.addAll(derby.getProfIDReviewList(profID));
		return result;
	}
	
	// verifies if submission is valid to submit for reviewing
	// if req not met, review submission should save data and loop back to create review page
	public boolean verifySubmission(){
		if(reviewModel.getRate() > 0 && reviewModel.getDesc() != null)//INTENTIONAL 
			return true;//SUBMISSION MET REQS
		else
			return false;//SUBMISSION DID NOT MET REQS
	}	
}
