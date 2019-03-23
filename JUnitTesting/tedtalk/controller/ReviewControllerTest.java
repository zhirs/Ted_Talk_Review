package tedtalk.controller;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


import tedtalk.controller.ReviewController;
import tedtalk.model.ReviewModel;
public class ReviewControllerTest {
	private ReviewModel modelHandler =  new ReviewModel();
	private ReviewController reviewController = new ReviewController();
	
	/* NOTE THE SET RATING METHOD WITHIN THE REVIEWMODEL CASE    * 
	 * ENSURE THE RATING IS BETWEEN 1 AND 5 OTHERWISE THE RATING *
	 * DEFAULTS TO 1 STAR; THEREFORE THERE IS NO TESTING DONE ON *
	 * THE RATING PORTION OF THE VERIFYSUBMISSION() IN THIS FILE */
	
	@Before
	public void setup0() {//NULL CASE
		System.out.println("initializing invalid message test case: ");
		reviewController.setModel(modelHandler);
	}
	@Test
	public void test0() {
		modelHandler.setReviewRating(5);
		modelHandler.setReviewMessage(null);//NULL REVIEW MESSAGE
		assertTrue(reviewController.verifySubmission() == true);

	}
	@After
	public void test0Result() {
	System.out.println("expected: false\nyour result:" + reviewController.verifySubmission());
}
	
	@Before
	public void setup1() {//MESSAGE NOT SET
		System.out.println("initializing invalid message test case2: ");
		reviewController.setModel(modelHandler);
	}
	@Test
	public void test1() {
		modelHandler.setReviewRating(5);
		//FAILURE TO SET THE REVIEWMESSAGE RESULTS IN THE INITIALIZED VALUE OF "null"
		assertTrue(reviewController.verifySubmission() == false);

	}
	@After
	public void test1Result() {
	System.out.println("expected: false\nyour result:" + reviewController.verifySubmission());
}
	/*******************************************************************   2 TEST CASES   ********************************************************************/

}
