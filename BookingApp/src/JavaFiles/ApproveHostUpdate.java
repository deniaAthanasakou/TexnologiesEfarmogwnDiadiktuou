package JavaFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApproveHostUpdate {
	
	public boolean approveHost(String username) throws ClassNotFoundException {
		try
		{
			System.out.println("INSIDE APPROVE");
			Statement stmt = ConnectionManager.getConnection().createStatement();

			//check for same username before insert
			String checkQuery = "SELECT * FROM User WHERE username=" + "'" + username + "'";
			System.out.println(checkQuery);

			ResultSet rs = stmt.executeQuery(checkQuery);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;
			String user_id = rs.getString("user_id");
		
			if(rowCount==1) {
				System.out.println("EXISTS");
				String updateUserTable = "UPDATE User SET approved=0 WHERE user_id="+ user_id;
				System.out.println("udpate user query: " + updateUserTable);
				stmt.executeUpdate(updateUserTable);
				String updateProcessApproval = "UPDATE ProcessApproval SET approved=1 WHERE host_id="+user_id;
				System.out.println("udpate process query: " + updateProcessApproval);
				stmt.executeUpdate(updateProcessApproval);
				stmt.close();
				return true;
			}
			else {
				System.out.println("User does not exist.");
				return false;
			}
		}
		catch(SQLException e) {
			System.err.println("Got an SQL exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public boolean unapproveHost(String username) throws ClassNotFoundException {
		try
		{
			System.out.println("INSIDE DISSAPPROVE");
			Statement stmt = ConnectionManager.getConnection().createStatement();

			//check for same username before insert
			String checkQuery = "SELECT * FROM User WHERE username=" + "'" + username + "'";
			System.out.println(checkQuery);

			ResultSet rs = stmt.executeQuery(checkQuery);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;
			String user_id = rs.getString("user_id");
		
			if(rowCount==1) {
				System.out.println("EXISTS" + user_id);
				String updateUserTable = "UPDATE User SET approved=1 WHERE user_id="+ user_id;
				stmt.executeUpdate(updateUserTable);
				String updateProcessApproval = "UPDATE ProcessApproval SET approved=1 WHERE host_id="+user_id;
				stmt.executeUpdate(updateProcessApproval);
				stmt.close();
				return true;
			}
			else {
				System.out.println("User does not exist.");
				return false;
			}
		}
		catch(SQLException e) {
			System.err.println("Got an SQL exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
}
