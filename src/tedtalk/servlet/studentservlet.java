package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.model.Student;
import tedtalkDB.model.Review;
import tedtalkDB.Controller.StudentController;
import tedtalk.controller.ReviewController;

public class studentservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private String email = null;
	private int profID;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Student Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		email = (String) req.getSession().getAttribute("email");
		profID = (int) req.getSession().getAttribute("profID");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			ReviewController revController = new ReviewController();
			StudentController controller = new StudentController();
			Student model = new Student();
			Review revModel = new Review();
			String errorMessage = null;
			controller.setModel(model);
			revController.setModel(revModel);
			//Adrian's code, needed something that would grab the session parameter. temporary
			ArrayList<Review> approved = revController.getApprovedRevs(profID);
			ArrayList<String> approvedDescs = new ArrayList<String>();
			if(!approved.isEmpty()) {
				for(int i = 0; i < approved.size(); i++) {
					approvedDescs.add("Review:" + approved.get(i).getName() + "Description:" + approved.get(i).getDesc()  );
				}
			}
			ArrayList<Review> denied = revController.getDeniedRevs(profID);
			ArrayList<String> deniedDescs = new ArrayList<String>();
			if(!denied.isEmpty()) {
				for(int i = 0; i < denied.size(); i++) {
					deniedDescs.add("Review: " + denied.get(i).getName() + "Description:" + denied.get(i).getDesc());
				}
			}
			ArrayList<Review> pending = revController.getPendingRevs(profID);
			ArrayList<String> pendingDescs = new ArrayList<String>();
			if(!pending.isEmpty()) {
				for(int i = 0; i < pending.size(); i++) {
					pendingDescs.add("Review: " + pending.get(i).getName() + "Description:" + pending.get(i).getDesc());
				}
			}
			req.setAttribute("pendingDescs" , pendingDescs);
			req.setAttribute("deniedDescs" , deniedDescs);
			req.setAttribute("approvedDescs", approvedDescs);
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("studentM", model);
			req.setAttribute("userModel", model);
			req.setAttribute("email", email);
			req.getRequestDispatcher("/_view/student.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Student Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/student.jsp").forward(req, resp);
	}
	
}
