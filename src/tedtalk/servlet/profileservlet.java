package tedtalk.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.model.Account;
import tedtalkDB.model.Review;
import tedtalk.controller.ReviewController;
import tedtalkDB.persist.DerbyDatabase;

public class profileservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private String email = null;
	private DerbyDatabase derby;
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
			//ProfileModel model = new ProfileModel();
			
			//ProfileController controller = new ProfileController();
		
			String errorMessage = null;
			//controller.setModel(model);
			derby = new DerbyDatabase();
			Account login = derby.setLogin(username);
			if(login == null) {
				System.out.println("This is null");
			}
			int role = derby.getRole(username);
			switch(role){
			case 0:
				req.getRequestDispatcher("/_view/networkadmin.jsp").forward(req, resp);
				break;
			case 1:	
				req.getRequestDispatcher("/_view/professor.jsp").forward(req, resp);
				break; 
			default:
				req.getRequestDispatcher("/_view/student.jsp").forward(req, resp);
			}
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
