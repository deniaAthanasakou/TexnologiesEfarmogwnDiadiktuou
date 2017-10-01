package ServLets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaFiles.ApproveHostUpdate;

/**
 * Servlet implementation class UnapproveHost
 */
@WebServlet("/UnapproveHost")
public class UnapproveHost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnapproveHost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = null;
	    // Get an array of Cookies associated with this domain
	    cookies = request.getCookies();
	    String username = cookies[0].getValue();
		ApproveHostUpdate approve = new ApproveHostUpdate();
		try {
			if(approve.unapproveHost(username)) {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Ο οικοδεσπότης απορρίφθηκε με επιτυχία!");
				response.getWriter().flush();
				response.getWriter().close();
				System.out.println("The host was unapproved successfully!");
			}else {
				System.out.println("There was an error with unapproving the host.");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
