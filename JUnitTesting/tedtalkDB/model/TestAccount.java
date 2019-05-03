package tedtalkDB.model;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
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
	private List<NetworkAdmin> admins;
	private List<Professor> professors;
	private List<Student> students;
	private DerbyDatabase derby;
	
	@Before
	public void setup() throws IOException, ParseException {
		users = new ArrayList<Account>();
		admins = new ArrayList<NetworkAdmin>();
		professors = new ArrayList<Professor>();
		students = new ArrayList<Student>();
		derby = new DerbyDatabase();
		
		// Add initial data
		users.addAll(InitialData.getAccounts());
		admins.addAll(InitialData.getAdmins());
		professors.addAll(InitialData.getProfs());
		students.addAll(InitialData.getStudents());
	}
	
	@Test
	public void testGetAccount() {
		assertTrue(users.get(0).getEmail().equals("jlandau2@ycp.edu"));
		assertTrue(users.get(0).getprofID() == 1);
		
		assertTrue(users.get(0).getUserName().equals("jlandau2"));
		System.out.println(users.get(0).getUserName());
	}

	@Test
	public void testNetworkAdmins() {
		assertTrue(admins.get(0).getprofID() == 1);		
		
		NetworkAdmin admin = admins.get(0);
		
		admin.setModStat(1);
		assertTrue(admin.getModStat() == 1);
		admin.setModStat(0);
		assertTrue(admin.getModStat() == 0);
		
		assertTrue(admin.getModStat() == 0);
	}
}
