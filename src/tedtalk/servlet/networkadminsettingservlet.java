package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import tedtalkDB.model.Account;
import tedtalkDB.model.NetworkAdmin;
import tedtalk.controller.NetworkAdminController;
import tedtalk.controller.ReviewController;

public class networkadminsettingservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private String email = null;
	private String password = null;
	private int profID;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Network Admin Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		password = (String) req.getSession().getAttribute("password");
		email = (String) req.getSession().getAttribute("email");
		profID = (int) req.getSession().getAttribute("profID");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			NetworkAdmin model = new NetworkAdmin(username, password, email, profID);
			
			NetworkAdminController controller = new NetworkAdminController();
		
			String errorMessage = null;
			
			controller.setModel(model);

			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("networkAdminM", model);
			req.setAttribute("userModel", model);
			req.setAttribute("email", email);
			req.getRequestDispatcher("/_view/networkadminsetting.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Network Admin Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/networkadminsetting.jsp").forward(req, resp);
	}
	
}