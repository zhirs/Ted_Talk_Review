package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tedtalkDB.Controller.StudentController;
import tedtalkDB.model.Account;
import tedtalkDB.model.Student;
import tedtalkDB.persist.DerbyDatabase;

public class studentoverviewservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private DerbyDatabase derby;
	private int role; 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Overview Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		// call JSP to generate empty form
		if(username != null) { 
			StudentController sc = new StudentController();
			ArrayList<String> majors = sc.getMajors();
			ArrayList<String> done = new ArrayList<String>();
			ArrayList<Student> students = new ArrayList<Student>();
			ArrayList<String> tabs = new ArrayList<String>();
			for(int i = 0; i < majors.size(); i++) {
				if(!done.contains(majors.get(i))) {
					students.addAll(sc.getStudentsbyMajor(majors.get(i)));
					done.add(majors.get(i));
				}
			}
			for(int i = 0; i < students.size(); i++) {
				if( sc.getRevTotal(students.get(i).getprofID())> 0) {
					tabs.add(students.get(i).getMajor() + " " + students.get(i).getUserName() + " " + sc.getRevTotal(students.get(i).getprofID()));
				}
			}
			req.getSession().setAttribute("tabs", tabs);
			req.getRequestDispatcher("/_view/studentoverview.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		// call JSP to generate empty form
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			System.out.println("Login Servlet: doPost");
		
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		// now call the JSP to render the new page	
	}
}
