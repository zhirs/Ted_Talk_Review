package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalk.model.ProfileModel;
import tedtalkDB.model.Review;
import tedtalk.controller.ProfileController;
import tedtalk.controller.ReviewController;

public class reviewservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Review Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/review.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Review Servlet: doPost");
		
		ReviewController revController = new ReviewController();
		Review revModel = new Review();
		String reviewDesc = req.getParameter("reviewText");
		
		revController.setModel(revModel);
		ArrayList<Review> revReturn= revController.fetchReviews((int) req.getSession().getAttribute("profID"));
		ArrayList<String> reviews = new ArrayList<String>();
					
		/*if(!revReturn.isEmpty()) {
			for(int i = 0; i < revReturn.size(); i++) {
				//reviews.add(revReturn.get(i).getDesc());
				reviews.add(reviewDesc);
			}
		} */
		reviews.add(reviewDesc);
		req.setAttribute("UpdatedReviews", reviews);
		
		//reviews.add(revController.newReview(req.getSession().getAttribute("username"), rate, topic, pres, desc, profID, revID));
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
	
}
