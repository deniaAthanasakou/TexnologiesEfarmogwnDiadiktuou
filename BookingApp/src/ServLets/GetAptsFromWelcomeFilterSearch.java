package ServLets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import Entities.SearchFilters;
import JavaFiles.GetAptsFromWelcomeSearchFilterSQL;
/**
 * Servlet implementation class GetAptsFromWelcomeSearch
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("INSIDE GetAptsFromWelcomeSearch SERVLET");
		
		//get fields
		String roomType = request.getParameter("roomType");
		int maxCost = Integer.parseInt(request.getParameter("maxCost"));
		String wifi = request.getParameter("wifi");
		String aircondition = request.getParameter("aircondition");
		String heating = request.getParameter("heating");
		String kitchen = request.getParameter("kitchen");
		String tv = request.getParameter("tv");
		String parking = request.getParameter("parking");
		String elevator = request.getParameter("elevator");
		String neighborhood = "";								//default
		String from ="2017-12-01";
		String to ="2018-02-01";
		
		//somehow get neighborhood
		
		System.out.println("roomType" + roomType +"!"+ wifi);
		SearchFilters filters = new SearchFilters(roomType,maxCost,wifi,aircondition,heating,kitchen,tv,parking,elevator,neighborhood, from, to);
		
		GetAptsFromWelcomeSearchFilterSQL getApts = new GetAptsFromWelcomeSearchFilterSQL();
		
		if (request.getParameter("submit") != null) {
			
        	System.out.println("BEFORE SELECT");
        	HashMap<Integer, HashMap<String, String>> allApts = getApts.ExecuteQuery(filters);
        	JSONObject aptsInfo = new JSONObject(allApts);
    		System.out.println("room_id: " + allApts.get(1).get("room_id"));
    		System.out.println("JSON: " + aptsInfo.toString());
    		response.setContentType("application/json");
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().write(aptsInfo.toString());
    		response.getWriter().flush();
    		response.getWriter().close();
        	
        	
        	
        	
            System.out.println("AFTER SELECT");
            
        } else {
        	System.out.println("SELECT DIDN'T HAPPEN");
        }
	}

}
