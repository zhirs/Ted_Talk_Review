package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.model.Professor;
import tedtalkDB.model.Review;
import tedtalkDB.persist.DerbyDatabase;
import tedtalkDB.Controller.ProfessorController;

public class checkstudentdateadminservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private DerbyDatabase derby;
	private int profID;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Professor Check Student Date Servlet: doGet");
		username = (String) req.getSession().getAttribute("username");
		
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		
		req.getRequestDispatcher("/_view/checkStudentDateAdmin.jsp").forward(req, resp);
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
		System.out.println(searchName);
		profID = derby.getProfID(searchName);
		
		String date1 = year+"/"+month+"/"+day;
		//String date1 = "2019/04/26";
		java.util.Date utilDate1 = new java.util.Date(date1);
	    java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
		
		String date2 = year2+"/"+month2+"/"+day2;
	    //String date2 = "2019/04/28";
		java.util.Date utilDate2 = new java.util.Date(date2);
	    java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
	    
	    ArrayList<Review> revReturn = derby.getReviewsBetweenDates(profID, sqlDate1, sqlDate2);
	    ArrayList<String> reviews = new ArrayList<String>();
		
		if(!revReturn.isEmpty()) {
			for(int i = 0; i < revReturn.size(); i++) {
				reviews.add(revReturn.get(i).getDesc());
			}
		}
		
		req.setAttribute("reviews" , reviews);
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/checkStudentDateAdmin.jsp").forward(req, resp);
	}
	
}