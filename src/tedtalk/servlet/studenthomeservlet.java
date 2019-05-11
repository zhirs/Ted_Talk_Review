package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class studenthomeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = null;
	private String show[] = new String[5];
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Student Home Servlet: doGet");	
		username = (String) req.getSession().getAttribute("username");

		// call JSP to generate empty form
		if(username == null) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			show = (String[]) req.getSession().getAttribute("TopURL");
			
			req.setAttribute("review0", show[0]);
			req.setAttribute("review1", show[1]);
			req.setAttribute("review2", show[2]);
			req.setAttribute("review3", show[3]);
			req.setAttribute("review4", show[4]);
			req.getRequestDispatcher("/_view/studentHome.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Student Home Servlet: doPost");
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/studentHome.jsp").forward(req, resp);
	}
	
}
