package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.model.Review;
import tedtalkDB.Controller.ProfessorController;

public class professorreviewqueueservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private ArrayList<Review> reviewQueue;
	private ProfessorController controller;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Professor Review Queue: doGet");	
		username = (String) req.getSession().getAttribute("username");
		controller = new ProfessorController();
		reviewQueue = new ArrayList<Review>();
		ArrayList<String> reviews = new ArrayList<String>();
		reviewQueue.addAll(controller.getReviewByStatus(0));
		//description, almost every element except rating
		if(!reviewQueue.isEmpty()) {
			for(int i = 0; i < reviewQueue.size(); i++) {
				reviews.add(reviewQueue.get(i).getDesc());
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
