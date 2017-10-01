package ServLets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import JavaFiles.ApartmentWithDetailsSql;
import JavaFiles.GetAptsOfHostSql;

/**
 * Servlet implementation class ApartmentWithDetails
 */
@WebServlet("/ApartmentWithDetails")
public class ApartmentWithDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApartmentWithDetails() {
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

		//get room_id
		String room_id = "";
		String date_fromTenant = "";
		String date_toTenant = "";
		
		Cookie[] cookies = null;
		
		cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			
			if (cookie.getName().equals("from")) {
				date_fromTenant = cookie.getValue();
			}
			if (cookie.getName().equals("to")) {
				date_toTenant = cookie.getValue();
			}
			
			if (cookie.getName().equals("room_id")) {
				room_id = cookie.getValue();
			}
			
		}
		
		
		ApartmentWithDetailsSql myClass = new ApartmentWithDetailsSql();
		
		String apt = myClass.getApt(room_id,date_fromTenant , date_toTenant);
    	JSONObject aptInfo=null;
    	
		try {
			aptInfo = new JSONObject(apt);
			Cookie host_id = new Cookie("host_id",aptInfo.getString("host_id"));
			host_id.setMaxAge(60*60*24);
			response.addCookie(host_id);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(aptInfo.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
