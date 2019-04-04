package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalk.model.ProfileModel;
import tedtalkDB.model.Account;
import tedtalk.controller.ProfileController;

public class profileservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Profile Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			ProfileModel model = new ProfileModel();
			
			ProfileController controller = new ProfileController();
			
			Account userModel = new Account();
			
			String errorMessage = null;
			
			controller.setModel(model);

			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("profileM", model);
			req.setAttribute("userModel", model);
			
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Profile Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
	
}
