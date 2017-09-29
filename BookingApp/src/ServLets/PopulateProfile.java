package ServLets;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONObject;

import JavaFiles.PopulateUser;

/**
 * Servlet implementation class PopulateUser
 */
@WebServlet("/PopulateUser")
public class PopulateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PopulateProfile() {
		super();
		// TODO Auto-generated constructor stub
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

		System.out.println("INSIDE POPULATE PROFILE: PSS ");
		try {
			System.out.println("INSIDE POPULATE PROFILE: ");
			HttpSession session = request.getSession(false); 
			if (session != null) {
				String username = (String) session.getAttribute("username");
				System.out.println("Populate PROFILE Servlet: " + username);
				PopulateUser populateProf = new PopulateUser();
				HashMap<String,String> populateInfo = new HashMap<String,String>(populateProf.populateProfile(username));
				JSONObject userInfo = new JSONObject(populateInfo);
				System.out.println("RESULT SET PROFILE: " + populateInfo.get("username"));
				System.out.println("JSON: " + userInfo.toString());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(userInfo.toString());
				response.getWriter().flush();
				response.getWriter().close();
			}
		}catch(Exception e) {
			PrintStream s = null;
			e.printStackTrace(s);
		}
	}

}
