package tedtalkDB.persist;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;

public interface IDatabase {
	// used for switching between real and fake databases
	public boolean checkCredentials(String user, String pass);
	public Account setLogin(String user);
	public ArrayList<Professor> addProfessor(String user, String pass, String email, int mod);
	public ArrayList<Student> addStudent(String user, String pass, String email, String section, String major);
	public ArrayList<NetworkAdmin> addAdmin(String user, String pass, String email, int modstat);
	public ArrayList<Review> getProfIDReviewList(int profID);
	public Integer getReviewTotal(int profID);
	public ArrayList<Review> findReview(String keyword);
	public ArrayList<Review> addReview(String URL, String name, int rate, String pres, String desc, int profID, String tag, int status);
	public Integer getModStat(int profID);
	public Integer getMod(int profID);
	public Integer updateModStat(int profID, int modStat);
	public Integer updateMod(int input, int i);
	public ArrayList<Review> getReviewsBetweenDates(int profID, Date date1, Date date2);
	public Integer updateStatus(int revID, int status);
	public Integer getStatus(int revID);
	public ArrayList<Student> studentsByMajor(String major);
	public Student studentByProfID(int profID);
	public Integer updateSection(int profID, String section);
	public String getSection(int profID);
	public Professor professorByProfID(int profID);
	public NetworkAdmin adminByProfID(int profID);
	public Integer getProfID(String user);
}