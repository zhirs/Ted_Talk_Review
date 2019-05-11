package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.model.Review;
import tedtalkDB.model.Student;
import tedtalkDB.Controller.ProfessorController;

public class professorreviewqueueservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private ArrayList<Review> reviewQueue;
	private ArrayList<String> revNames;
	private ArrayList<String> revURLs;
	private ArrayList<String> revPresenters;
	private ArrayList<String> revDescriptions;
	private ProfessorController controller;
	private int listSize;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Professor Review Queue: doGet");	
		username = (String) req.getSession().getAttribute("username");
		controller = new ProfessorController();
		reviewQueue = new ArrayList<Review>();
		revNames = new ArrayList<String>();
		revURLs = new ArrayList<String>();
		revPresenters = new ArrayList<String>();
		revDescriptions = new ArrayList<String>();
		
		//the zero is to get every review that has not been approved or denied
		reviewQueue.addAll(controller.getReviewByStatus(0));	
		//description, almost every element except rating
		if(!reviewQueue.isEmpty()) {
			for(int i = 0; i < reviewQueue.size(); i++) {
				revNames.add(reviewQueue.get(i).getName());
				revURLs.add(reviewQueue.get(i).getURL());
				revPresenters.add(reviewQueue.get(i).getPres());
				revDescriptions.add(reviewQueue.get(i).getDesc());
			}
		}
		listSize = revNames.size() - 1;
		req.setAttribute("listSize", listSize);
		req.setAttribute("revNames" , revNames);
		req.setAttribute("revURLs" , revURLs);
		req.setAttribute("revPresenters" , revPresenters);
		req.setAttribute("revDescriptions" , revDescriptions);
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
		
		String delete = (String) req.getParameter("delete");
		controller.
		ArrayList<Review> newbies = controller.search(delete);
		ArrayList<String> newNames = new ArrayList<String>();
		for(Review stud : newbies) {
			newNames.add(stud.getName());
		}
		req.getSession().setAttribute("newbs", null);
		req.getSession().setAttribute("newbies", newNames);
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/professorReviewQueue.jsp").forward(req, resp);
	}
	
}
