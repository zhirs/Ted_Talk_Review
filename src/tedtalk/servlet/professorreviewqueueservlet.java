package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.model.Review;
import tedtalkDB.persist.DerbyDatabase;

public class professorreviewqueueservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private DerbyDatabase derby;
	private ArrayList<Review> reviewQueue;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Professor Review Queue: doGet");	
		username = (String) req.getSession().getAttribute("username");
		derby = new DerbyDatabase();
		reviewQueue = new ArrayList<Review>();
		reviewQueue.addAll(derby.getReviewByStatus(0));	//0 is the default has not been reviewed
		ArrayList<String> reviews = new ArrayList<String>();
		//description, almost every element except rating
		if(!reviewQueue.isEmpty()) {
			for(int i = 0; i < reviewQueue.size(); i++) {
				reviews.add(reviewQueue.get(i).getDesc());
				System.out.println(reviewQueue.get(i).getDesc());
			}
		}
		req.setAttribute("reviews" , reviews);
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/professorReviewQueue.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Professor Review Queue: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/professorReviewQueue.jsp").forward(req, resp);
	}
	
}
