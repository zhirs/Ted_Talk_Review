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
	private String review0;
	private String review1;
	private String review2;
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
			System.out.println("Network Admin Home Servlet: doGet");
			NetworkAdminController NAController = new NetworkAdminController();
			
			review0 = (String) req.getSession().getAttribute("review0");
			review1 = (String) req.getSession().getAttribute("review1");
			review2 = (String) req.getSession().getAttribute("review2");
			
			String delete = (String) req.getParameter("delete");
			ArrayList<Student> newbies = NAController.loadUnapproveds();
			ArrayList<String> newNames = new ArrayList<String>();
			for(Student stud : newbies) {
				newNames.add(stud.getUserName());
			}
			req.getSession().setAttribute("newbs", newNames);
			req.setAttribute("review0", review0);
			req.setAttribute("review1", review1);
			req.setAttribute("review2", review2);
			req.getRequestDispatcher("/_view/networkadminHome.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//getting the data from the search bar
		NetworkAdminController NAController = new NetworkAdminController();
		System.out.println("Network Admin Home Servlet: doPost");
		
		String delete = (String) req.getParameter("delete");
		ArrayList<Student> newbies = NAController.denyStudent(delete);
		ArrayList<String> newNames = new ArrayList<String>();
		for(Student stud : newbies) {
			newNames.add(stud.getUserName());
		}
		req.getSession().setAttribute("newbs", null);
		req.getSession().setAttribute("newbies", newNames);
		
		System.out.println("Network Admin Home Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/networkadminHome.jsp").forward(req, resp);
	}
	
}
