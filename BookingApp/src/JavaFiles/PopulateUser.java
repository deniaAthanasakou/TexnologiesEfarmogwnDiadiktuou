package JavaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PopulateUser {

	public static int populateProfile(String username)
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
			String checkQuery = "SELECT * FROM User WHERE username=" + "'" + username + "'";
			ResultSet rs = stmt.executeQuery(checkQuery);
			stmt.close();
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;
		
			if(rowCount==0) {
				
				//populate profile 
				
				
				return 0;
			}
			else {
				System.out.println("User does not exist.");
				return 1;
			}
		}
		catch (Exception e)
		{
			System.err.println("Got an SQL exception! ");
			System.err.println(e.getMessage());
			return 2;
		}
	}
}
