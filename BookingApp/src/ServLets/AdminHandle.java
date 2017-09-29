package ServLets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import JavaFiles.ImportUsersAdmin;

/**
 * Servlet implementation class AdminHandle
 */
@WebServlet("/AdminHandle")
public class AdminHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHandle() {
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
				
		ImportUsersAdmin importUsers = new ImportUsersAdmin();
		ResultSet rs = importUsers.populateAdminHandle();
		try {
			rs.beforeFirst();
			int counterRow = 1;
			JSONObject  Users      = new JSONObject();
			JSONArray  json      = new JSONArray();
			while(rs.next()) {
				byte roleAdmin = rs.getByte("role_admin");
				if(roleAdmin==1) {
					rs.next();
				}
				String username = rs.getString("username");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				byte roleHost = rs.getByte("role_host");
				byte roleTenant = rs.getByte("role_tenant");
				byte approved = rs.getByte("approved");
				String roles = "";
				String approval ="";
				byte processed = rs.getByte("processed");
				
				if(roleTenant==1) {
					roles += "Ενοικιαστής";
				}
				
				if(roleHost==1) {
					roles += "/Οικοδεσπότης";
				}
				
				
				if(roleHost==1 && approved==1 && processed==0) {
					approval = "<button class=\"btn btn-md btn-success\" id=\"button_approve\"><i class=\"glyphicon glyphicon-ok\"></i> Έγκριση</button>";
				}
				else if(roleHost==1 && approved==0 && processed==1){
					approval = "Εγκρίθηκε";
				}
				else if(roleHost==1 && approved==1 && processed==1){
					approval = "Απορρίφθηκε";
				}
				
				JSONArray user = new JSONArray();
				
				//add to jsonobj
				user.put(counterRow);
				user.put(username);
				user.put(name);
				user.put(surname);
				user.put(roles);
				user.put(approval);
				
				json.put(user);
				counterRow++;
				
			}
			
			Users.put("Users", json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json.toString());
			response.getWriter().flush();
			response.getWriter().close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
