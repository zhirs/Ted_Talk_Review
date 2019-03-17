package tedtalk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tedtalk.model.TestModel;
import tedtalk.controller.TestController;

public class servlettest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("GuessingGame Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/test.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("GuessingGame Servlet: doPost");
		
		// create GuessingGame model - model does not persist between requests
		// must recreate it each time a Post comes in 
		TestModel model = new TestModel();

		// create GuessingGame controller - controller does not persist between requests
		// must recreate it each time a Post comes in
		TestController controller = new TestController();
		
		// assign model reference to controller so that controller can access model
		controller.setModel(model);
		
		// check if user is starting a new game and call controller method
		if (req.getParameter("login") != null) {
			controller.loginLoad();
		}
		// otherwise, user is already playing the game - get the old min and max
		// from the posted form
		// without persistence, we must pass the values back and forth between the
		// client and the server every time in order to remember them
		else {
			// get min and max from the Posted form data
			String user= getString(req, "Username");
			String pass= getString(req, "Password");
			
			// initialize model with the old min, max values
			// since the data does not persist between posts, we need to 
			// recreate and re-initialize the model each time
			model.setTestUser(user);
			model.setTestPass(pass);
			
			// now check to see which button the user pressed
			// and adjust min, max, and guess accordingly
			// must call controller methods to do this since the
			// view only reads the model data, it never changes
			// the model - only the controller can change the model
			if (req.getParameter("Verify Credentials") != null) {
				controller.testLogin();
			} else {
				throw new ServletException("Unknown command");
			}
		}
		
		// set "game" attribute to the model reference
		// the JSP will reference the model elements through "game"
		req.setAttribute("testModelAttrib", model);
		
		// now call the JSP to render the new page
		req.getRequestDispatcher("/_view/test.jsp").forward(req, resp);
	}

	private String getString(HttpServletRequest req, String string) {
		return String.valueOf(req.getParameter(string));
	}
}
