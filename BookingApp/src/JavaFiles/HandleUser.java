package JavaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HandleUser {

	public static void insertUser()
	{
		System.out.println("INSIDE insertUser");

		try
		{
			System.out.println("INSIDE TRYYYY");

			// create our mysql database connection
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/mydb";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root1234");

			System.out.println("INSIDE TRYYYY " + conn.isValid(1000));


			String query ="INSERT INTO User (user_id, username, password, name, surname, email, tel, role_host, role_tenant, role_admin,address_number,street_address,city, country, postal_code,birthday,comment_id) "+
			"VALUES (10, 'evagg', '1@3$kk2g', 'Evaggelos', 'Papadopoulos', 'evagPap@gmail.com', 6923673211, 0, 0, 1,  12, 'Efstratiou', 'Thessaloniki', 'Ellada', 11098,  06/07/1975,112)";

			Statement stmt = conn.createStatement();
			int colCount = stmt.executeUpdate(query);
			

			if(colCount==-1) {
				System.out.println("User already exists.");
			}
			else {
				System.out.println("Insert completed successfully!");
			}
			stmt.close();
		}
		catch (Exception e)
		{
			System.err.println("Got an SQL exception! ");
			System.err.println(e.getMessage());
		}
	}
}
