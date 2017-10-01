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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;

import antlr.collections.List;

import Entities.Facility;
import Entities.Freedate;
import Entities.Location;
import Entities.Apartment;
import Entities.Rule;

import JavaFiles.ImgToBArray;

public class InputAptSql {
	public void inputApt(Apartment apt, Location loc, Rule rules, Facility facilities, Freedate fd)
	{
	    try
	    {

	      Statement stmt = ConnectionManager.getConnection().createStatement();
	     	  	      
	      ArrayList<String> photoListString = new ArrayList<String>(Arrays.asList(apt.getImagePath().split(",")));
	      ArrayList <byte[]> photoListBytes = new ArrayList <byte[]>();
	      
	      ImgToBArray imgToArray = new ImgToBArray();
	      
	      for(int i=0; i<photoListString.size(); i++) {
	    	  byte[] element= imgToArray.convertImageToBArray(photoListString.get(i).trim());
	    	  photoListBytes.add(element);
	      }
	      
	      
	      
	    //insert into table Apartment
	      String query = "INSERT INTO Apartment (room_photo, host_id, cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom,capacity, max_tenants, min_cost_booking,cost_per_person";
	      String queryPart2 =" VALUES (" + "'" +photoListBytes.get(0) + "'" +","+ apt.getHostId()+ ","+ apt.getCostPerDay() + "," + "'" +apt.getType()+"'" + "," + apt.getNumberRooms()+ "," + apt.getNumberCritics() +"," + apt.getAverage_critic()+ "," + apt.getNumberBeds() +"," + apt.getNumberBathrooms() +"," + apt.getNumberBedrooms()+ "," + apt.getLivingRoom() + "," + apt.getCapacity() + "," + apt.getMaxTenants() + "," + apt.getMinCostBooking() + "," + apt.getCostPerPerson();
	      
	      if(!apt.getDescription().equals("")) {
	    	  query+=",description";
	    	  queryPart2+=","+ "'" +apt.getDescription()+"'" ;
	      }
	      
	      query+=")";
	      
	      queryPart2+=")";
	      
	      String finalQuery = query + queryPart2;
	      	   
	      
	      stmt.executeUpdate(finalQuery,Statement.RETURN_GENERATED_KEYS);
	      ResultSet rs_id = stmt.getGeneratedKeys();
	      int room_id = -1;
	      if ( rs_id.next() ) {
	    	    // Retrieve the auto generated key(s).
	    	  	room_id = rs_id.getInt(1);
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
	      
        if(photoListBytes.size()>1) {
	    	  System.out.println("photos");
	    	  for(int i=1;i<photoListBytes.size();i++) {
	    		  finalQuery = "INSERT INTO MorePhotos (room_id,image) VALUES ("+room_id +","+ "'" +photoListBytes.get(i) + "'"+ ")";
	    	      stmt.executeUpdate(finalQuery);
	    	  }	
	      }
        
        
	      
	      query = "INSERT INTO Rule (room_id,smoking_allowed, pets_allowed, events, min_days_booking)";
	      queryPart2 =" VALUES ("+room_id +","+rules.getSmokingAllowed() + ","+rules.getPetsAllowed() + "," +rules.getEvents()+ "," + rules.getMinDaysBooking()+")";
	      finalQuery = query + queryPart2;
	      
	      stmt.executeUpdate(finalQuery);
	      
	      
	      
	      query = "INSERT INTO Facilities (room_id,wifi, aircondition, heating, kitchen, tv, parking, elevator)";
	      queryPart2 =" VALUES ("+room_id +","+facilities.getWifi() + ","+facilities.getAircondition() + "," +facilities.getHeating()+ "," + facilities.getKitchen()+"," + facilities.getTv()+"," + facilities.getParking()+"," + facilities.getElevator()+")";
	      finalQuery = query + queryPart2;
	      
	      stmt.executeUpdate(finalQuery);
	      
	      	      
	      query = "INSERT INTO Location (room_id,address_number, street, postal_code, city, country, neighborhood, map";
	      queryPart2 =" VALUES ("+room_id +","+loc.getAddressNumber() + ","  +"'" +loc.getStreet()  +"'"+ "," +"'" +loc.getPostalCode() +"'"+ ","  +"'"+ loc.getCity() +"'"+"," +"'" + loc.getCountry()  +"'"+","  +"'"+ loc.getNeighborhood() +"'" +" ," + "'" +loc.getMap() +"'";
	      
	      	      
	      if(!loc.getTransportation().equals("")) {	
	    	  query+=",transportation";
	    	  queryPart2+=","+ "'" +loc.getTransportation() +"'" ;
	      }
	      query+=")";
	      queryPart2+=")";
	      
	      
	      finalQuery = query + queryPart2;
	      
	      stmt.executeUpdate(finalQuery);
	      
	      
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      Date from = fd.getFrom();
	      Date to = fd.getTo();

	      
	      
	      
	      query = "INSERT INTO FreeDates (room_id,from_date, to_date)";
		  queryPart2 =" VALUES ("+room_id +","+ "'" + sdf.format(from) + "'"  + ","  + "'" + sdf.format(to) + "'" +")" ;
		     
		  finalQuery = query + queryPart2;
	      
	      stmt.executeUpdate(finalQuery);
	      
	      
	      stmt.close();
	    }
	    catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
