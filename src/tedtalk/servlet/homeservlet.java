package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tedtalkDB.model.Review;
import tedtalkDB.persist.*;
import tedtalk.model.ProfileModel;
import tedtalk.controller.ProfileController;
import java.util.ArrayList;

public class homeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DerbyDatabase derby = new DerbyDatabase();//DERBY INSTANCE come out
	private String username = null;
  //TEMPORARY UNTIL GET QUERY UP FROM ADRIAN
	//come out
	private int role;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Home Servlet: doGet");	
		
		req.setAttribute("description", null);
		req.setAttribute("presenterName", null);
		req.setAttribute("url", null);
		req.setAttribute("tag", null);
		req.setAttribute("name", null);
		username = (String) req.getSession().getAttribute("username");
		//comeout

		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			//come out
			//FIND REVIEW AND RETURN ALL ATTRIBUTES OF REVIEW:
			
			role = (int) req.getSession().getAttribute("role");
			if(role==0) {
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/networkadminHome");
			}
			else if(role==1) {
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/professorHome");
			}
			else if(role ==2) {
				System.out.println("Login Servlet: Login Successful");
				resp.sendRedirect(req.getContextPath() + "/studentHome");
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//comeout
		HttpSession session = req.getSession(true);
		//SET TOP REVIEW INTO SESSION:
		
		role = (int) req.getSession().getAttribute("role");
		if(role==0) {
			System.out.println("Login Servlet: Login Successful");
			resp.sendRedirect(req.getContextPath() + "/networkadminHome");
		}
		else if(role==1) {
			System.out.println("Login Servlet: Login Successful");
			resp.sendRedirect(req.getContextPath() + "/professorHome");
		}
		else if(role ==2) {
			System.out.println("Login Servlet: Login Successful");
			resp.sendRedirect(req.getContextPath() + "/studentHome");
		}
	}
	
}