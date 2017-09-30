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
		String neighborhood = "";
		String fromDate = "02/05/2018";
		String toDate = "05/12/2020";
		String tenants = "1";
		
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
