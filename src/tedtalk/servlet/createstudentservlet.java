package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.Controller.NetworkAdminController;
import tedtalkDB.model.Student;

public class createstudentservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Student Servlet: doGet");	 
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/createStudent.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Student Servlet: doPost");
		
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		String email = req.getParameter("email");
		String section = req.getParameter("section");
		String major = req.getParameter("major");
		
		NetworkAdminController NAController = new NetworkAdminController();
		
		System.out.println(user);
		System.out.println(pass);
		System.out.println(email);
		System.out.println(section);
		System.out.println(major);
		
		NAController.addStudent(user, pass, email, section, major);
		if(NAController.checkUsername(user) == 1) {
			req.setAttribute("error", "Username already taken");
		}
		System.out.println("Saved new student in quarantine");
		// now call the JSP to render the new page	
		req.getRequestDispatcher("/_view/createStudent.jsp").forward(req, resp);	
	}
		
}
