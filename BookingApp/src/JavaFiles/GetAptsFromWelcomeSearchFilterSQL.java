package JavaFiles;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Entities.SearchFilters;

public class GetAptsFromWelcomeSearchFilterSQL {


	public  HashMap<Integer, HashMap<String, String>> ExecuteQuery(SearchFilters filters) {
		try {
			Statement stmt = ConnectionManager.getConnection().createStatement();
			//String query = "SELECT * FROM Location NATURAL JOIN Apartment NATURAL JOIN Facilities NATURAL JOIN Rule NATURAL JOIN FreeDates WHERE";
			String query = "SELECT room_id , room_photo, cost_per_day, type, number_beds, number_critics, average_critic FROM Location NATURAL JOIN Apartment NATURAL JOIN Facilities NATURAL JOIN FreeDates WHERE";
			if (!filters.getNeighborhood().equals(""))
				query+="  neighborhood = '" + filters.getNeighborhood() + "' ";
			else 
				query+=" (1) ";
			query+= "AND type = '" + filters.getRoomType() + "' ";
			if(filters.getMaxCost()!=0) {
				query += " AND " + filters.getMaxCost() + " >= min_cost_ booking ";
			}
			if(filters.getWifi()!=null) {
				query += " AND wifi = true";
			}
			if(filters.getAircondition()!=null) {
				query += " AND aircondition = true";
			}
			if(filters.getHeating()!=null) {
				query += " AND heating = true";
			}
			if(filters.getKitchen()!=null) {
				query += " AND kitchen = true";
			}
			if(filters.getTv()!=null) {
				query += " AND tv = true";
			}
			if(filters.getParking()!=null) {
				query += " AND parking = true";
			}
			if(filters.getElevator()!=null) {
				query += " AND elevator = true";
			}
			
			query+= " AND from_date <= " + filters.getFrom();
			query+= " AND to_date >= " + filters.getTo();
			
			query+= " ORDER BY min_cost_booking";

			System.out.println("My query is "+ query);

			ResultSet rs = stmt.executeQuery(query);


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

			ArrayList<String> ids= new ArrayList<String>();

			HashMap<Integer, HashMap<String, String>> allApts = new HashMap<Integer, HashMap<String,String>>();

			rs.beforeFirst();
			int i=0;
			while (rs.next()) {

				i++;

				HashMap <String,String> apt = new HashMap <String,String>();    	  
				String id = rs.getString("room_id");
				ids.add(id);
				ImgToBArray image = new ImgToBArray();//getRoomPhotoAsString
				String imgPath = image.convertBArrayToImage(rs.getBytes("room_photo"));
				String costPerDay = rs.getString("cost_per_day");
				String type = rs.getString("type");
				String beds = rs.getString("number_beds");
				String critics = rs.getString("number_critics");
				String avgCritic = rs.getString("average_critic");
				System.out.println("results " +id +" "+type +" "+beds +" "+critics +" "+avgCritic + " " + imgPath);

				//insert data into inner HashMap
				apt.put("room_id",id);
				apt.put("image",imgPath);				//photo
				apt.put("costPerDay",costPerDay);
				apt.put("type",type);
				apt.put("beds",beds);
				apt.put("critics",critics);
				apt.put("avgCritic",avgCritic);

				//insert data into outer HashMap
				allApts.put(i, apt);
			}


			stmt.close();
			return allApts;
		}
		catch (Exception e)
		{
			System.err.println("Got an SQL exception! ");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
