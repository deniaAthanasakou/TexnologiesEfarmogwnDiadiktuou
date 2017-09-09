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
        System.out.println("INSIDE TRYYYY");

      // create our mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/mydb";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "root1@");
      
      System.out.println("INSIDE TRYYYY " + conn.isValid(1000));

      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
     // String query = "SELECT * FROM User";
      
      //String query = "INSERT INTO User VALUES (00000000, 'evagg', '1@3$kk2g', 'Evaggelos', 'Papadopoulos', 'evagPap@gmail.com', 6923673211, 0, 0, 1, , 12, 'Efstratiou', 'Thessaloniki', 'Ellada', 11098, , 06/07/1975)";
      String query = "INSERT INTO User (user_id, username, password, name, surname, email, tel, role_host, role_tenant, role_admin,address_number,city, country, postal_code,birthday)"+
      "VALUES (00000009, 'evagg', '1@3$kk2g', 'Evaggelos', 'Papadopoulos', 'evagPap@gmail.com', 6923673211, 0, 0, 1,  12, 'Efstratiou', 'Thessaloniki', 'Ellada', 11098,  06/07/1975)";

      System.out.println(query);

      // create the java statement
      Statement stmt = conn.createStatement();

      
      // execute the query, and get a java resultset
      ResultSet rs = stmt.executeQuery(query);
      
      //query = "SELECT * FROM User";
      //stmt = conn.createStatement();
      //rs = stmt.executeQuery(query);
      
      if (rs.next() == false) { 
    	  System.out.println("ResultSet is empty in Java"); 
      }

      // iterate through the java resultset
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