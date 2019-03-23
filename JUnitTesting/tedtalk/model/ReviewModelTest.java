package tedtalk.model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;//LONG
import tedtalk.model.ReviewModel;
public class ReviewModelTest {
	private ReviewModel tester = new ReviewModel();
//SETTERS TESTING:

	//TEST0 - TEST2: TESTING setReviewRating()
	@Test
	public void test0(){
		System.out.println("initializing test0: ");
		System.out.println("value of reviewRating: " + tester.getReviewRating());
		tester.setReviewRating(5);
		assertEquals(5, tester.getReviewRating());
		System.out.println("expected value: 5");
		System.out.println("your value: " + tester.getReviewRating());
	}
	//
	@Test
	public void test1(){
		System.out.println("initializing test1: ");
		System.out.println("value of reviewRating: " + tester.getReviewRating());
		tester.setReviewRating(2);
		assertEquals(2, tester.getReviewRating());
		System.out.println("expected value: 2");
		System.out.println("your value: " + tester.getReviewRating());
	}
	@Test
	public void test2(){//INVALID RATING TEST CASE
		System.out.println("initializing test2: ");
		System.out.println("value of reviewRating: " + tester.getReviewRating());
		tester.setReviewRating(1);
		tester.setReviewRating(0);//INVALID RATING
		assertEquals(1, tester.getReviewRating());//1 MAINTAIN VALID RATING VALUE
		System.out.println("expected value: 3");
		System.out.println("your value: " + tester.getReviewRating());
	}

	//TEST3 - TEST5: TESTING setRequestID()
	@Test
	public void test3(){
		System.out.println("initializing test3: ");
		System.out.println("value of requestID: " + tester.getRequestID());
		tester.setRequestID(15);
		assertEquals(15, tester.getRequestID());
		System.out.println("expected value: 15");
		System.out.println("your value: " + tester.getRequestID());
	}
	@Test
	public void test4(){
		System.out.println("initializing test4: ");
		System.out.println("value of requestID: " + tester.getRequestID());
		tester.setRequestID(100);
		assertEquals(100, tester.getRequestID());
		System.out.println("expected value: 100");
		System.out.println("your value: " + tester.getRequestID());
	}
	@Test
	public void test5(){
		System.out.println("initializing test5: ");
		System.out.println("value of requestID: " + tester.getRequestID());
		tester.setRequestID(-300);
		assertEquals(-300, tester.getRequestID());
		System.out.println("expected value: -300");
		System.out.println("your value: " + tester.getRequestID());
	}
	//TEST6 - TEST9: TESTING setReviewStatus()
	@Test
	public void test6(){
		System.out.println("initializing test6: ");
		System.out.println("value of status: " + tester.getReviewStatus());
		tester.setReviewStatus("denied");
		assertEquals("denied", tester.getReviewStatus());
		System.out.println("expected status: denied");
		System.out.println("your status: " + tester.getReviewStatus());
	}
	@Test
	public void test7(){
		System.out.println("initializing test7: ");
		System.out.println("value of status: " + tester.getReviewStatus());
		tester.setReviewStatus("approved");
		assertEquals("approved", tester.getReviewStatus());
		System.out.println("expected status: approved");
		System.out.println("your status: " + tester.getReviewStatus());
	}
	@Test
	public void test8(){
		System.out.println("initializing test8: ");
		System.out.println("value of requestID: " + tester.getReviewStatus());
		tester.setReviewStatus("pending");
		assertEquals("pending", tester.getReviewStatus());
		System.out.println("expected status: pending");
		System.out.println("your status: " + tester.getReviewStatus());
	}
	@Test
	public void test9(){//INVALID STATUS TEST CASE
		System.out.println("initializing test9: ");
		System.out.println("value of requestID: " + tester.getReviewStatus());
		tester.setReviewStatus("pending");//SHOULD REMAIN SET
		tester.setReviewStatus("processing");//NOT A VALID STATUS
		assertEquals("pending", tester.getReviewStatus());
		System.out.println("expected status: pending");
		System.out.println("your status: " + tester.getReviewStatus());
	}
	@Test
	public void test10(){//WHITESPACE TEST CASE
		System.out.println("initializing test9: ");
		System.out.println("value of requestID: " + tester.getReviewStatus());
		tester.setReviewStatus("approved");//SHOULD REMAIN SET
		tester.setReviewStatus(" denied");;//NOT A VALID STATUS WHITESPACE
		assertEquals("approved", tester.getReviewStatus());
		System.out.println("expected status: approved");
		System.out.println("your status: " + tester.getReviewStatus());
	}
	/************************************************************ 11 TEST CASES**********************************************************************/

}