package tedtalk.model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;//LONG
import tedtalk.model.ReviewModel;
public class ReviewModelTest {
	private ReviewModel tester = new ReviewModel();
//SETTERS TESTING:

	//TEST0 - TEST2: TESTING setRate()
	@Test
	public void test0(){
		System.out.println("initializing test0: ");
		System.out.println("value of reviewRating: " + tester.getReviewRating());
		tester.setRate(5);
		assertEquals(5, tester.getReviewRating());
		System.out.println("expected value: 5");
		System.out.println("your value: " + tester.getReviewRating());
	}
	//
	@Test
	public void test1(){
		System.out.println("initializing test1: ");
		System.out.println("value of reviewRating: " + tester.getReviewRating());
		tester.setRate(2);
		assertEquals(2, tester.getReviewRating());
		System.out.println("expected value: 2");
		System.out.println("your value: " + tester.getReviewRating());
	}
	@Test
	public void test2(){//INVALID RATING TEST CASE
		System.out.println("initializing test2: ");
		System.out.println("value of reviewRating: " + tester.getReviewRating());
		tester.setRate(1);
		tester.setRate(0);//INVALID RATING
		assertEquals(1, tester.getReviewRating());//1 MAINTAIN VALID RATING VALUE
		System.out.println("expected value: 3");
		System.out.println("your value: " + tester.getReviewRating());
	}
}