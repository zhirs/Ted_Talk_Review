package tedtalkDB.persist;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;

public class InitialData {
	public static List<NetworkAdmin> getAdmins() throws IOException{
		List<NetworkAdmin> adminList = new ArrayList<NetworkAdmin>();
		ReadCSV readAdmins = new ReadCSV("admins.csv");
		// four superadmins created
		try {
			Integer adminID = 1;
			while(true) {
				List<String> tuple = readAdmins.next();
				if(tuple == null) {
					break;
				}
				Iterator<String>i = tuple.iterator();
				Integer.parseInt(i.next());
				NetworkAdmin admin = new NetworkAdmin();
				admin.setAdminId(adminID++);
				admin.setprofID(Integer.parseInt(i.next()));
				admin.setModStat(Integer.parseInt(i.next()));
				
				adminList.add(admin);
			}
			System.out.println("adminList loaded from CSV file");
			return adminList;
		}
		finally {
			readAdmins.close();
		}
	}
	
	public static List<Professor> getProfs() throws IOException{
		List<Professor> profList = new ArrayList<Professor>();
		ReadCSV readProfs = new ReadCSV("professors.csv");
		// prof created
		try {
			Integer profID = 1;
			while(true) {
				List<String> tuple = readProfs.next();
				if(tuple == null) {
					break;
				}
				Iterator<String>i = tuple.iterator();
				Integer.parseInt(i.next());
				Professor prof = new Professor();
				
				prof.setProfessorID(profID++);
				prof.setprofID(Integer.parseInt(i.next()));
				prof.setMod(Integer.parseInt(i.next()));
				
				profList.add(prof);
			}
			System.out.println("profList loaded from CSV file");
			return profList;
		}
		finally {
			readProfs.close();
		}
	}
	
	public static List<Student> getStudents() throws IOException{
		List<Student> studentList = new ArrayList<Student>();
		ReadCSV readStudents = new ReadCSV("students.csv");
		// four superadmins created
		try {
			Integer studentID = 20000;
			while(true) {
				List<String> tuple = readStudents.next();
				if(tuple == null) {
					break;
				}
				Iterator<String>i = tuple.iterator();
				Integer.parseInt(i.next());
				Student student = new Student();
				
				student.setStudentID(studentID++);
				student.setprofID(Integer.parseInt(i.next()));
				student.setSection(i.next()); 
				student.setMajor(i.next());
				
				studentList.add(student);
			}
			System.out.println("studentList loaded from CSV file");
			return studentList;
		}
		finally {
			readStudents.close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static List<Review> getReviews() throws IOException, ParseException{		
		List<Review> reviewList = new ArrayList<Review>();
		
		ReadCSV readReviews = new ReadCSV("reviews.csv");
		// four superadmins created
		try {
			Integer reviewID = 50000;
			while(true) {
				List<String> tuple = readReviews.next();
				if(tuple == null) {
					break;
				}
				Iterator<String>i = tuple.iterator();
				Integer.parseInt(i.next());
				reviewID = reviewID++;
				String url = i.next();
				String name = i.next();
				int rate = Integer.parseInt(i.next());
				String pres = i.next();
				String desc = i.next();
				int profID = Integer.parseInt(i.next());
				String tag = i.next();
				int status = Integer.parseInt(i.next());
				String date = i.next();
				java.util.Date utilDate = new java.util.Date(date);
			    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			    System.out.println("utilDate:" + utilDate);
			    System.out.println("sqlDate:" + sqlDate);

				Review review = new Review(url, name, rate, pres, desc, profID, reviewID, tag, status, sqlDate);
				reviewList.add(review);
			}
			System.out.println("studentList loaded from CSV file");
			return reviewList;
		}
		finally {
			readReviews.close();
		}
	}

	public static List<Account> getAccounts() throws IOException, ParseException {
		List<Account> accountList = new ArrayList<Account>();
		ReadCSV readStudents = new ReadCSV("accounts.csv");
		// four superadmins created
		try {
			Integer profID = 1;
			while(true) {
				List<String> tuple = readStudents.next();
				if(tuple == null) {
					break;
				}
				Iterator<String>i = tuple.iterator();
				Integer.parseInt(i.next());
				Account account = new Account();
				
				account.setprofID(profID++);
				account.setUsername(i.next());
				account.setPassword(i.next());
				account.setEmail(i.next());
				account.setRole(Integer.parseInt(i.next()));
				
				accountList.add(account);
			}
			System.out.println("accountList loaded from CSV file");
			return accountList;
		}
		finally {
			readStudents.close();
		}
	}
}
