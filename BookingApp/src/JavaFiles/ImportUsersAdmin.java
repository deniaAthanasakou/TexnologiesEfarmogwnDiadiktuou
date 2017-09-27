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
			// create our mysql database connection
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/mydb?useSSL=true";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root1234");

			Statement stmt = conn.createStatement();

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
