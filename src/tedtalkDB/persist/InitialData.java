package tedtalkDB.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;
import tedtalkDB.model.Tags;

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
				adminID = adminID++;
				String username = i.next();
				String password = i.next();
				String email = i.next();
				NetworkAdmin admin = new NetworkAdmin(username, password, email, adminID);
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
			Integer profID = 10000;
			while(true) {
				List<String> tuple = readProfs.next();
				if(tuple == null) {
					break;
				}
				Iterator<String>i = tuple.iterator();
				Integer.parseInt(i.next());
				profID = profID++;
				String username = i.next();
				String password = i.next();
				String email = i.next();
				Professor prof = new Professor(username, password, email, profID);
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
				studentID = studentID++;
				String username = i.next();
				String password = i.next();
				String email = i.next();
				String section = i.next();
				Student student = new Student(username, password, email, section, studentID);
				studentList.add(student);
			}
			System.out.println("studentList loaded from CSV file");
			return studentList;
		}
		finally {
			readStudents.close();
		}
	}
	
	public static List<Review> getReviews() throws IOException{		
		List<Review> reviewList = new ArrayList<Review>();
		
		ReadCSV readReviews = new ReadCSV("reviews.csv");
		// four superadmins created
		try {
			Integer reviewID = 1;
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
				Review review = new Review(url, name, rate, pres, desc, profID, reviewID, tag, status);
				reviewList.add(review);
			}
			System.out.println("studentList loaded from CSV file");
			return reviewList;
		}
		finally {
			readReviews.close();
		}
	}
}
