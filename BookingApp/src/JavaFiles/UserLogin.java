package JavaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserLogin {

	public boolean loginUser(String username, String password)
	{
		try
		{
			Statement stmt = ConnectionManager.getConnection().createStatement();
			
			String checkQuery = "SELECT username,password FROM User WHERE username=" + "'" + username + "'"
							+ " AND password=" + "'" + password + "'";
			
			//System.out.println("QUERY: " + checkQuery);
			ResultSet rs = stmt.executeQuery(checkQuery);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;
		
			if(rowCount==0) {
				return false;
			}else {
				return true;
			}
			
		}
		catch (Exception e)
		{
			System.err.println("Got an SQL exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}

}
