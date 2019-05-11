package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.model.Review;
import tedtalkDB.Controller.NetworkAdminController;

public class checkstudentdateprofessorservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private NetworkAdminController controller;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Professor Check Student Date Servlet: doGet");
		username = (String) req.getSession().getAttribute("username");
		controller = new NetworkAdminController();
		
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		
		req.getRequestDispatcher("/_view/checkStudentDateProfessor.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Professor Check Student Date Servlet: doPost");
		//getting the data from the search bar
		String searchName = req.getParameter("searchName");
		String year = req.getParameter("year1");
		String month = req.getParameter("month1"); //remember to use ## format. EX: 01 or 12
		String day = req.getParameter("day1"); //remember to use ## format. EX: 04
		String year2 = req.getParameter("year2");
		String month2 = req.getParameter("month2");   
		String day2 = req.getParameter("day2");
	    
	    ArrayList<Review> revReturn = controller.getReviewsBetweenDates(searchName, year, month, day, year2, month2, day2);
	    ArrayList<String> reviews = new ArrayList<String>();
		
		if(!revReturn.isEmpty()) {
			for(int i = 0; i < revReturn.size(); i++) {
				reviews.add(revReturn.get(i).getDesc());
			}
		}
		
		req.setAttribute("reviews" , reviews);
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/checkStudentDateProfessor.jsp").forward(req, resp);
	}
	
}