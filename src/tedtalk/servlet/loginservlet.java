package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		
		// set "game" attribute to the model reference
		// the JSP will reference the model elements through "game"
		String AdminUser = "root";
		String AdminPass = "toor";
		
		String user = req.getParameter("u");
		String pass = req.getParameter("p");
		
		
		if( pass.equals(AdminPass) && user.equals(AdminUser)) {
			resp.sendRedirect(req.getContextPath() + "/home");
			System.out.println("Login Servlet: Login Successful");
			req.getSession().setAttribute("username", user); // adds username to session
		}else{
			req.setAttribute("response", "Incorrect Username or Password");
			System.out.println("Login Servlet: Login Failed");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}

		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

}
