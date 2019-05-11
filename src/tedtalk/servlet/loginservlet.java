package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.model.Account;
import tedtalkDB.persist.DerbyDatabase;

public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private DerbyDatabase derby;
	private int role; 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
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
		derby = new DerbyDatabase();	//used derby instead of controller
		//Question for the future how do we decide which controller to use if we don't know if they actually have an account yet
		
		if(derby.checkCredentials(user, pass)) {	//replaced controller methods with derby methods
			Account login = derby.setLogin(user);
			HttpSession session = req.getSession(true);
			
			String review0 = "Joseph Landau's Symposium";
			String review1 = "Darnell Hill smells funny";
			String review2 = "Endgame spoilers suck";
			
			session.setAttribute("review0", review0);
			session.setAttribute("review1", review1);
			session.setAttribute("review2", review2);
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
}
