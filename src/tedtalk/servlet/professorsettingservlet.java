package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.model.Professor;
import tedtalkDB.model.Account;
import tedtalk.controller.ProfessorController;
import tedtalk.controller.ReviewController;

public class professorsettingservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private String email = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Professor Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		email = (String) req.getSession().getAttribute("email");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			Professor model = new Professor();
			
			ProfessorController controller = new ProfessorController();
		
			String errorMessage = null;
			
			controller.setModel(model);
			 
			ReviewController revController = new ReviewController();
			
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("professorM", model);
			req.setAttribute("userModel", model);
			req.setAttribute("email", email);
			req.getRequestDispatcher("/_view/professorsetting.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Professor Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/professorsetting.jsp").forward(req, resp);
	}
	
}