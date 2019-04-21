package tedtalkDB.persist;

import java.util.ArrayList;

import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;

public interface IDatabase {
	// used for switching between real and fake databases
	public boolean checkCredentials(String user, String pass);
	public Account setLogin(String user);
	public ArrayList<Professor> addProfessor(String user, String pass, String email);
	public ArrayList<Student> addStudent(String user, String pass, String email, String section);
	public ArrayList<NetworkAdmin> addAdmin(String user, String pass, String email);
	public ArrayList<Review> getProfIDReviewList(int profID);
	public int getReviewTotal(int profID);
	public ArrayList<Review> findReview(String keyword);
	public ArrayList<Review> addReview(String URL, String name, int rate, String pres, String desc, int profID, String tag, int status);
}