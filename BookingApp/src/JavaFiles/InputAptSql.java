package JavaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class InputAptSql {
	public static void inputApt()
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
	      
	      String query = "INSERT INTO Apartment (room_id, cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom,capacity,description, max_tenants, min_cost_booking,cost_per_person)"+
	      "VALUES (0000000, 60, Domatio, 1, 17, 4, 2, 1, 1, 0, 20, Polu wraio kai aneto, 2, 30, 30)";

	      
	      System.out.println(query);
	      String query1 = "SELECT * FROM Apartment";

	      
	      // execute the query, and get a java resultset
	       ResultSet rs = stmt.executeQuery(query1);
	      
	      
	      if (rs.next() == false) { 
	    	  System.out.println("ResultSet is empty in Java"); 
	      }
	      else if (rs.next() == true) {
	    	  System.out.println("ResultSet is NOT empty"); 
	      }
	      
	      if (rs.next())
	      {
	    	  System.out.println("NOT NULL"); 
	      }
	      else {
	    	  System.out.println(" NULL"); 
	      }

	      // iterate through the java resultset
	      
	      rs.beforeFirst();
	      while (rs.next())
	      {
	          System.out.println("STMT ");

	          String id = rs.getString("room_id");
	        
	        // print the results
	        System.out.println(id);
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
