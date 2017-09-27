package ServLets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendDataPopulate
 */
@WebServlet("/SendDataPopulate")
public class SendDataPopulate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendDataPopulate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("INSIDE GET ADMIN HANDLE");
		String username = request.getParameter("username");
		System.out.println("USERNAME: " + username);
				
		// Create cookies for first and last names.      
	    Cookie usernameCookie = new Cookie("username", username);
	    // Set expiry date after 24 Hrs
	    usernameCookie.setMaxAge(60*60*24);
	    
	    System.out.println("before cookie print");
	    System.out.println("cookiee: " + usernameCookie.getValue()); 
	    response.addCookie(usernameCookie);
        
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("1");
		response.getWriter().flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
