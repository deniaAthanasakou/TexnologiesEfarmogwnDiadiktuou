package ServLets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		
		System.out.println("GETTT");
		
		ImportUsersAdmin importUsers = new ImportUsersAdmin();
		ResultSet rs = importUsers.populateAdminHandle();
		try {
			rs.beforeFirst();
			String insideTable = "";
			int counterRow = 1;
			while(rs.next()) {
				String username = rs.getString("username");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				byte roleHost = rs.getByte("role_host");
				byte roleTenant = rs.getByte("role_tenant");
				String roles = "";
				insideTable += "<tr>" + "<th scope=\"row\">" + counterRow + "</th>" 
							+ "<td id=\"username\">" + username + "</td>"
							+ "<td>" + name + "</td>"
							+ "<td>" + surname + "</td>";
				
				if(roleTenant==1) {
					roles += "Ενοικιαστής";
				}
				
				if(roleHost==1) {
					roles += "/Οικοδεσπότης";
				}
				
				insideTable += "<td>" + roles + "</td>"
							+ "<td><button class=\"btn btn-md btn-success\"><i class=\"glyphicon glyphicon-ok\"></i> Έγκριση</button></td>"
							+ "</tr>";
				counterRow++;
			}
			
			System.out.println(insideTable);
			response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
		    response.getWriter().write(insideTable);       
		} catch (SQLException e) {
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
