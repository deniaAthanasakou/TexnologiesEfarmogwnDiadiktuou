package JavaFiles;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import Entities.User;

public class HandleUser {

	public static int insertUser(User newUser)
	{
		try
		{
			Statement stmt = ConnectionManager.getConnection().createStatement();

			//check for same username before insert
			String checkQuery = "SELECT * FROM User WHERE username=" + "'" + newUser.getUsername() + "'";
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
				
				
				int colCount = stmt.executeUpdate(insertQuery);
				if(colCount==-1) {
					System.out.println("An error has occured.");
				}
				else {
					System.out.println("Insert completed successfully!");
				}
				
				ResultSet rsCheck = stmt.executeQuery(checkQuery);
				rsCheck.beforeFirst();
				int rowCountCheck = rsCheck.last() ? rsCheck.getRow() : 0;
				
				rsCheck.first();
				if(rsCheck.first() && rowCountCheck==1) {
					String user_id = rsCheck.getString("user_id");
					if(newUser.getRoleHost()==1) {
						String insertApproval = "INSERT INTO ProcessApproval (approved, host_id) VALUES (false,"
											+ user_id + ")";
						stmt.executeUpdate(insertApproval);
					}
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
