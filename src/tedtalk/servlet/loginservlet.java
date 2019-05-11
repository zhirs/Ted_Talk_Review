package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.model.Account;
import tedtalkDB.model.Pair;
import tedtalkDB.persist.DerbyDatabase;

public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private DerbyDatabase derby = new DerbyDatabase();
	private int role; 
	private ArrayList<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();
	private String leader = null;
	private String second = null;
	private String third = null;
	private int max[] = new int[5];
	private String show[] = new String[5];
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		
		result.addAll(derby.leaderBoardTotals());
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
		
		if(derby.checkCredentials(user, pass)) {	//replaced controller methods with derby methods
			printLeaderBoards();
			getTopReviews();

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
		leader = derby.getUser(result.get(0).getRight());
		second = derby.getUser(result.get(1).getRight());
		third = derby.getUser(result.get(2).getRight());
		
		leader += " with " + result.get(0).getLeft();
		second += " with " + result.get(1).getLeft();
		third += " with " + result.get(2).getLeft();
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
}
