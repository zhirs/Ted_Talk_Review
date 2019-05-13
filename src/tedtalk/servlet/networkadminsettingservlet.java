package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.model.NetworkAdmin;
import tedtalkDB.persist.DerbyDatabase;
import tedtalkDB.Controller.NetworkAdminController;

public class networkadminsettingservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private int globalMod;
	private int modStat;
	private String globalModStatStatus = null;
	private String personalStat = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Network Admin Settings Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		globalMod = (int) req.getSession().getAttribute("modStat");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			modStat = (int) req.getSession().getAttribute("moderator");
	
			String errorMessage = null;
			
			changeToString();
			
			req.setAttribute("globalModStat", globalModStatStatus);
			req.setAttribute("currentStat", personalStat);
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/networkadminSetting.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Network Admin Settings Servlet: doPost");
		
		NetworkAdminController NAController = new NetworkAdminController();
		modStat = NAController.updateModStat(username);
		globalMod = NAController.findGlobalStat();
		
		changeToString();
		
		HttpSession session = req.getSession(true);
		
		session.setAttribute("modStat", globalMod);
		session.setAttribute("moderator", modStat);
		req.setAttribute("globalModStat", globalModStatStatus);
		req.setAttribute("currentStat", personalStat);
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/networkadminSetting.jsp").forward(req, resp);
	}
	
	public void changeToString() {
		if(globalMod < 0) {
			globalModStatStatus = "Ted Talk review reviewing by professor is currently off, " + Math.abs(globalMod) + " moderator status change(s) need to change review type";
		}else if(globalMod > 0){
			globalModStatStatus = "Ted Talk review reviewing by professor is currently on, " + globalMod + " moderator status change(s) need to change review type";
		}else {
			globalModStatStatus = "Ted Talk review reviewing by professor is currently off, 1 moderator status change(s) need to change review type";
		}
		
		switch(modStat) {
		case 1:
			personalStat = "off";
			break;
		case 2:
			personalStat = "on";
			break;
		default:
			personalStat = "not assigned";
		}
	}
}