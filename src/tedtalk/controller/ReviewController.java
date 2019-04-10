package tedtalk.controller;

import java.util.ArrayList;

import tedtalk.model.ReviewModel;
import tedtalkDB.model.Review;
import tedtalkDB.persist.FakeDatabase;

public class ReviewController {
	private Review reviewModel;
	private FakeDatabase fake;
	public ReviewController() {
		 fake = new FakeDatabase();
	}

	public void setModel(Review modelHandler) {
		this.reviewModel = modelHandler;
	}
	
	// creates new review, does same thing as database method
	public ArrayList<Review> newReview(String name, int rate, String topic, String pres, String desc, int profID) {
		ArrayList<Review>result = fake.createReview(name, rate, topic, pres, desc, profID);
		
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
