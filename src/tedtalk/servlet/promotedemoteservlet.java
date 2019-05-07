package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalkDB.Controller.NetworkAdminController;

public class promotedemoteservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private int role;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Promote Demote Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");
		
		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			role = (int) req.getSession().getAttribute("role");
			if(role == 0) {
				req.getRequestDispatcher("/_view/promoteDemote.jsp").forward(req, resp);
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Promote Demote Servlet: doPost");
		
		NetworkAdminController NAController = new NetworkAdminController();

		String user = req.getParameter("user");
		String promo = req.getParameter("promo");
		boolean promote = false;
		
		if(promo == "promote") {
			promote = true;
		}
		
		NAController.promoteDemote(promote, user);
		
		req.setAttribute("user", user);
		req.setAttribute("promote", promo);
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/promoteDemote.jsp").forward(req, resp);
	}
}