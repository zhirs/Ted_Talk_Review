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
		assertTrue(users.get(6).getEmail().equals("randomPerson@ycp.edu"));
		assertTrue(users.get(6).getPassword().equals("random"));
		assertTrue(users.get(6).getprofID() == 7);
		assertTrue(users.get(6).getUserName().equals("randp"));
		assertTrue(((Student) users.get(6)).getSection().equals("CS320"));
	}
	
	@Test
	public void testProf() {
		assertTrue(users.get(5).getUserName().equals("djhake2"));
		assertTrue(users.get(5).getprofID() == 6);
		
		Professor prof = (Professor) users.get(5);
		assertTrue(prof.getMod() == 0);
	}
	
	@Test
	public void testNetworkAdmins() {
		assertTrue(users.get(0).getprofID() == 1);		
		assertFalse(users.get(1).getPassword().equals("monkey"));
		
		NetworkAdmin admin = (NetworkAdmin) users.get(0);
		Professor prof = (Professor) users.get(4);
		
		admin.setMod(prof);
		assertTrue(prof.getMod() == 1);
		admin.setMod(prof);
		assertTrue(prof.getMod() == 0);
		
		assertTrue(admin.getModStat() == 0);
	}
}
