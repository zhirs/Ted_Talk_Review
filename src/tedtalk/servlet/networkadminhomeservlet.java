package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.Controller.NetworkAdminController;
import tedtalkDB.model.Student;

public class networkadminhomeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Network Admin Home Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		// call JSP to generate empty form
		if(username == null) { 
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			NetworkAdminController NAController = new NetworkAdminController();
			ArrayList<Student> names = NAController.loadUnapproveds();
			ArrayList<String> newbs = new ArrayList<String>();
			for(Student stud : names) {
				newbs.add(stud.getUserName());
			}
			req.getSession().setAttribute("newbs", newbs);
			req.getRequestDispatcher("/_view/networkadminHome.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Network Admin Home Servlet: doPost");
		NetworkAdminController NAController = new NetworkAdminController();
		NAController.approveAllStudents();
		req.getSession().setAttribute("newbs", "No new students to approve");
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/networkadminHome.jsp").forward(req, resp);
	}
	
}
