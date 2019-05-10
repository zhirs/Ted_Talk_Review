package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.persist.DerbyDatabase;



public class resetpasswordservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DerbyDatabase derby;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Home Servlet: doGet");	
		
		req.getRequestDispatcher("/_view/resetPassword.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Home Servlet: doPost");
		derby = new DerbyDatabase();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
}
