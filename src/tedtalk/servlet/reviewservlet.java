package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.model.Review;
import tedtalkDB.Controller.NetworkAdminController;
import tedtalkDB.Controller.ProfessorController;
import tedtalk.controller.ReviewController;

public class reviewservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//VARIABLES:
	private String username = null;
  //TEMPORARY ONCE TOP10 QUERY IS COMPLETE WILL USE:
	//the review strings don't need to be set to null because it is implied
	private String review0, review1, review2, review3, review4;
	private String common1 = null;
	private String common2 = null;
	private String titlez = null;
	private int avgRating  = 0;
	private ReviewController revController;
	private NetworkAdminController adminController;
	Review handle = new Review();
	private String[] show;
	private int profID = -1;
	private ArrayList<String> descriptions;  
	private ArrayList<Integer> ratings;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		revController = new ReviewController();
		adminController = new NetworkAdminController();
		ArrayList<Review> derbyResults = new ArrayList<Review>();
		
		System.out.println("Review Servlet: doGet");	
		
		username = (String) req.getSession().getAttribute("username");

		show = (String[]) req.getSession().getAttribute("TopURL");
	 
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
			ArrayList<String> title = (ArrayList<String>) req.getSession().getAttribute("titles");
			if(title != null) {
			String newTitle = title.get(0); 

			derbyResults.addAll(revController.search(newTitle));
			derbyResults.remove(derbyResults.size() - 1);	//without this the review controller adds an extra review
		
			//String review0 = "Joseph Landau's Symposium";
			//SETTING REFERENCE FOR JSP: INDEX OF 0 WILL RETURN THE FIRST HIT FOR THAT TITLE
			req.setAttribute("description", derbyResults.get(0).getDesc());
			req.setAttribute("presenterName", derbyResults.get(0).getPres());
			req.setAttribute("url", derbyResults.get(0).getURL());
			req.setAttribute("tag", derbyResults.get(0).getTag());
			req.setAttribute("name",derbyResults.get(0).getName());
			
			//DISPLAY RELATED REVIEWS:
			if(review1 != null) {
			ArrayList<Review> tester = revController.search(review1);
			req.setAttribute("common1Title", tester.get(0).getName());
			req.setAttribute("common1URL", tester.get(0).getURL());
			req.setAttribute("common1Rate", tester.get(0).getRate());
			
			ArrayList<Review> tester1 = revController.search(review2);			
			req.setAttribute("common2Title", tester1.get(0).getName());
			req.setAttribute("common2URL", tester1.get(0).getURL());
			req.setAttribute("common2Rate", tester1.get(0).getRate());
			}
			
			//AVG RATING:
			avgRating = revController.getAverageRating(derbyResults.get(0).getURL());
			for(int i = 0; i < derbyResults.size(); i++) {
				System.out.println("----the description is: " + derbyResults.get(i).getDesc() + " the name is " + derbyResults.get(i).getName());
			}
			//Array list for previous review population
			descriptions = new ArrayList<String>();
			ratings = new ArrayList<Integer>();
			for(Review reviews: derbyResults) {
				if(!descriptions.contains(reviews.getDesc())) {
					descriptions.add(reviews.getDesc());
					ratings.add(reviews.getRate());
				}
			}
			int listSize = descriptions.size() -1;

			req.setAttribute("avgRating", avgRating);
			req.setAttribute("descriptions", descriptions);
			req.setAttribute("ratings", ratings);
			req.setAttribute("listSize", listSize);
			}
			
			req.getRequestDispatcher("/_view/review.jsp").forward(req, resp);

		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Review Servlet: doPost");
    
		Review handle = new Review();
		ReviewController revController = new ReviewController();
		revController.setModel(handle);//USED WITH THE DB REVIEW MODE

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
		NetworkAdminController nc = new NetworkAdminController();
		ProfessorController pc = new ProfessorController();
		if(nc.findGlobalStat() < 0) {
			pc.approvalAllReviews();
		}
		  
		String reviewDesc = req.getParameter("description");//REMOVE THIS LINE LATER

		reviews.add(reviewDesc);
		req.setAttribute("UpdatedReviews", reviews);
		req.setAttribute("reviewHandle",handle);//CREATING AN ATTRIB TO USE IN JSP
		
		//clears unneeded session data
		req.setAttribute("description", null);
		req.setAttribute("presenterName", null);
		req.setAttribute("url", null);
		req.setAttribute("tag", null);
		req.setAttribute("name", null);
		req.getSession().setAttribute("titles", null);
		
		//HOW DO WE KNOW WHAT JSP TO RENDER?:
		
		int roleID = adminController.findRoleID(username);	//for some reason this method works but creates a null pointer exception
		//System.out.println(username);
		
		if(roleID == 0) {
			req.getRequestDispatcher("/_view/networkadmin.jsp").forward(req, resp);
		}
		else if(roleID== 1) {
			req.getRequestDispatcher("/_view/professor.jsp").forward(req, resp);
		}
		else{
			req.getRequestDispatcher("/_view/student.jsp").forward(req, resp);
		} 
	}
	
}
