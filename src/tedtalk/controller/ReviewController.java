package tedtalk.controller;

import java.io.IOException;
import java.util.ArrayList;

import tedtalkDB.model.Review;
import tedtalkDB.model.Tags;
import tedtalkDB.persist.FakeDatabase;

public class ReviewController {
	private Review reviewModel;
	private FakeDatabase fake;
	public ReviewController() throws IOException {
		 fake = new FakeDatabase();
	}

	public void setModel(Review modelHandler) {
		this.reviewModel = modelHandler;
	}
	
	// creates new review, does same thing as database method
	public ArrayList<Review> newReview(String url, String name, int rate, String pres, String desc, int profID, Tags tag) {
		ArrayList<Review>result = fake.createReview(url, name, rate, pres, desc, profID, tag);
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
		result.addAll(fake.getProfIDReviewList(profID));
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
