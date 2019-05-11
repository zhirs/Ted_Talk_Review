package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class studenthomeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private String review0;
	private String review1;
	private String review2;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Student Home Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");

		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			review0 = (String) req.getSession().getAttribute("review0");
			review1 = (String) req.getSession().getAttribute("review1");
			review2 = (String) req.getSession().getAttribute("review2");
			
			req.getRequestDispatcher("/_view/studentHome.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Student Home Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/studentHome.jsp").forward(req, resp);
	}
	
}
