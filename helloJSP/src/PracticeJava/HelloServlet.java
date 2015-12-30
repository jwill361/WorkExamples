package PracticeJava;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * This is a simple example of combining JSP and servlets. 
 */

@WebServlet(
		name = "HelloServlet", 
		urlPatterns = {"/hello", "/hello/*", "/Hello" , "/Hello/*"}
)

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//Gets the action and displays page
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "hello";
		switch (action) {
		case "hello":
		default:
			this.sayHello(request, response);
			break;
		}

	}

	private void sayHello(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/view/hello.jsp").forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		//response.sendRedirect("/hello");
	}

}
