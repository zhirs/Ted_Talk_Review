package tedtalk.controller;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


import tedtalk.controller.ReviewController;
import tedtalk.model.ReviewModel;
import tedtalkDB.model.Review;
import tedtalkDB.persist.FakeDatabase;
public class ReviewControllerTest {
	private Review modelHandler;
	private ReviewController reviewController;
	private FakeDatabase fake;
	
	@Before
	public void setup0() {
		fake = new FakeDatabase();
		reviewController = new ReviewController();
		modelHandler = fake.getReviewList().get(0);
		reviewController.setModel(modelHandler);
	}
	
	@Test
	public void checkValidReview() {
		assertTrue(reviewController.verifySubmission() == true);
	}
	
	@Test
	public void createNewReview() {
		System.out.println(fake.getReviewList().size());
		reviewController.newReview("Wilds", 3, "Environment", "Hamilton", "fake description", 6);
		System.out.println(fake.getReviewList().size());
		
		assertTrue(fake.getReviewList().size() == 5);
		System.out.println(fake.getReviewList().get(4).getName());
		assertTrue(fake.getReviewList().get(4).getName().equals("Wilds"));
		assertTrue(fake.getReviewList().get(4).getProfID() == 6);
	}
}
	/*******************************************************************   2 TEST CASES   ********************************************************************/