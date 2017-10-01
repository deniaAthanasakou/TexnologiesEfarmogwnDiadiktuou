package JavaFiles;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import Entities.Apartment;
import Entities.Facility;
import Entities.Freedate;
import Entities.Location;
import Entities.Rule;

public class ChangeAptUpdateSql {
	
	public void updateApt(int room_id, Apartment apt, Location loc, Rule rules, Facility facilities, Freedate fd) {
		
		 try
		    {
		      Statement stmt = ConnectionManager.getConnection().createStatement();
		      String query1="UPDATE Apartment";
		      String query2=" SET capacity =" + apt.getCapacity() +", cost_per_day="+apt.getCostPerDay() +", cost_per_person=" +apt.getCostPerPerson()+
		    		  		",min_cost_booking = "+apt.getMinCostBooking() + 
		    		  		",number_bathrooms="+apt.getNumberBathrooms() + ", number_beds="+apt.getNumberBeds() + ", number_bedrooms = "+apt.getNumberBedrooms() +
		    		  		",number_rooms = "+apt.getNumberRooms() +", type ='" +apt.getType()+ "'";
		      
		      if(!apt.getDescription().equals("")) {
		    	  query2+= ", description = " + "'" +apt.getDescription() + "'";
		      }
		      else {
		    	  query2+= ", description = " + "'Den iparxei kapoia perigrafi tou xwrou.'";
		      }
		      
		      
		   /*   ArrayList<String> photoListString = new ArrayList<String>(Arrays.asList(apt.getImagePath().split(",")));
		      System.out.println(" string list " +photoListString);
		      ArrayList <byte[]> photoListBytes = new ArrayList <byte[]>();
		      
		      ImgToBArray imgToArray = new ImgToBArray();
		      
		      for(int i=0; i<photoListString.size(); i++) {
		    	  byte[] element= imgToArray.convertImageToBArray(photoListString.get(i).trim());
		    	  System.out.println("element " +element);
		    	  photoListBytes.add(element);
		      }
		      if(photoListBytes.size()>0) {		//must change image
		    	  query2+=", room_photo='"+photoListBytes.get(0) + "'";
		      }*/
		      
		      String query3=" WHERE room_id =" + room_id;
		      
		      String finalQuery = query1+query2+query3;
		      stmt.executeUpdate(finalQuery);

		      query1="UPDATE Facilities";
		      query2=" SET aircondition =" + facilities.getAircondition() + ", elevator=" +facilities.getElevator() + ", heating="+facilities.getHeating()+
		    		  ", kitchen="+facilities.getKitchen()  +", parking=" + facilities.getParking() + ",tv="+facilities.getTv()+ ", wifi="+ facilities.getWifi();
		      query3=" WHERE room_id =" + room_id;
		      
		      finalQuery = query1+query2+query3;
		      stmt.executeUpdate(finalQuery);
		      
		      
		      query1="UPDATE Rule";
		      query2=" SET smoking_allowed =" + rules.getSmokingAllowed() + ", pets_allowed=" +rules.getPetsAllowed() + ", events="+rules.getEvents()+", min_days_booking="+rules.getMinDaysBooking();
		      query3=" WHERE room_id =" + room_id;
		      
		      finalQuery = query1+query2+query3;
		      stmt.executeUpdate(finalQuery);
		      
		      
		  /*    //morephotos
			       if(photoListBytes.size()>1) {
	    	  		System.out.println("photos");
	    	  		//delete previous photos
	    	  		 finalQuery = "DELETE FROM MorePhotos WHERE room_id="+room_id;
	    	  		 stmt.executeUpdate(finalQuery);
	    	  		
	    	  		
			    	  for(int i=1;i<photoListBytes.size();i++) {
			    		  finalQuery = "INSERT INTO MorePhotos (room_id,image) VALUES ("+room_id +","+ "'" +photoListBytes.get(i) + "'"+ ")";
			    	      System.out.println("query is "+ finalQuery);
			    	      stmt.executeUpdate(finalQuery);
			    	  }	
			      }*/
		      
		      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		      Date from = fd.getFrom();
		      Date to = fd.getTo();
		      
		      query1="UPDATE FreeDates";
		      query2=" SET from_date ='" + sdf.format(from) + "'" + ", to_date='" +sdf.format(to) +  "'";
		      query3=" WHERE room_id =" + room_id;
		      finalQuery = query1+query2+query3;
		      stmt.executeUpdate(finalQuery);
		      
		      
		      query1="UPDATE Location";
		      query2=" SET street ='" + loc.getStreet() + "'" + ", city='" +loc.getCity() +  "'" + ", country='" +loc.getCountry() +  "'" + ", neighborhood='" +loc.getNeighborhood() +  "'" + ", map='" +loc.getMap() +  "'"+
		    		  ",address_number ="+ loc.getAddressNumber() + ", postal_code="+loc.getPostalCode();
		      
		      
		      if(!loc.getTransportation().equals("")) {
		    	  query2+= ", transportation = '" + loc.getTransportation() + "'";
		      }
		      else {
		    	  query2+= ", transportation = " + "'Den iparxei kapoia perigrafi gia metakinisi ston xwro mas.'";
		      }
		      
		      query3=" WHERE room_id =" + room_id;
		      
		      finalQuery = query1+query2+query3;
		      
		      stmt.executeUpdate(finalQuery);
		      stmt.close();
		      		      
		    }
		    catch (Exception e)
			{
				e.printStackTrace();
			}
				
	}

}
