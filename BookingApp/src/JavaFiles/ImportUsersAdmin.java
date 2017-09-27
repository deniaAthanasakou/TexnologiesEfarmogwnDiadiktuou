package JavaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImportUsersAdmin {

	public ResultSet populateAdminHandle()
	{
		try
		{
			Statement stmt = ConnectionManager.getConnection().createStatement();

			//check for same username before insert
			String results = "SELECT username,name,surname,role_host,role_tenant,approved FROM User";
			ResultSet rs = stmt.executeQuery(results);
			rs.beforeFirst();
			
			return rs;
		}
		catch (Exception e)
		{
			System.err.println("Got an SQL exception! ");
			System.err.println(e.getMessage());
			return null;
		}
		
	}
}
