package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			req.getRequestDispatcher("/_view/networkadminHome.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//getting the data from the search bar
		String searchTitle = req.getParameter("dateSearch");
		String year = req.getParameter("year");
		String month = req.getParameter("month"); //remember to use ## format. EX: 01 or 12
		String day = req.getParameter("day"); //remember to use ## format. EX: 04
		profID = (int) req.getSession().getAttribute("profID");
		System.out.println(searchTitle);
		
		Calendar today = Calendar.getInstance();	//Grabs the current day, to use as date1
		java.util.Date utilDate1 = today.getTime();
	    java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
		
		String date2 = year+"/"+month+"/"+ day;
		java.util.Date utilDate2 = new java.util.Date(date2);
	    java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
		ArrayList<Review> reviews = derby.getReviewsBetweenDates(profID, sqlDate1, sqlDate2);
		if(reviews.isEmpty()) {
			System.out.println("It is empty the date2 is " + date2 + " the prof id is " + profID);
		}
		
		req.setAttribute("reviews" , reviews);
		System.out.println("Network Admin Home Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/networkadminHome.jsp").forward(req, resp);
	}
	
}
