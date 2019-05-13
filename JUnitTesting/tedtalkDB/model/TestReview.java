package tedtalkDB.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tedtalkDB.persist.DerbyDatabase;

public class TestReview {
	private List<Review> reviews1;
	private List<Review> reviews2;
	private DerbyDatabase derby;
	
	@Before
	public void setup() {
		reviews1 = new ArrayList<Review>();	//an array list of Reviews for each profile
		reviews2 = new ArrayList<Review>();	//a new array list of Reviews for a different profile
		derby = new DerbyDatabase();
		
		// Add initial data
		reviews1.addAll(derby.getProfIDReviewList(8, 0));
		reviews2.addAll(derby.getProfIDReviewList(9, 0));
	}
	
	@Test
	public void testGetName() {
		assertTrue(reviews1.get(0).getName().equals("Endgame spoilers suck"));
		assertTrue(reviews2.get(0).getName().equals("Darnell Hill smells funny"));
	}
	
	@Test
	public void testGetPres() {
		assertTrue(reviews1.get(0).getPres().equals("Stan Lee"));
		assertTrue(reviews2.get(0).getPres().equals("Prof Hake"));
		assertTrue(reviews1.get(0).getRate() == 1);
		assertTrue(reviews2.get(0).getRate() == 2);
	}
	
	@Test
	public void testGetRevID() {
		assertTrue(reviews1.get(0).getRevID() == 4);
		assertTrue(reviews2.get(0).getRevID() == 2);
	}
	
	@Test
	public void testGetDesc() {
		assertTrue(reviews1.get(0).getDesc().equals("Seriously endgame spoilers make me mad"));
		assertTrue(reviews2.get(0).getDesc().equals("Please don't flunk me"));
	}
	
	@Test
	public void testUpdateDesc() {
		String rev = reviews1.get(0).getDesc();
		reviews1.get(0).setDesc("this is an updated description");
		
		assertTrue(rev != reviews1.get(0).getDesc());
		reviews1.get(0).setDesc(rev);
	}
}
