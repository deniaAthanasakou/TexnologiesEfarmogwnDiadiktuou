package JavaFiles;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import antlr.collections.List;

import Entities.Facility;
import Entities.Freedate;
import Entities.Location;
import Entities.Apartment;
import Entities.Rule;

public class InputAptSql {
	public static void inputApt(Apartment apt, Location loc, Rule rules, Facility facilities, Freedate fd)
	{
		System.out.println("INSIDE inputApt");

	    try
	    {
	        System.out.println("INSIDE TRYYYY");

	      // create our mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/mydb?useSSL=true";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "root1@");
	      
	      System.out.println("INSIDE TRYYYY " + conn.isValid(1000));

	      Statement stmt = conn.createStatement();
	      
	      System.out.println(" string " +apt.getImagePath());
	      
	      
	      ArrayList<String> photoListString = new ArrayList<String>(Arrays.asList(apt.getImagePath().split(",")));
	      System.out.println(" string list " +photoListString);
	      ArrayList <byte[]> photoListBytes = new ArrayList <byte[]>();
	      
	      for(int i=0; i<photoListString.size(); i++) {
	    	  byte[] element= convertImageToBArray(photoListString.get(i).trim());
	    	  System.out.println("element " +element);
	    	  photoListBytes.add(element);
	      }
	      
	      System.out.println(" bytes " +photoListBytes);
	      
	      
	    //insert into table Apartment
	      String query = "INSERT INTO Apartment (room_photo, cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom,capacity, max_tenants, min_cost_booking,cost_per_person";
	      String queryPart2 ="VALUES (" + "'" +photoListBytes.get(0) + "'" +","+apt.getCostPerDay() + "," + "'" +apt.getType()+"'" + "," + apt.getNumberRooms()+ "," + apt.getNumberCritics() +"," + apt.getAverage_critic()+ "," + apt.getNumberBeds() +"," + apt.getNumberBathrooms() +"," + apt.getNumberBedrooms()+ "," + apt.getLivingRoom() + "," + apt.getCapacity() + "," + apt.getMaxTenants() + "," + apt.getMinCostBooking() + "," + apt.getCostPerPerson();
	      
	      if(!apt.getDescription().equals("")) {
	    	  query+=",description";
	    	  queryPart2+=","+ "'" +apt.getDescription()+"'" ;
	      }
	      
	      if(apt.getHostId()!=-1) {	//auto tha afairethei meta
	    	  System.out.println("host_id");
	    	  query+=",host_id";
	    	  queryPart2+=","+ apt.getHostId();
	      }
	      query+=")";
	      
	      queryPart2+=")";
	      
	      String finalQuery = query + queryPart2;
	      
	      System.out.println("query is "+ finalQuery);
	   
	      
	      stmt.executeUpdate(finalQuery,Statement.RETURN_GENERATED_KEYS);
	      ResultSet rs_id = stmt.getGeneratedKeys();
	      int room_id = -1;
	      if ( rs_id.next() ) {
	    	    // Retrieve the auto generated key(s).
	    	  	room_id = rs_id.getInt(1);
	    	    System.out.println("id "+ room_id);
	    	}
	      else {
	    	  System.out.println("id_not_found");
	      }
	      
	      String selectQuery = "SELECT room_id from Apartment WHERE room_photo = " + "'" +photoListBytes.get(0) + "'";
	      ResultSet rsSelect = stmt.executeQuery(selectQuery);
	      
	      // iterate through the java resultset
	      
	      rsSelect.beforeFirst();
	      String idSelect ="";
	      while (rsSelect.next())
	      {
	          idSelect = rsSelect.getString("room_id");
	      }  
	        // print the results
	        System.out.println("room id = "+ idSelect);
	      
        if(photoListBytes.size()>1) {
	    	  System.out.println("photos");
	    	  for(int i=1;i<photoListBytes.size();i++) {
	    		  finalQuery = "INSERT INTO MorePhotos (room_id,image) VALUES ("+room_id +","+ "'" +photoListBytes.get(i) + "'"+ ")";
	    	      System.out.println("query is "+ finalQuery);
	    	      stmt.executeUpdate(finalQuery);
	    	  }	
	      }
        
        
	      
	      query = "INSERT INTO Rule (room_id,smoking_allowed, pets_allowed, events, min_days_booking)";
	      queryPart2 ="VALUES ("+room_id +","+rules.getSmokingAllowed() + ","+rules.getPetsAllowed() + "," +rules.getEvents()+ "," + rules.getMinDaysBooking()+")";
	      finalQuery = query + queryPart2;
	      
	      System.out.println("query is "+ finalQuery);
	      stmt.executeUpdate(finalQuery);
	      
	      
	      
	      query = "INSERT INTO Facilities (room_id,wifi, aircondition, heating, kitchen, tv, parking, elevator)";
	      queryPart2 ="VALUES ("+room_id +","+facilities.getWifi() + ","+facilities.getAircondition() + "," +facilities.getHeating()+ "," + facilities.getKitchen()+"," + facilities.getTv()+"," + facilities.getParking()+"," + facilities.getElevator()+")";
	      finalQuery = query + queryPart2;
	      
	      System.out.println("query is "+ finalQuery);
	      stmt.executeUpdate(finalQuery);
	      
	      	      
	      query = "INSERT INTO Location (room_id,address_number, street, postal_code, city, country, neighborhood";
	      queryPart2 ="VALUES ("+room_id +","+loc.getAddressNumber() + ","  +"'" +loc.getStreet()  +"'"+ "," +"'" +loc.getPostalCode() +"'"+ ","  +"'"+ loc.getCity() +"'"+"," +"'" + loc.getCountry()  +"'"+","  +"'"+ loc.getNeighborhood() +"'";
	      
	      if(!loc.getMap().equals("")) {	
	    	  System.out.println("map");
	    	  query+=",map";
	    	  queryPart2+=","+ "'" +loc.getMap() +"'" ;
	      }
	      	      
	      if(!loc.getTransportation().equals("")) {	
	    	  System.out.println("transportation");
	    	  query+=",transportation";
	    	  queryPart2+=","+ "'" +loc.getTransportation() +"'" ;
	      }
	      query+=")";
	      queryPart2+=")";
	      
	      
	      finalQuery = query + queryPart2;
	      
	      System.out.println("query is "+ finalQuery);
	      stmt.executeUpdate(finalQuery);
	      
	      
	      
	      query = "INSERT INTO FreeDates (room_id,from, to)";
		  queryPart2 ="VALUES ("+room_id +","+fd.getFrom() + ","  +fd.getTo() +")" ;
		     
		  finalQuery = query + queryPart2;
	      
	      System.out.println("query is "+ finalQuery);
	      stmt.executeUpdate(finalQuery);
	      
	      
	      
	      
	 /*     String query1 = "SELECT * FROM Apartment";

	      
	      // execute the query, and get a java resultset
	       ResultSet rs = stmt.executeQuery(query1);
	      
	      // iterate through the java resultset
	      
	      rs.beforeFirst();
	      while (rs.next())
	      {
	          System.out.println("STMT ");

	          String id = rs.getString("room_id");
	        
	        // print the results
	        System.out.println("room id = "+ id);
	      }*/
	      stmt.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an SQL exception! ");
	      System.err.println(e.getMessage());
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
