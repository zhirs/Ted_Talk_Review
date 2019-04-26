package tedtalkDB.persist;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tedtalkDB.model.Account;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;
import tedtalkDB.model.Tags;
import tedtalkDB.persist.*;

public class TestFakeDatabase {
	private FakeDatabase test = new FakeDatabase();
	
	@Test
	public void credCheckZack() {
		assertTrue(test.checkCredentials("zhirs", "monkey") == true);
		assertFalse(test.checkCredentials("zhirs", "tree") == true);
		assertFalse(test.checkCredentials("zhirs", "cow") == true);
	}
	
	@Test
	public void credCheckDarnell() {
		assertTrue(test.checkCredentials("dhill22", "banana") == true);
		assertFalse(test.checkCredentials("dhill22", "tree") == true);
		assertFalse(test.checkCredentials("dhill22", "cow") == true);
	}
	
	@Test
	public void credCheckJoe() {
		assertTrue(test.checkCredentials("jlandau2", "tree") == true);
		assertFalse(test.checkCredentials("jlandau2", "monkey") == true);
		assertFalse(test.checkCredentials("jlandau2", "cow") == true);
	}
	
	@Test
	public void credCheckAdrian() {
		assertTrue(test.checkCredentials("acastro7", "forest") == true);
		assertFalse(test.checkCredentials("acastro7", "tree") == true);
		assertFalse(test.checkCredentials("acastro7", "cow") == true);
	}
	
	@Test
	public void credCheckOther() {
		assertTrue(test.checkCredentials("profx", "professorX") == true);
		assertFalse(test.checkCredentials("profx", "tree") == true);
		assertFalse(test.checkCredentials("profx", "cow") == true);
		
		assertTrue(test.checkCredentials("randp", "random") == true);
		assertFalse(test.checkCredentials("randp", "tree") == true);
		assertFalse(test.checkCredentials("randp", "cow") == true);
	}
	
	@Test
	public void setLoginCheck() {
		assertTrue(test.setLogin("zhirs").getEmail().equals("zhirs@ycp.edu"));
		assertTrue(test.setLogin("randp").getUserName().equals("randp"));
		assertTrue(test.setLogin("profx").getprofID() == 5);
		assertTrue(test.setLogin("jlandau2").getPassword().equals("tree"));
	}

	@Test
	public void addNewUserTest() {
		test.addUser("RandomStudent", "unknown", "student@ycp.edu", "CS100", 0);
		
		assertTrue(test.getUserList().size() == 8);
		assertTrue(test.getUserList().get(test.getUserList().size() - 1).getUserName().equals("RandomStudent"));
		assertTrue(test.getUserList().get(test.getUserList().size() - 1).getPassword().equals("unknown"));
		assertTrue(test.getUserList().get(test.getUserList().size() - 1).getEmail().equals("student@ycp.edu"));
		assertTrue(((Student) test.getUserList().get(test.getUserList().size() - 1)).getSection().equals("CS100"));
		assertTrue(test.getUserList().get(test.getUserList().size() - 1).getprofID() == 8);
	}
	
	@Test
	public void getProfIDReviewListTest() {
		ArrayList<Review> prof1 = new ArrayList<Review>();
		ArrayList<Review> prof2 = new ArrayList<Review>();
		
		prof1 = test.getProfIDReviewList(test.getUserList().get(0).getprofID());
		prof2 = test.getProfIDReviewList(test.getUserList().get(5).getprofID());
		
		assertTrue(prof1.size() == 1);
		assertTrue(prof2.size() == 2);
		
		assertTrue(prof1.get(0).getRate() == 3);
		assertTrue(prof2.get(1).getTag() == Tags.technology);
	}
	
	@Test
	public void getReviewTotalTest() {
		assertTrue(test.getReviewTotal(1) == 1);
		assertTrue(test.getReviewTotal(test.getUserList().size() - 1) == 2);
	}
	
	@Test
	public void createReviewTest() {
		int oldSize = test.getReviewList().size();
		test.createReview("tEDTalk.com/Wilds", "Wilds", 3, "Hamilton", "fake description", 6, Tags.environmental);
		assertTrue(test.getReviewList().size() > oldSize);
		assertTrue(test.getReviewList().get(oldSize).getRate() == 3);
		assertTrue(test.getReviewList().get(oldSize).getName().equals("Wilds"));
	}
	
	@Test
	public void getAllProfessorAccounts() {
		ArrayList<Professor> professors = test.findAllProfessors();
		assertTrue(professors.size() == 2);
		assertTrue(professors.get(0).getMod() == 0);
		assertTrue(professors.get(1).getMod() == 0);
	}
}
