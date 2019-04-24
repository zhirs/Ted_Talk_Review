package tedtalk.controller;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import tedtalk.controller.ReviewController;
import tedtalkDB.model.Review;
import tedtalkDB.model.Tags;
import tedtalkDB.persist.FakeDatabase;

public class ReviewControllerTest {
	private Review modelHandler;
	private ReviewController reviewController;
	private FakeDatabase fake;
	private ArrayList <Review> result;
	
	@Before
	public void setup0() {
		fake = new FakeDatabase();
		reviewController = new ReviewController();
		// sets model to a review already in database for testing methods
		modelHandler = fake.getReviewList().get(0);
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
		result = fake.getReviewList();
		System.out.println(result.size());
		String testName = "Wilds";
		String testURL = "tEDtalk.com/Wilds";
		int testRate = 3;
		Tags tag = Tags.environmental;
		String testPresenter= "Hamilton";
		String testDescription = "fake description";
		int fakeprofID = 6;
		
		ArrayList<Review>test = reviewController.newReview(testURL, testName, testRate, testPresenter, testDescription, fakeprofID, tag);
		
		//System.out.println(fake.getReviewList().size());

		
		assertTrue(test.size() == 6);
		//System.out.println(result.get(5).getName());
		assertTrue(test.get(5).getName().equals("Wilds"));
		assertTrue(test.get(5).getProfID() == 6);
	}
}
	/*******************************************************************   2 TEST CASES   ********************************************************************/