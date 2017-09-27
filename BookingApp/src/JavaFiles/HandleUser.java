package JavaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import Entities.User;

public class HandleUser {

	public static int insertUser(User newUser)
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
			String checkQuery = "SELECT username FROM User WHERE username=" + "'" + newUser.getUsername() + "'";
			ResultSet rs = stmt.executeQuery(checkQuery);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String afterFormat = null;
			if(newUser.getBirthday()!=null) {
				afterFormat = sdf.format(newUser.getBirthday());
			}
		
			if(rowCount==0) {
				
				//insert query
				String insertQuery = "INSERT INTO User (username, password, name, surname, email, tel, role_host, role_tenant,";
				if(newUser.getPhoto()!=null) {
					insertQuery +=  "photo,";  
				}
				insertQuery += "address_number, street_address, city, country, postal_code";
				if(newUser.getBirthday()!=null) {
					insertQuery += ",birthday)";
				}
				else {
					insertQuery += ")";
				}
				
				insertQuery += " VALUES ("
						+ "'" + newUser.getUsername() + "'" +  ","
						+ "'" + newUser.getPassword() + "'" +  ","
						+ "'" + newUser.getName() + "'" +  ","
						+ "'" + newUser.getSurname() + "'" +  ","
						+ "'" + newUser.getEmail() + "'" +  ","
						+ "'" + newUser.getTel() + "'" +  ","
						+ newUser.getRoleHost() +  ","
						+ newUser.getRoleTenant() +  ",";
				
				if(newUser.getPhoto()!=null) {
					insertQuery += "'" + newUser.getPhoto() + "'" +  "," ; 
				}
				
				insertQuery += newUser.getAddressNumber() +  ","
						+ "'" + newUser.getStreetAddress() + "'" +  ","
						+ "'" + newUser.getCity() + "'" +  ","
						+ "'" + newUser.getCountry() + "'" +  ","
						+ "'" + newUser.getPostalCode() + "'";
				
				if(newUser.getBirthday()!=null) {
					insertQuery += "," + "'" + afterFormat + "'" + ")";
				}
				else {
					insertQuery += ")";
				}
				
				System.out.println("INSERT: " + insertQuery);
				
				int colCount = stmt.executeUpdate(insertQuery);
				if(colCount==-1) {
					System.out.println("An error has occured.");
				}
				else {
					System.out.println("Insert completed successfully!");
				}
				stmt.close();
				return 0;
			}
			else {
				System.out.println("User already exists.");
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
