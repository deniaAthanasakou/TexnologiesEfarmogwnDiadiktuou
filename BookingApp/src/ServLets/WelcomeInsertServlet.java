package ServLets;


import JavaFiles.JavaMysqlSelect;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/WelcomeInsertServlet")
public class WelcomeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WelcomeInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException
	{
		System.out.println("----------");
		System.out.println("---------- INSERT  ----------");
		System.out.println("----------");


		try
		{
			// create our mysql database connection
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/mydb?useSSL=true";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root1234");

			Statement stmt = conn.createStatement();

			System.out.println("INSIDE TRYYYY " + conn.isValid(1000));

			

			ArrayList<String> lines = new ArrayList<String>();
			//insert users
			lines.add("INSERT INTO User (user_id, username, password, name, surname, email,tel,role_host, role_tenant, role_admin,address_number,street_address, city,country,potal_code, birthday) VALUES (1,'Admin','1234@ds','Christos','Papadopoulos','chPap@gmail.com','6934546332',false, false, true ,2,'Eustratiou','Athens','Greece','Agia Paraskeuh','12452','1980-05-03')");
			lines.add("INSERT INTO User (user_id, username, password, name, surname, email,role_host, role_tenant, role_admin,address_number,street_address, city,country,potal_code, birthday) VALUES (2,'eleni1','fd41s','Eleni','Georgiou','elenitsa@gmail.com',true, false, false ,24,'Karampampa','Athens','Greece','Imittos','12515','1990-05-05')");
			lines.add("INSERT INTO User (user_id, username, password, name, surname, email,tel,role_host, role_tenant, role_admin,approved) VALUES (3,'maria','%^##Vda','Maria','Kitsou','mariaK@hotmail.com','6935125521',true, false, false ,0)");
			lines.add("INSERT INTO User (user_id, username, password, name, surname, email,role_host, role_tenant, role_admin,) VALUES (4,'kostasG','jack24','Konstantinos','Grigoriou','kg@hotmail.com',false, true, false)");
			
			//insert apartments
			lines.add("INSERT INTO Apartment (room_id, cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom,capacity, max_tenants, min_cost_booking,cost_per_person,description,host_id,room_photo)VALUES (1,30,'spiti',4,5,3.4,2,1,2,true,200,4,20,20,'Polu wraio kai aneto!',2,'[B@367a6a82')");
			lines.add("INSERT INTO Apartment (room_id, cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom,capacity, max_tenants, min_cost_booking,cost_per_person,description,host_id,room_photo)VALUES (2,50,'spiti',2,1,2.0,2,1,2,false,150,2,15,10,'Euruxwro',2,'[B@367a6a83')");
			lines.add("INSERT INTO Apartment (room_id, cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom,capacity, max_tenants, min_cost_booking,cost_per_person,description,host_id,room_photo)VALUES (3,30,'spiti',4,5,3.4,2,1,2,true,200,4,20,20,'Polu wraio kai aneto!',3,'[B@367a6a83')");
			lines.add("INSERT INTO Apartment (room_id, cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom,capacity, max_tenants, min_cost_booking,cost_per_person,host_id,room_photo) VALUES (4,30,'domatio',4,5,3.4,2,1,2,true,70,4,20,20,3,'[B@367a6a83')");

			//insert rules
			
			//insert facilities
			
			//insert free dates
			
			//insert locations
			
			
			for(int i=0;i<lines.size();i++)   {
				
				stmt.executeUpdate(lines.get(i));
			}

			conn.close();

		}
		catch (SQLException e)
		{
			System.err.println("Got an SQL exception!");
			System.err.println(e.getMessage());
		}
		catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}


















	}


}
