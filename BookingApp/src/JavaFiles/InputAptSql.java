package JavaFiles;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Entities.Apartments;

public class InputAptSql {
	public static void inputApt(Apartments apt)
	{
		System.out.println("INSIDE inputApt");

	    try
	    {
	        System.out.println("INSIDE TRYYYY");

	      // create our mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/mydb";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "root1@");
	      
	      System.out.println("INSIDE TRYYYY " + conn.isValid(1000));

	      Statement stmt = conn.createStatement();
	      
	      //insert into table Apartment
	      String query = "INSERT INTO Apartment (room_photo, cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom,capacity, max_tenants, min_cost_booking,cost_per_person";
	   //   System.out.println("first query is "+ query);	     
	      String queryPart2 ="VALUES (" + "'" +apt.getImagePath() + "'" +","+apt.getCost_per_day() + "," + "'" +apt.getType()+"'" + "," + apt.getNumber_rooms()+ "," + apt.getNumber_critics() +"," + apt.getAverage_critic()+ "," + apt.getNumber_beds() +"," + apt.getNumber_bathrooms() +"," + apt.getNumber_bedrooms()+ "," + apt.isLiving_room() + "," + apt.getCapacity() + "," + apt.getMax_tenants() + "," + apt.getMin_cost_booking() + "," + apt.getCost_per_person();
	  //    System.out.println("second query is "+ queryPart2);
	      
	      if(!apt.getDescription().equals("")) {
	    	  System.out.println("descr");
	    	  query+=",description";
	    	  queryPart2+=","+ "'" +apt.getDescription()+"'" ;
	      }
	      if(apt.getMore_photos()!=null) {
	    	  System.out.println("photos");
	    	  query+=",photos";
	    	  queryPart2+= ","+  apt.getMore_photos();
	      }
	      if(apt.getHost_id()!=-1) {	//auto tha afairethei meta
	    	  System.out.println("host_id");
	    	  query+=",host_id";
	    	  queryPart2+=","+ apt.getHost_id();
	      }
	      query+=")";
	      queryPart2+=")";
	      
	      String finalQuery = query + queryPart2;
	      
	      System.out.println("query is "+ finalQuery);
	   
	      
	     // String query = "INSERT INTO Apartment (room_id, room_photo, cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom,capacity,description, max_tenants, min_cost_booking,cost_per_person, host_id)"+
	     // "VALUES ('12432',3, 60, 'Domatio', 1, 17, 4, 2, 1, 1, 0, 20, 'Polu wraio kai aneto', 2, 30, 30,'0')";

	    //  stmt.executeUpdate(query);
	      
	      
	      query = "INSERT INTO Rule (smoking_allowed, pets_allowed, events, min_days_booking)";
	     // System.out.println("first query is "+ query);	     
	      queryPart2 ="VALUES ("+apt.isSmoking_allowed() + ","+apt.isPets_allowed() + "," +apt.isEvents()+ "," + apt.getMin_days_booking()+")";
	   //   System.out.println("second query is "+ queryPart2);
	      
	      finalQuery = query + queryPart2;
	      
	      System.out.println("query is "+ finalQuery);
	      
	      query = "INSERT INTO Facilities (wifi, aircondition, heating, kitchen, tv, parking, elevator)";
	    //  System.out.println("first query is "+ query);	     
	      queryPart2 ="VALUES ("+apt.isWifi() + ","+apt.isAircondition() + "," +apt.isHeating()+ "," + apt.isKitchen()+"," + apt.isTv()+"," + apt.isParking()+"," + apt.isElevator()+")";
	    //  System.out.println("second query is "+ queryPart2);
	      
	      finalQuery = query + queryPart2;
	      
	      System.out.println("query is "+ finalQuery);
	      
	      
	      //exw valei map null
	      
	      query = "INSERT INTO Location (address_number, street, postal_code, city, country, neighborhood";
	     // System.out.println("first query is "+ query);	     
	      queryPart2 ="VALUES ("+apt.getAddress_number() + ","  +"'" +apt.getStreet()  +"'"+ "," +"'" +apt.getPostal_code() +"'"+ ","  +"'"+ apt.getCity() +"'"+"," +"'" + apt.getCountry()  +"'"+","  +"'"+ apt.getNeighborhood() +"'";
	     // System.out.println("second query is "+ queryPart2);
	      
	      if(!apt.getMap().equals("")) {	
	    	  System.out.println("map");
	    	  query+=",map";
	    	  queryPart2+=","+ "'" +apt.getMap() +"'" ;
	      }
	      	      
	      if(!apt.getTransportation().equals("")) {	
	    	  System.out.println("transportation");
	    	  query+=",transportation";
	    	  queryPart2+=","+ "'" +apt.getTransportation() +"'" ;
	      }
	      query+=")";
	      queryPart2+=")";
	      
	      
	      finalQuery = query + queryPart2;
	      
	      System.out.println("query is "+ finalQuery);
	      
	      
	      
	      //IN NEED OF FREE DATES INSERT
	      
	      
	      
	    //  System.out.println(query);
	 /*     String query1 = "SELECT * FROM Apartment";

	      
	      // execute the query, and get a java resultset
	       ResultSet rs = stmt.executeQuery(query1);
	      
	      
	      if (rs.next() == false) { 
	    	  System.out.println("ResultSet is empty in Java"); 
	      }
	      else if (rs.next() == true) {
	    	  System.out.println("ResultSet is NOT empty"); 
	      }
	      
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
}
