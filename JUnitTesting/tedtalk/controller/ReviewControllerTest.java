package tedtalk.controller;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import tedtalk.controller.ReviewController;
import tedtalkDB.model.Review;
import tedtalkDB.persist.DerbyDatabase;

public class ReviewControllerTest {
	private Review modelHandler;
	private ReviewController reviewController;
	private DerbyDatabase derby;
	private ArrayList <Review> result;
	
	@Before
	public void setup0() {
		derby = new DerbyDatabase();
		try {
			reviewController = new ReviewController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// sets model to a review already in database for testing methods
		modelHandler = derby.getProfIDReviewList(20001).get(0);
		reviewController.setModel(modelHandler);
		result = new ArrayList<Review>();
	}
	
	@Test
	public void checkValidReview() {
		// finds if submission is valid to be sent in for review from professor
		assertTrue(reviewController.verifySubmission() == true);
	}
	
	@Test
	public void createNewReview() {
		//result = fake.getReviewList();
		System.out.println(result.size());
		String testName = "Wilds";
		String testURL = "tEDtalk.com/Wilds";
		int testRate = 3;
		String tag = "environmental";
		String testPresenter= "Hamilton";
		String testDescription = "fake description";
		int fakeprofID = 20001;
		ArrayList<Review>InitialSize = reviewController.fetchReviews(fakeprofID);
		int initialSize = InitialSize.size();
		ArrayList<Review>test = reviewController.newReview(testURL, testName, testRate, testPresenter, testDescription, fakeprofID, tag);

		//System.out.println(result.get(5).getName());
		assertTrue(test.get(0).getName().equals("Wilds"));
		assertTrue(test.get(0).getDesc().equals(testDescription));
		assertTrue(test.get(0).getURL().equals(testURL));
		
		ArrayList<Review> doubleCheck = reviewController.fetchReviews(fakeprofID);
		assertTrue(doubleCheck.size() == (initialSize + 1));
	}
}
	/*******************************************************************   2 TEST CASES   ********************************************************************/