package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			req.getSession().setAttribute("username", null);
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
		
		ProfileModel model = new ProfileModel();
		ProfileController controller = new ProfileController();
		controller.setModel(model);

		// set "game" attribute to the model reference
		// the JSP will reference the model elements through "game"	
		String user = req.getParameter("u");
		String pass = req.getParameter("p");

		model.setUser(user);
		model.setPass(pass);	
		
		
		if(controller.verified()) {
			controller.createLogin(user);
			HttpSession session = req.getSession(true);
			session.setAttribute("username", model.getUser());
			session.setAttribute("email", model.getEmail());
			session.setAttribute("profID", model.getProfID());
			session.setAttribute("section", model.getSection());
			
			System.out.println("Login Servlet: Login Successful");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		else{
			req.setAttribute("response", "Incorrect Username or Password");
			System.out.println("Login Servlet: Login Failed");				
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			}
		// now call the JSP to render the new page	
	}
}
