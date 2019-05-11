package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.Controller.StudentController;
import tedtalkDB.model.Account;
import tedtalkDB.model.Pair;
import tedtalkDB.persist.DerbyDatabase;

public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private DerbyDatabase derby = new DerbyDatabase();
	private int role; 
	private String leader = null;
	private String second = null;
	private String third = null;
	private int max[] = new int[5];
	private String show[] = new String[5];
	private int count[] = new int[3];
	private String student[] = new String[3];
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		
		getTopProfiles();
		printLeaderBoards();
		
		req.setAttribute("leader", leader);
		req.setAttribute("second", second);
		req.setAttribute("third", third);
		
		// call JSP to generate empty form
		if(username != null) { 
			req.getSession().setAttribute("username", null);
			req.getSession().setAttribute("email", null);
			req.getSession().setAttribute("profID", null);
			req.getSession().setAttribute("section", null);
			req.getSession().setAttribute("role", null);
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		else {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		// call JSP to generate empty form
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doPost");
		
		//ProfileModel model = new ProfileModel();
		//ProfileController controller = new ProfileController();
		//controller.setModel(model);

		// set "game" attribute to the model reference
		// the JSP will reference the model elements through "game"	
		String user = req.getParameter("u");
		String pass = req.getParameter("p");

		//model.setUser(user);
		//model.setPass(pass);
		//Question for the future how do we decide which controller to use if we don't know if they actually have an account yet
		getTopProfiles();
		printLeaderBoards();
		getTopReviews();
		
		if(derby.checkCredentials(user, pass)) {	//replaced controller methods with derby methods
			req.setAttribute("leader", leader);
			req.setAttribute("second", second);
			req.setAttribute("third", third);
			
			Account login = derby.setLogin(user);
			HttpSession session = req.getSession(true);
			
			session.setAttribute("TopURL", show);
			session.setAttribute("username", user);
			session.setAttribute("email", login.getEmail());
			session.setAttribute("profID", login.getprofID());
			// global review status
			session.setAttribute("modStat", derby.getGlobalMod());
			if(derby.getRole(user) == 2){
				String section = derby.getSection(login.getprofID());
				session.setAttribute("section", section);
			}//need to change this depending if they are student/admin/ or professor
			role = derby.getRole(user);	//grabs the role number from accounts to find the correct home page
//			System.out.println(role);
			session.setAttribute("role", role);
			if(role == 0) {
				// if admin get modStat
				session.setAttribute("moderator", derby.getModStat(login.getprofID()));
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/networkadminHome");
			}
			else if(role== 1) {
				// if professor get mod
				session.setAttribute("moderator", derby.getMod(login.getprofID()));
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/professorHome");
			}
			else if(role == 2) {
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/studentHome");
			} 
			else {	//this should never occur
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		}
		
		//check if changing else below to check cred != true will work better for running this
		else if(user.equals("") || pass.equals("")) {
			req.getRequestDispatcher("/_view/createStudent.jsp").forward(req, resp);
		}
		
		else{
			req.setAttribute("response", "Incorrect Username or Password");
			System.out.println("Login Servlet: Login Failed");				
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		// now call the JSP to render the new page	
	}
	
	public void printLeaderBoards() {
		leader = student[0];
		second = student[1];
		third = student[2];
		
		leader += " with " + count[0];
		second += " with " + count[1];
		third += " with " + count[2];
	}
	
	public void getTopReviews() {
		ArrayList<String> unique = new ArrayList<String>();
		ArrayList<Integer> top = new ArrayList<Integer>();
		top.addAll(derby.getReviewTop());
		unique.addAll(derby.getReviewUnique());
	
		for(int x = 0; x < 5; x ++) {
			max[x] = -1;
			show[x] = null;
		}
		
		for(int w = 0; w < 5; w ++) {
			for(int x = 0; x < top.size(); x ++) {
				if(top.get(x) > max[w]) {
					max[w] = top.get(x);
					show[w] = unique.get(x);
				}
			}
			for(int x = 0; x < top.size(); x ++) {
				if(show[w].equals(unique.get(x))) {
					unique.remove(x);
					top.remove(x);
				}
			}
		}
		
		for(int x = 0; x < 5; x ++) {
			show[x] = derby.getReviewNameByURL(show[x]).get(0);
			System.out.println(show[x]);
		}
	}
	
	public void getTopProfiles() {
		ArrayList<String> students = new ArrayList<String>();
		ArrayList<Integer> highest = new ArrayList<Integer>();
		students.addAll(derby.getStudentUserNames());
		for(int x = 0; x < students.size(); x ++) {
			highest.add(derby.getReviewTotal(derby.getProfID(students.get(x))));
		}
	
		for(int x = 0; x < 3; x ++) {
			count[x] = -1;
			student[x] = null;
		}
		
		for(int w = 0; w < 3; w ++) {
			for(int x = 0; x < highest.size(); x ++) {
				if(highest.get(x) > count[w]) {
					count[w] = highest.get(x);
					student[w] = students.get(x);
				}
			}
			for(int x = 0; x < highest.size(); x ++) {
				if(student[w].equals(students.get(x))) {
					students.remove(x);
					highest.remove(x);
				}
			}
		}
		
		for(int x = 0; x < 3; x ++) {
			System.out.println(student[x] + "   " + count[x]);
		}
	}
}
