package tedtalk.controller;

import tedtalk.model.ReviewModel;

public class ReviewController {
	private ReviewModel reviewModel;
	
public void setModel(ReviewModel model) {
	this.reviewModel = model;
}
public boolean verifySubmission(){
	if(reviewModel.getReviewRating() > 0 && reviewModel.getReviewMessage() != "null")//INTENTIONAL 
		return true;//SUBMISSION MET REQS
	else
		return false;//SUBMISSION DID NOT MET REQS
	}
}
