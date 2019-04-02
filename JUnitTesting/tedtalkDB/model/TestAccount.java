package tedtalkDB.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

import tedtalkDB.model.*;
import tedtalkDB.persist.InitialData;

public class TestAccount {
	private List<Account> users;
	
	@Before
	public void setup() {
		users = new ArrayList<Account>();
		
		// Add initial data
		users.addAll(InitialData.getUsers());
	}
	
	@Test
	public void testGet() {
		assertTrue(users.get(5).getEmail().equals("randomPerson@ycp.edu"));
		assertTrue(users.get(5).getPassword().equals("random"));
		assertTrue(users.get(5).getprofID() == 6);
		assertTrue(users.get(5).getRole() == 0);
		assertTrue(users.get(5).getUser().equals("randp"));
		assertTrue(users.get(5).getSection().equals("CS320"));
	}
	
	@Test
	public void testProf() {
		assertTrue(users.get(4).getRole() == 1);
		assertTrue(users.get(4).getprofID() == 5);
		assertTrue(users.get(4).getSection().equals("CS260"));
	}
	
	@Test
	public void testSuperAdmins() {
		assertTrue(users.get(0).getRole() == 2);
		assertTrue(users.get(0).getprofID() == 1);
		assertTrue(users.get(0).getSection().equals("ADMIN"));
		
		assertFalse(users.get(1).getPassword().equals("monkey"));
	}
}
