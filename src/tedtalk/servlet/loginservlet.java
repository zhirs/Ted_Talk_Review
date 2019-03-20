package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalk.model.ProfileModel;
import tedtalk.controller.ProfileController;

public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		// call JSP to generate empty form
		if(username != null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			req.getSession().setAttribute("username", null);
		}
		else {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doPost");
		
		ProfileModel model = new ProfileModel();
		ProfileController controller = new ProfileController();
		controller.setModel(model);
		String errorMessage = null;
		// set "game" attribute to the model reference
		// the JSP will reference the model elements through "game"	
		try {
			String user = req.getParameter("u");
			String pass = req.getParameter("p");
			
			model.setUser(user);
			model.setPass(pass);
			
			if(controller.verified()) {
				resp.sendRedirect(req.getContextPath() + "/home");
				System.out.println("Login Servlet: Login Successful");
				req.getSession().setAttribute("username", model.getUser());
			}
			else{
				req.setAttribute("response", "Incorrect Username or Password");
				System.out.println("Login Servlet: Login Failed");
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			}
		}catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}

		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

}
