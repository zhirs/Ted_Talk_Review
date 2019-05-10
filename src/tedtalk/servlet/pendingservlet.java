package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.model.Student;
import tedtalkDB.model.Review;
import tedtalkDB.Controller.StudentController;
import tedtalk.controller.ReviewController;

public class pendingservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Pending Servlet: doGet");	
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/pending.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Pending Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/pending.jsp").forward(req, resp);
	}
	
}
