package JavaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaMysqlSelect
{

  public static void getLocation()
  {
      System.out.println("INSIDE GETLOCATION");

    try
    {
    	Statement stmt = ConnectionManager.getConnection().createStatement();
       //stmt.executeUpdate(query1);
      
     // System.out.println(query1);
      String query = "SELECT * FROM User";
      // create the java statement
      // stmt = conn.createStatement();

      
      // execute the query, and get a java resultset
       ResultSet rs = stmt.executeQuery(query);
      
      //query = "SELECT * FROM User";
      //stmt = conn.createStatement();
      //rs = stmt.executeQuery(query);
      
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

        int id = rs.getInt("user_id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        //Date dateCreated = rs.getDate("date_created");
        //boolean isAdmin = rs.getBoolean("is_admin");
        //int numPoints = rs.getInt("num_points");
        
        // print the results
        System.out.println(id + username+ password);
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