package tedtalk.controller;

import java.io.IOException;
import java.util.ArrayList;

import tedtalkDB.model.Review;
import tedtalkDB.persist.DerbyDatabase;

public class ReviewController {
	private Review reviewModel;
	private DerbyDatabase derby;
	
	public ReviewController() throws IOException {
		 derby = new DerbyDatabase();
	}

	public void setModel(Review modelHandler) {
		this.reviewModel = modelHandler;
	}
	
	// creates new review, does same thing as database method
	public ArrayList<Review> newReview(String url, String name, int rate, String pres, String desc, int profID, String tag) {
		ArrayList<Review>result = derby.addReview(url, name, rate, pres, desc, profID, tag, 0);
		// if mods are turned off, review is automatically approved and added
		try {
			String[] parsed = result.get(0).getName().split(" ");
			if(parsed.length > 0) {
			derby.addandParse(name, result.get(0).getRevID());
			}
			if(derby.getModStat(profID) == 1) {
				result.get(result.size() - 1).setStatus(1);
			}
		}
		catch (Exception e){
			System.out.println("No review found");
		}
		return result;
	}
	
	// fetches all reviews for a specific person within the database
	// used primarily for printing all reviews by the user within the profile page
	public ArrayList<Review> fetchReviews(int profID){
		ArrayList<Review> result = new ArrayList<Review>();
		result.addAll(derby.getProfIDReviewList(profID, 0));
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
	
	public ArrayList<Review> search(String input) {
		ArrayList<Review> revs = new ArrayList<Review>();
		ArrayList<String> keys = derby.parseTitle(input);
		ArrayList<Integer> revIDS = new ArrayList<Integer>();
		keys.add(input);
		revs.addAll(derby.findReview(input));
		for(int i = 0; i < keys.size(); i++) {
			revIDS.addAll(derby.getRevID(keys.get(i)));
		}
		for(int i = 0; i < revIDS.size(); i++) {
			revs.addAll(derby.getReviews(revIDS.get(i))); 
		}
		return revs;
	}
	public ArrayList<Review> findByTitle(String titles){
		return derby.findReview(titles);		
	}


	
	public ArrayList<Review> getApprovedRevs(int prof_id){
		ArrayList<Review> revs = new ArrayList<Review>();
		revs.addAll(derby.getProfIDReviewList(prof_id, 2));
		return revs;
	}
	public ArrayList<Review> getDeniedRevs(int prof_id){
		ArrayList<Review> revs = new ArrayList<Review>();
		revs.addAll(derby.getProfIDReviewList(prof_id, 1));
		return revs;
	}
	public ArrayList<Review> getPendingRevs(int prof_id){
		ArrayList<Review> revs = new ArrayList<Review>();
		revs.addAll(derby.getProfIDReviewList(prof_id, 0));
		return revs;
	}
	public int getAverageRating(String url) {
		int avrg = derby.averageReviewRating(url);
		return avrg;
	}
}
