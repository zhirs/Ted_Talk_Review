package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalk.emailer.SendEmail;

public class sentresettoemailservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Home Servlet: doGet");	
		
		req.getRequestDispatcher("/_view/sentResetToEmail.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Home Servlet: doPost");
		
		String username = req.getParameter("username");
		SendEmail emailer = new SendEmail();
		emailer.sendEmail(username + "@ycp.edu"); 
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/sentResetToEmail.jsp").forward(req, resp);
	}
	
}
