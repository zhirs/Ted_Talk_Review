package tedtalkDB.persist;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.model.Pair;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.model.Student;
import tedtalkDB.model.keywords;

public interface IDatabase {
	// used for switching between real and fake databases
	public boolean checkCredentials(String user, String pass);
	public Account setLogin(String user);
	public ArrayList<Professor> addProfessor(String user, String pass, String email, int mod);
	public ArrayList<Student> addStudent(String user, String pass, String email, String section, String major);
	public ArrayList<NetworkAdmin> addAdmin(String user, String pass, String email, int modstat);
	public ArrayList<Review> getProfIDReviewList(int profID, int status);
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
	public Integer getRole(String user);
	public ArrayList<Integer> getRevID(String keyword);
	public ArrayList<Review> getReviews(int rev_id);
	public ArrayList<keywords> addKeyword(String keyword, int rev_id);
	public ArrayList<String> parseTitle(String title);
	public Integer removeReview(String user, String title);
	public Integer addToAdmin(String user);
	public Integer addToProfessor(String user);
	public Integer addToStudent(String user);
	public Integer removeFromAdmin(String user);
	public Integer removeFromProfessor(String user);
	public Integer removeFromStudent(String user);
	public Integer removeAccount(String user, int role);
	public Integer updateRole(String user, boolean promo);
	public Integer getGlobalMod();
	public ArrayList<String> addandParse(String title, int rev_id);
	public ArrayList<Student> approveStudent(String user);
	public ArrayList<Student> addNewStudent(String user, String pass, String email, String section, String major);
	public Integer checkUsername(String user);
	public ArrayList<Student> unapprovedStudents();
	public ArrayList<Review> getReviewByStatus(int status);
	public Integer changeReviewStatus(int status, int rev_id);
	public Integer resetPassword(String username, String password);
	public Integer averageReviewRating(String url);
	public ArrayList<Student> denyStudent(String user);
	ArrayList<Pair<Integer, Integer>> leaderBoardTotals();
	String getUser(int profID);
	public ArrayList<String> getMajors();
	ArrayList<Integer> getStudents();
}