package ServLets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import Entities.SearchFilters;
import JavaFiles.GetAptsFromWelcomeSearchFilterSQL;
import JavaFiles.GetAptsFromWelcomeSearchSQL;
/**
 * Servlet implementation class GetAptsFromWelcomeFilterSearch
 */
@WebServlet("/GetAptsFromWelcomeFilterSearch")
public class GetAptsFromWelcomeFilterSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAptsFromWelcomeFilterSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get fields
		String roomType = request.getParameter("roomType");
		String maxC = request.getParameter("maxCost");
		int maxCost = 0;					//default
		if(maxC!=null) {
			maxCost = Integer.parseInt(request.getParameter("maxCost"));
		}
		
		boolean wifi = false;
		boolean aircondition = false;
		boolean heating = false;
		boolean kitchen = false;		
		boolean tv = false;		
		boolean parking = false;
		boolean elevator= false;
		
		if(request.getParameter("wifi").equals("true")){
			wifi = true;
		}
		if(request.getParameter("aircondition").equals("true")){
			 aircondition = true;
		}
		if(request.getParameter("heating").equals("true")){
			heating = true;
		}
		if(request.getParameter("kitchen").equals("true")){
			kitchen = true;
		}
		if(request.getParameter("tv").equals("true")){
			tv = true;
		}
		if(request.getParameter("parking").equals("true")){
			parking = true;
		}
		if(request.getParameter("elevator").equals("true")){
			elevator = true;
		}
		
		Cookie[] cookies = null;
		// Get an array of Cookies associated with this domain
		String neighborhood="";

		String from = "";
		String to = "";
		String tenants = "";
		cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("neighborhood")) {
				neighborhood = cookie.getValue();
			}
			if (cookie.getName().equals("from")) {
				from = cookie.getValue();
			}
			if (cookie.getName().equals("to")) {
				to = cookie.getValue();
			}
			if (cookie.getName().equals("people")) {
				tenants = cookie.getValue();
			}
		}
		
		
		SearchFilters filters = new SearchFilters(roomType,maxCost,wifi,aircondition,heating,kitchen,tv,parking,elevator,neighborhood, from, to, tenants);
				
		GetAptsFromWelcomeSearchFilterSQL search = new GetAptsFromWelcomeSearchFilterSQL();
		String allApts = search.ExecuteQuery(filters);
    	JSONObject aptsInfo=null;
		try {
			aptsInfo = new JSONObject(allApts);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(aptsInfo.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
	}

}
