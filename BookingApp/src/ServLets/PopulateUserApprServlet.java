package ServLets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import JavaFiles.PopulateUser;

/**
 * Servlet implementation class PopulateUserServlet
 */
@WebServlet("/PopulateUserApprServlet")
public class PopulateUserApprServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateUserApprServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Thread.currentThread();
			Thread.sleep((long) Math.random() * 1000);
		    Cookie[] cookies = null;
		    // Get an array of Cookies associated with this domain
		    cookies = request.getCookies();
		    String username=null;
		    for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					//do something
					username = cookie.getValue();
				}

			}
			PopulateUser populateProf = new PopulateUser();
			HashMap<String,String> populateInfo = new HashMap<String,String>(populateProf.populateProfile(username));
			JSONObject userInfo = new JSONObject(populateInfo);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(userInfo.toString());
			response.getWriter().flush();
			response.getWriter().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
