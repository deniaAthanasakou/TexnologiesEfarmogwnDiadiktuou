package ServLets;

import java.io.IOException;

import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckLoggedUser
 */
@WebServlet("/CheckLoggedUser")
public class CheckLoggedUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoggedUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
         
		//System.out.println("INSIDE CHECK LOGGED");
		try {
			
			HttpSession session = request.getSession(false); 
			if (session != null) {
				// Already created.
				response.getWriter().write("1");
				response.getWriter().flush();
				response.getWriter().close();

			}
	        
		}catch(Exception e) {
			PrintStream s = null;
			e.printStackTrace(s);
		}
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
