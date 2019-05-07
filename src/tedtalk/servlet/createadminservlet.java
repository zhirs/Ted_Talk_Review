package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.Controller.NetworkAdminController;
import tedtalkDB.model.NetworkAdmin;

public class createadminservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private int role;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Admin Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			role = (int) req.getSession().getAttribute("role");
			System.out.println(role);
			if(role == 0) {
				req.getRequestDispatcher("/_view/createAdmin.jsp").forward(req, resp);
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Admin Servlet: doPost");
		
		NetworkAdminController NAController = new NetworkAdminController();
		NetworkAdmin handle = new NetworkAdmin();
		
		NAController.addNetworkAdmins(handle.getUserName(), handle.getPassword(), handle.getEmail(), 0);
		
		req.setAttribute("adminHandle", handle);//CREATING AN ATTRIB TO USE IN JSP
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/createAdmin.jsp").forward(req, resp);
	}
	
}
