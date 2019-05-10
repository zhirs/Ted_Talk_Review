package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.model.Review;
import tedtalkDB.persist.*;
import tedtalk.model.ProfileModel;
import tedtalk.controller.ProfileController;
import java.util.ArrayList;

public class homeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DerbyDatabase derby = new DerbyDatabase();//DERBY INSTANCE
	private String username = null;
	String review0 = "Joseph Landau's Symposium";
	String review1 = "Darnell Hill smells funny";
	String review2 = "Endgame spoilers suck";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Home Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		req.getSession().setAttribute("review0", review0);
		req.getSession().setAttribute("review1", review1);
		req.getSession().setAttribute("review2", review2);

		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			//FIND REVIEW AND RETURN ALL ATTRIBUTES OF REVIEW:
			ArrayList<Review> derbyResults0 = derby.findReview(review0);
			ArrayList<Review> derbyResults1 = derby.findReview(review1);
			ArrayList<Review> derbyResults2 = derby.findReview(review2);

			req.setAttribute("review0", derbyResults0.get(0).getName());//GET TITLE OF REVIEW
			req.setAttribute("review1", derbyResults1.get(0).getName());//GET TITLE OF REVIEW
			req.setAttribute("review2", derbyResults2.get(0).getName());//GET TITLE OF REVIEW

			//DIRECTED TO HOME JSP:
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		//SET TOP REVIEW INTO SESSION:
		session.setAttribute("review0", review0);
		session.setAttribute("review1", review1);
		session.setAttribute("review2", review2);
		
		// now call the JSP to render the new page:
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
}