package JavaFiles;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


import Entities.Users;

public class HandleUser {

	public static int insertUser(Users newUser)
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
			
			int hostBoolean;
			int tenantBoolean;
			if(newUser.getHost()==null) {
				hostBoolean = 0;
			}
			else {
				hostBoolean = 1;
			}
			if(newUser.getTenant()==null) {
				tenantBoolean = 0;
			}
			else {
				tenantBoolean = 1;
			}

			Statement stmt = conn.createStatement();

			//check for same username before insert
			String checkQuery = "SELECT username FROM User WHERE username=" + "'" + newUser.getUsername() + "'";
			ResultSet rs = stmt.executeQuery(checkQuery);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;

			
			if(rowCount==0) {
				//insert query
				System.out.println("BYTE ARRAY: " + convertImageToBArray(newUser.getImagePath()) );
				
				String insertQuery = "INSERT INTO User (username, password, name, surname, email, tel, role_host, role_tenant, photo, address_number, street_address, city, country, postal_code, birthday, comment_id) "
						+ "VALUES ("
						+ "'" + newUser.getUsername() + "'" +  ","
						+ "'" + newUser.getPassword() + "'" +  ","
						+ "'" + newUser.getName() + "'" +  ","
						+ "'" + newUser.getSurname() + "'" +  ","
						+ "'" + newUser.getEmail() + "'" +  ","
						+ newUser.getTel() +  ","
						+ hostBoolean +  ","
						+ tenantBoolean +  ","
						+ "'" + convertImageToBArray(newUser.getImagePath()) + "'" +  ","
						+ newUser.getAddressNumber() +  ","
						+ "'" + newUser.getStreetAddress() + "'" +  ","
						+ "'" + newUser.getCity() + "'" +  ","
						+ "'" + newUser.getCountry() + "'" +  ","
						+ newUser.getPostalCode() +  ","
						+ "STR_TO_DATE( '" + newUser.getBirthday() +  "'," + " '%d/%m/%Y'),"
						+ 112 + ")";

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

	private static byte[] convertImageToBArray(String ImageName) throws IOException {

		// open image
		File imgPath = new File(ImageName);
		BufferedImage bufferedImage = ImageIO.read(imgPath);

		// get DataBufferBytes from Raster
		WritableRaster raster = bufferedImage .getRaster();
		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		return ( data.getData() );
	}
}
