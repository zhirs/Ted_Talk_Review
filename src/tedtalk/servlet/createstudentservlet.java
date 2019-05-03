package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.Controller.ProfessorController;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Student;

public class createstudentservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Review Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/createStudent.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Review Servlet: doPost");
		
		ProfessorController NAController = new ProfessorController();
		Professor profHandle = new Professor();
		Student handle = new Student();

		ProfessorController.setModel(profHandle);
		
		NAController.newStudent(handle.getUserName(), handle.getPassword(), handle.getEmail(), handle.getSection(), handle.getMajor());
		
		req.setAttribute("profHandle", handle);//CREATING AN ATTRIB TO USE IN JSP
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/createProfessor.jsp").forward(req, resp);
	}
	
}
