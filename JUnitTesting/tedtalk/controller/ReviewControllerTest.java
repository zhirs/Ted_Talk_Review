package tedtalk.controller;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import tedtalk.controller.ReviewController;
import tedtalk.model.ReviewModel;
import tedtalkDB.model.Review;
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
		modelHandler = fake.getReviewList().get(0);
		reviewController.setModel(modelHandler);
		result = new ArrayList<Review>();
	}
	
	@Test
	public void checkValidReview() {
		assertTrue(reviewController.verifySubmission() == true);
	}
	
	@Test
	public void createNewReview() {
		result = fake.getReviewList();
		System.out.println(result.size());
		String testName = "Wilds";
		int testRate = 3;
		String testTopic = "Environment";
		String testPresenter= "Hamilton";
		String testDescription = "fake description";
		int fakeprofID = 6;
		
		ArrayList<Review>test = reviewController.newReview(testName, testRate, testTopic, testPresenter, testDescription, fakeprofID);
		
		//System.out.println(fake.getReviewList().size());

		
		assertTrue(test.size() == 6);
		//System.out.println(result.get(5).getName());
		assertTrue(test.get(5).getName().equals("Wilds"));
		assertTrue(test.get(5).getProfID() == 6);
	}
}
	/*******************************************************************   2 TEST CASES   ********************************************************************/