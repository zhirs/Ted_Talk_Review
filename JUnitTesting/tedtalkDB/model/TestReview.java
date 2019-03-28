package tedtalkDB.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

import tedtalkDB.model.*;
import tedtalkDB.persist.InitialData;

public class TestReview {
	private List<Review> reviews;
	
	@Before
	public void setup() {
		reviews = new ArrayList<Review>();
		
		// Add initial data
		reviews.addAll(InitialData.getReviews());
	}
	
	@Test
	public void testGetName() {
		assertTrue(reviews.get(0).getName() == "World will be gone by 2020");
		assertTrue(reviews.get(1).getName() == "a new class drug that could prevent depression and PTSD");
		assertTrue(reviews.get(2).getName() == "why we need to clean up our space debris");
		assertTrue(reviews.get(3).getName() == "the beautiful future of solar power");
		assertTrue(reviews.get(4).getName() == "How to keep human bias out of AI");
	}
	
	@Test
	public void testGetPres() {
		assertTrue(reviews.get(0).getPres() == "ProfessorX");
		assertTrue(reviews.get(1).getPres() == "Rebecca Brachman");
		assertTrue(reviews.get(2).getPres() == "Moriba Jah");
		assertTrue(reviews.get(3).getPres() == "Marjan van Aubel");
		assertTrue(reviews.get(4).getPres() == "Phil Plait");
	}
	
	@Test
	public void testGetProfID() {
		assertTrue(reviews.get(0).getProfID() == 1);
		assertTrue(reviews.get(1).getProfID() == 2);
		assertTrue(reviews.get(2).getProfID() == 3);
		assertTrue(reviews.get(3).getProfID() == 6);
		assertTrue(reviews.get(4).getProfID() == 6);
	}
	
	@Test
	public void testGetRate() {
		assertTrue(reviews.get(0).getRate() == 3);
		assertTrue(reviews.get(1).getRate() == 5);
		assertTrue(reviews.get(2).getRate() == 1);
		assertTrue(reviews.get(3).getRate() == 4);
		assertTrue(reviews.get(4).getRate() == 2);
	}
	
	@Test
	public void testGetRevID() {
		assertTrue(reviews.get(0).getrevID() == 1);
		assertTrue(reviews.get(1).getrevID() == 2);
		assertTrue(reviews.get(2).getrevID() == 3);
		assertTrue(reviews.get(3).getrevID() == 4);
		assertTrue(reviews.get(4).getrevID() == 5);
	}
	
	@Test
	public void testGetDesc() {
		assertTrue(reviews.get(0).getDesc() == "this will be the large part of the review taking up much room and containing a lot of information");
		assertTrue(reviews.get(1).getDesc() == "A crazy new drug development that could help cure severe mental issues with those who were in war");
		assertTrue(reviews.get(2).getDesc() == "a possible problem that pertains to all living people on earth by space debris blocking out sunlight");
		assertTrue(reviews.get(3).getDesc() == "Solar power is inefficient at the current moment in time, nowhere near that of oil, but it is slowly being researched and will one day be more efficient and more compact that it is today");
		assertTrue(reviews.get(4).getDesc() == "keeping human bias out of robots will become a huge part of those creating this robots for some reason");
	}
	
	@Test
	public void testUpdateDesc() {
		String rev = reviews.get(0).getDesc();
		reviews.get(0).updateDesc("this is an updated description");
		
		assertTrue(rev != reviews.get(0).getDesc());
	}
}
