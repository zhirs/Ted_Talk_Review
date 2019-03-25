package tedtalkDB.persist;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

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
}
