package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.model.Review;
import tedtalk.controller.ReviewController;
import tedtalkDB.persist.DerbyDatabase;

public class reviewservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//DATABASE INSTANCE:
	DerbyDatabase derby = new DerbyDatabase();
	//VARIABLES:
	private String username = null;
  //TEMPORARY ONCE TOP10 QUERY IS COMPLETE WILL USE:
	private String review0 = null;
	private String review1 = null;
	private String review2 = null;
	private String common1 = null;
	private String common2 = null;
	private int avgRating  = 0;
	public ReviewController revController;
	private int profID = -1;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Review Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		review0 = (String) req.getSession().getAttribute("review0");
		review1 = (String) req.getSession().getAttribute("review1");
		review2 = (String) req.getSession().getAttribute("review2");
		
		profID = (int) req.getSession().getAttribute("profID");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			//GET REVIEWS FROM DATABASE: TO AUTO POPULATE THE REVIEW PAGE:
			//String review0 = "Joseph Landau's Symposium";
			ArrayList<Review> derbyResults = derby.findReview(review2);//SEARCHED BY TITLE
			
			//SETTING REFERENCE FOR JSP: INDEX OF 0 WILL RETURN THE FIRST HIT FOR THAT TITLE
			req.setAttribute("description", derbyResults.get(0).getDesc());
			req.setAttribute("presenterName", derbyResults.get(0).getPres());
			req.setAttribute("url", derbyResults.get(0).getURL());
			req.setAttribute("tag", derbyResults.get(0).getTag());
			req.setAttribute("name",derbyResults.get(0).getName());
			
			//DISPLAY RELATED REVIEWS:
			ArrayList<Review> tester = derby.findReview(review1);
			req.setAttribute("common1Title", tester.get(0).getName());
			req.setAttribute("common1URL", tester.get(0).getURL());
			req.setAttribute("common1Rate", tester.get(0).getRate());
			
			ArrayList<Review> tester1 = derby.findReview(review2);			
			req.setAttribute("common2Title", tester1.get(0).getName());
			req.setAttribute("common2URL", tester1.get(0).getURL());
			req.setAttribute("common2Rate", tester1.get(0).getRate());
			
			//AVG RATING:
			avgRating += tester.get(0).getRate();
			avgRating += tester1.get(0).getRate();
			avgRating /= 2;

			req.setAttribute("avgRating", avgRating);
			req.getRequestDispatcher("/_view/review.jsp").forward(req, resp);


		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Review Servlet: doPost");
		Review handle = new Review();
		ReviewController revController = new ReviewController();
		DerbyDatabase derby = new DerbyDatabase();
		revController.setModel(handle);//USED WITH THE DB REVIEW MODE

    derby.getProfID(username);
		handle.setURL(req.getParameter("url"));
		System.out.println(handle.getURL());
		handle.setName( req.getParameter("title"));
		System.out.println(handle.getName());
		handle.setRate(req.getIntHeader("rating"));
		System.out.println(handle.getRate());
		handle.setPres(req.getParameter("presenterName"));
		System.out.println(handle.getPres());
		handle.setDesc(req.getParameter("description"));
		System.out.println(handle.getDesc());
		handle.setTag(req.getParameter("tags"));
		System.out.println(handle.getTag());
		
		revController.setModel(handle);//USED WITH THE DB REVIEW MODEL
		
		revController.newReview(handle.getURL(), handle.getName(), handle.getRate(), handle.getPres(), handle.getDesc(), profID,  handle.getTag());
		ArrayList<String> reviews = new ArrayList<String>();
							
		String reviewDesc = req.getParameter("description");//REMOVE THIS LINE LATER

		reviews.add(reviewDesc);
		req.setAttribute("UpdatedReviews", reviews);
		req.setAttribute("reviewHandle",handle);//CREATING AN ATTRIB TO USE IN JSP
		//HOW DO WE KNOW WHAT JSP TO RENDER?:
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		//req.getRequestDispatcher("/_view/networkadmin.jsp").forward(req, resp);
	}
	
}
