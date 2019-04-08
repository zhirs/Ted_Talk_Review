package tedtalk.controller;

import java.util.ArrayList;

import tedtalk.model.ReviewModel;
import tedtalkDB.model.Review;
import tedtalkDB.persist.FakeDatabase;

public class ReviewController {
	private Review reviewModel;
	private FakeDatabase fake = new FakeDatabase();
	public ReviewController() {
		
	}

	public void setModel(Review modelHandler) {
		this.reviewModel = modelHandler;
	}
	public void newReview(String name, int rate, String topic, String pres, String desc, int profID) {
		fake.createReview(name, rate, topic, pres, desc, profID);
	}
	
	public ArrayList<Review> fetchReviews(int profID){
		ArrayList<Review> result = new ArrayList<Review>();
		result.addAll(fake.getProfIDReviewList(profID));
		return result;
	}
	
	public boolean verifySubmission(){
		if(reviewModel.getRate() > 0 && reviewModel.getDesc() != null)//INTENTIONAL 
			return true;//SUBMISSION MET REQS
		else
			return false;//SUBMISSION DID NOT MET REQS
	}	
}
