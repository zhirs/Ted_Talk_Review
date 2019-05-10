package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.Controller.NetworkAdminController;
import tedtalkDB.model.Student;
import tedtalkDB.model.Review;
import tedtalkDB.persist.DerbyDatabase;

public class networkadminhomeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private int profID;
	private DerbyDatabase derby;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Network Admin Home Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		derby = new DerbyDatabase();

		// call JSP to generate empty form
		if(username == null) { 
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			NetworkAdminController NAController = new NetworkAdminController();
			ArrayList<Student> names = NAController.loadUnapproveds();
			ArrayList<String> newbs = new ArrayList<String>();
			for(Student stud : names) {
				newbs.add(stud.getUserName());
			}
			req.getSession().setAttribute("newbs", newbs);
			req.getRequestDispatcher("/_view/networkadminHome.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//getting the data from the search bar
		String searchName = req.getParameter("searchName");
		String year = req.getParameter("year1");
		String month = req.getParameter("month1"); //remember to use ## format. EX: 01 or 12
		String day = req.getParameter("day1"); //remember to use ## format. EX: 04
		String year2 = req.getParameter("year2");
		String month2 = req.getParameter("month2");   
		String day2 = req.getParameter("day2");  
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
		System.out.println("Network Admin Home Servlet: doPost");
		NetworkAdminController NAController = new NetworkAdminController();
		NAController.approveAllStudents();
		req.getSession().setAttribute("newbs", "No new students to approve");
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/networkadminHome.jsp").forward(req, resp);
	}
	
}
