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
	private ArrayList<String> revNames, revURLs, revPresenters, revDescriptions;
	private ArrayList<Integer> revIDs;
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
		revIDs = new ArrayList<Integer>();
		
		//the zero is to get every review that has not been approved or denied
		reviewQueue.addAll(controller.getReviewByStatus(0));	
		//description, almost every element except rating
		if(!reviewQueue.isEmpty()) {
			for(int i = 0; i < reviewQueue.size(); i++) {
				revNames.add(reviewQueue.get(i).getName());
				revURLs.add(reviewQueue.get(i).getURL());
				revPresenters.add(reviewQueue.get(i).getPres());
				revDescriptions.add(reviewQueue.get(i).getDesc());
				revIDs.add(reviewQueue.get(i).getRevID());
			}
		}
		listSize = revNames.size() - 1;
		req.setAttribute("listSize", listSize);
		req.setAttribute("revNames" , revNames);
		req.setAttribute("revURLs" , revURLs);
		req.setAttribute("revPresenters" , revPresenters);
		req.setAttribute("revDescriptions" , revDescriptions);
		req.setAttribute("revIDs" , revIDs);
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
		
		listSize = 0;
		revNames = new ArrayList<String>();
		revURLs = new ArrayList<String>();
		revPresenters = new ArrayList<String>();
		revDescriptions = new ArrayList<String>();
		revIDs = new ArrayList<Integer>();

		if(req.getParameter("delete") != null) {
			int delete = Integer.parseInt(req.getParameter("delete"));	//converts the String parameter to int
			controller.changeReviewStatus(1, delete);
			//the zero is to get every review that has not been approved or denied
			reviewQueue = new ArrayList<Review>();
			reviewQueue = controller.getReviewByStatus(0);	
			//description, almost every element except rating
			if(!reviewQueue.isEmpty()) {
				for(int i = 0; i < reviewQueue.size(); i++) {
					revNames.add(reviewQueue.get(i).getName());
					revURLs.add(reviewQueue.get(i).getURL());
					revPresenters.add(reviewQueue.get(i).getPres());
					revDescriptions.add(reviewQueue.get(i).getDesc());
					revIDs.add(reviewQueue.get(i).getRevID());
				}
			}
			listSize = revNames.size() - 1;
			
			
			
		}
		
		// now call the JSP to render the new page
		req.setAttribute("listSize", listSize);
		req.setAttribute("revNames" , revNames);
		req.setAttribute("revURLs" , revURLs);
		req.setAttribute("revPresenters" , revPresenters);
		req.setAttribute("revDescriptions" , revDescriptions);
		req.setAttribute("revIDs" , revIDs);
		req.getRequestDispatcher("/_view/professorReviewQueue.jsp").forward(req, resp);
	} 
	
}
