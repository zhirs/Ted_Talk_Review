package tedtalkDB.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

import tedtalkDB.model.*;
import tedtalkDB.persist.InitialData;

public class TestUsername {
	private List<Account> users;
	
	@Before
	public void setup() {
		users = new ArrayList<Account>();
		
		// Add initial data
		users.addAll(InitialData.getUsers());
	}
	
	@Test
	public void testGet() {
		assertTrue(users.get(5).getEmail() == "randomPerson@ycp.edu");
		assertTrue(users.get(5).getPassword() == "random");
		assertTrue(users.get(5).getprofID() == 6);
		assertTrue(users.get(5).getRole() == 0);
		assertTrue(users.get(5).getUser() == "randp");
		assertTrue(users.get(5).getSection() == "CS320");
	}
	
	@Test
	public void testProf() {
		assertTrue(users.get(4).getRole() == 1);
		assertTrue(users.get(4).getprofID() == 5);
		assertTrue(users.get(4).getSection() == "CS260");
	}
	
	@Test
	public void testSuperAdmins() {
		assertTrue(users.get(0).getRole() == 2);
		assertTrue(users.get(0).getprofID() == 1);
		assertTrue(users.get(0).getSection() == "ADMIN");
		
		assertFalse(users.get(1).getPassword() == "monkey");
	}
}
