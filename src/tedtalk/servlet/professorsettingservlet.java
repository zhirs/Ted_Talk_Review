package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.model.Professor;
import tedtalkDB.persist.DerbyDatabase;
import tedtalkDB.Controller.ProfessorController;
import tedtalk.controller.ReviewController;

public class professorsettingservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private String email = null;
	private int globalMod;
	private String globalModStatStatus = null;
	private String personalStat = null;
	private int mod;
	private DerbyDatabase derby = new DerbyDatabase();
	private int profID;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Professor Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		email = (String) req.getSession().getAttribute("email");
		globalMod = (int) req.getSession().getAttribute("modStat");
		profID = (int) req.getSession().getAttribute("profID");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			Professor model = new Professor();
			
			ProfessorController controller = new ProfessorController();
		
			String errorMessage = null;
			
			controller.setModel(model);
			 
			ReviewController revController = new ReviewController();
			
			if(globalMod <= 0) {
				mod = (int) req.getSession().getAttribute("moderator");
			}
			
			changeToString();
			
			req.setAttribute("globalModStat", globalModStatStatus);
			req.setAttribute("currentStat", personalStat);
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("professorM", model);
			req.setAttribute("userModel", model);
			req.setAttribute("email", email);
			req.getRequestDispatcher("/_view/professorSetting.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Professor Servlet: doPost");
		
		ProfessorController profController = new ProfessorController();
		mod = profController.switchMod(username);
		
		changeToString();
		
		HttpSession session = req.getSession(true);
		
		session.setAttribute("moderator", mod);
		req.setAttribute("globalModStat", globalModStatStatus);
		req.setAttribute("currentStat", personalStat);
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/professorSetting.jsp").forward(req, resp);
	}
	
	public void changeToString() {
		if(globalMod <= 0) {
			globalModStatStatus = "tED Talk review reviewing by professor is currently off, you may set your reviewing status";
			switch(mod) {
			case 0:
				personalStat = "off";
				break;
			case 1:
				personalStat = "on";
				break;
			default:
				personalStat = "not assigned";
			}
		}else {
			globalModStatStatus = "tED Talk review reviewing by professor is currently on, you are required to be a review moderator";
			personalStat = "off";
		}
	}
	
}