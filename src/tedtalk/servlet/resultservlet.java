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

public class resultservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private int profID = -1;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Result Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		profID = (int) req.getSession().getAttribute("profID");
		// call JSP to generate empty form
		if(username == null) { 
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/result.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Result Servlet: doPost");
		
		ReviewController revController = new ReviewController();
		Review handle = new Review();
				
		revController.setModel(handle);//USED WITH THE DB REVIEW MODEL
		
		String input = req.getParameter("input");
		ArrayList<Review> revs =  revController.search(input);
		ArrayList<String> titles = new ArrayList<String>();
		for(Review review : revs) {
			titles.add(review.getName());
		}
	
		if(!titles.isEmpty()) {
			req.getSession().setAttribute("titles", titles);
		}
		else {
			req.getSession().setAttribute("error", "Unable to find related TedTalks");
		}
	
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/result.jsp").forward(req, resp);
	}
	
}
