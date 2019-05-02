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
		Review handle = new Review();
		ReviewController revController = new ReviewController();
		DerbyDatabase derby = new DerbyDatabase();
		revController.setModel(handle);//USED WITH THE DB REVIEW MODEL
		
		//USE OF THE ORGINAL MODEL TO SET ATTRIB:
		handle.setDesc(req.getParameter("description"));
		handle.setPres(req.getParameter("presenterName"));
		handle.setRate(req.getIntHeader("rating"));
		handle.setName( req.getParameter("title"));
				
		//FIND OUT WHAT TYPE OF USER IS LOGGED IN CURRENTLY THEN GET THERE PROFID:		
	//	revController.newReview(handle.getURL(), handle.getName(), handle.getRate(), handle.getPres(), handle.getDesc(), profileHandle.getProfID(),  handle.getTag());
		
		//GET REVIEWS FROM DATABASE:
		ArrayList<Review> derbyResults =  derby.findReview("Database");//SEARCHED BY KEYWORD
		//handle.setDesc((String)derbyResults.get(6));//REVIEW DESCRIPTION;CAN'T CONVERT REVIEW TO STRING...
							
		handle.setDesc("TESTING AUTOFILL IN DESC FIELD");
		req.setAttribute("reviewHandle",handle);//CREATING AN ATTRIB TO USE IN JSP
		//req.getSession().setAttribute("reviewHandle", handle);//JOES SUGGESTION

		//reviews.add(revController.newReview(req.getSession().getAttribute("username"), rate, topic, pres, desc, profID, revID));
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/review.jsp").forward(req, resp);//SHOULD UPDATE CURRENT PAGE 
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);//ALSO UPDATE THE PENDING REVIEW SECTION ON PROFILE

	}
	
}
