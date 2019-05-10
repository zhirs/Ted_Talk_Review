package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class homeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private int role;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Home Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");

		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			role = (int) req.getSession().getAttribute("role");
			if(role==0) {
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/networkadminHome");
			}
			else if(role==1) {
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/professorHome");
			}
			else if(role ==2) {
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/studentHome");
			}
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Home Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
}
