package ServLets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import JavaFiles.GetAptsFromWelcomeSearchSQL;

/**
 * Servlet implementation class GetAptsFromWelcomeSearch
 */
@WebServlet("/GetAptsFromWelcomeSearch")
public class GetAptsFromWelcomeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAptsFromWelcomeSearch() {
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
		System.out.println("POPULATE FROM WELCOME");
		Cookie[] cookies = null;
		// Get an array of Cookies associated with this domain
		String neighborhood="";

		String fromDate = "";
		String toDate = "";
		String tenants = "";
		cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("neighborhood")) {
				//do something
				neighborhood = cookie.getValue();
			}
			if (cookie.getName().equals("from")) {
				//do something
				fromDate = cookie.getValue();
			}
			if (cookie.getName().equals("to")) {
				//do something
				toDate = cookie.getValue();
			}
			if (cookie.getName().equals("people")) {
				//do something
				tenants = cookie.getValue();
			}
		}
		
		GetAptsFromWelcomeSearchSQL search = new GetAptsFromWelcomeSearchSQL();
		String allApts = search.ExecuteQuery(neighborhood,fromDate,toDate,tenants);
    		JSONObject aptsInfo=null;
		try {
			aptsInfo = new JSONObject(allApts);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(aptsInfo.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("JSON: " + aptsInfo.toString());
		
	}

}
