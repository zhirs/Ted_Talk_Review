package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class professorhomeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Professor Home Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");

		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/professorHome.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Professor Home Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/professorHome.jsp").forward(req, resp);
	}
	
}
