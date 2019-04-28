package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalk.model.ProfileModel;
import tedtalkDB.model.Review;
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
		Review handle = new Review();
		ProfileModel profileHandle = new ProfileModel();
		/*USE OF THE DB REVIEWMODEL TO SET OBTAIN ATTRIB:
		 * String reviewName = req.getParameter("name");
		String reviewDesc = req.getParameter("reviewText");
		String reviewTitle = req.getParameter("title");
		String reviewPresenter = req.getParameter("presenterName");
		String	url = req.getParameter("url");
		String tags = req.getParameter("tags");
		int reviewRating = req.getIntHeader("rating");*/
		
	/*	//USE OF THE ORGINAL MODEL TO SET ATTRIB:
		handle.setDesc(req.getParameter("description"));
		handle.setPres(req.getParameter("presenterName"));
		handle.setRate(req.getIntHeader("rating"));
		handle.setName( req.getParameter("name"));
		handle.setTopic(req.getParameter("title"));*/
				
		revController.setModel(handle);//USED WITH THE DB REVIEW MODEL
		
		revController.newReview(handle.getURL(), handle.getName(), handle.getRate(), handle.getPres(), handle.getDesc(), profileHandle.getProfID(),  handle.getTag());
		ArrayList<String> reviews = new ArrayList<String>();
							
		String reviewDesc = req.getParameter("reviewText");//REMOVE THIS LINE LATER

		reviews.add(reviewDesc);
		req.setAttribute("UpdatedReviews", reviews);
		req.setAttribute("reviewHandle",handle);//CREATING AN ATTRIB TO USE IN JSP
		
		//reviews.add(revController.newReview(req.getSession().getAttribute("username"), rate, topic, pres, desc, profID, revID));
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
	
}
