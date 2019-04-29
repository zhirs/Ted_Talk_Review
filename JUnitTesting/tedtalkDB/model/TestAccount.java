package tedtalkDB.model;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

import tedtalkDB.model.*;
import tedtalkDB.persist.InitialData;
import tedtalkDB.persist.DerbyDatabase;

public class TestAccount {
	private List<Account> users;
	private DerbyDatabase derby;
	
	@Before
	public void setup() throws IOException {
		users = new ArrayList<Account>();
		derby = new DerbyDatabase();
		
		// Add initial data
		users.addAll(InitialData.getAdmins());
		users.addAll(InitialData.getProfs());
		users.addAll(InitialData.getStudents());
	}
	
	@Test
	public void testGet() {
		assertTrue(users.get(0).getEmail().equals("jlandau2@ycp.edu"));
		assertTrue(users.get(0).getprofID() == 1);
		assertTrue(users.get(0).getUserName().equals("jlandau2"));
	}

	@Test
	public void testNetworkAdmins() {
		assertTrue(users.get(0).getprofID() == 1);		
		
		NetworkAdmin admin = (NetworkAdmin) users.get(0);
		
		admin.setModStat(1);
		assertTrue(admin.getModStat() == 1);
		admin.setModStat(0);
		assertTrue(admin.getModStat() == 0);
		
		assertTrue(admin.getModStat() == 0);
	}
}
