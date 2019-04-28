package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalk.model.ProfileModel;
import tedtalkDB.model.Account;
import tedtalkDB.model.Review;
import tedtalk.controller.ProfileController;
import tedtalk.controller.ReviewController;

public class profileservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private String email = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Profile Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		email = (String) req.getSession().getAttribute("email");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			ProfileModel model = new ProfileModel();
			
			ProfileController controller = new ProfileController();
		
			String errorMessage = null;
			
			controller.setModel(model);
			 
			ReviewController revController = new ReviewController();
			Review revModel = new Review();

			//Adrian's code, needed something that would grab the session parameter. temporary
			ArrayList<String> test = new ArrayList<String>();
			test.add(req.getParameter("UpdatedReviews"));
			
			revController.setModel(revModel);
			ArrayList<Review> revReturn= revController.fetchReviews((int) req.getSession().getAttribute("profID"));
			ArrayList<String> reviews = new ArrayList<String>();
						
			if(!revReturn.isEmpty()) {
				for(int i = 0; i < revReturn.size(); i++) {
					reviews.add(revReturn.get(i).getDesc());
				}
			}
			
			req.setAttribute("reviews" , reviews);
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("profileM", model);
			req.setAttribute("userModel", model);
			req.setAttribute("email", email);
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Profile Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
	
}
