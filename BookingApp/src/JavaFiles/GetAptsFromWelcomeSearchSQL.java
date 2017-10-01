package JavaFiles;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import Entities.SearchFilters;

public class GetAptsFromWelcomeSearchSQL {
	public  String ExecuteQuery(String neighborhood, String from, String to, String tenants) {
		try {
			Statement stmt = ConnectionManager.getConnection().createStatement();
			String query = "SELECT room_id , room_photo, cost_per_day, type, number_beds, number_critics, average_critic FROM Location NATURAL JOIN Apartment NATURAL JOIN Facilities NATURAL JOIN FreeDates WHERE";
			if (!neighborhood.equals(""))
				query+="  neighborhood = '" + neighborhood + "' ";
			else 
				query+=" (1) ";
			

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDate = sdf.parse(from.replaceAll("/", "-"));
			Date toDate = sdf.parse(to.replaceAll("/", "-"));
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

			query+= " AND from_date <= " + "'" + sdf2.format(fromDate) + "'";
			query+= " AND to_date >= " + "'" +  sdf2.format(toDate) + "'";
			query+= " AND max_tenants >="  + tenants ;
			query+= " ORDER BY min_cost_booking";
			

			ResultSet rs = stmt.executeQuery(query);


			if (rs.next() == false) { 
				System.out.println("ResultSet is empty in Java"); 
			}
			else if (rs.next() == true) {
				System.out.println("ResultSet is NOT empty"); 
			}

			HashMap<Integer, HashMap<String, String>> allApts = new HashMap<Integer, HashMap<String,String>>();

			rs.beforeFirst();
			int i=0;
			JSONArray aptArray = new JSONArray();
			while (rs.next()) {

				i++;
				
				HashMap <String,String> apt = new HashMap <String,String>();    	  
				String id = rs.getString("room_id");
				ImgToBArray image = new ImgToBArray();//getRoomPhotoAsString
				String costPerDay = rs.getString("cost_per_day");
				String type = rs.getString("type");
				String beds = rs.getString("number_beds");
				String critics = rs.getString("number_critics");
				String avgCritic = rs.getString("average_critic");

				//insert data into inner HashMap
				apt.put("room_id",id);
				apt.put("costPerDay",costPerDay);
				apt.put("type",type);
				apt.put("beds",beds);
				apt.put("critics",critics);
				apt.put("avgCritic",avgCritic);

				JSONObject aptJSON = new JSONObject(apt);
				aptArray.put(aptJSON);
				//insert data into outer HashMap
				allApts.put(i, apt);
			}
			
			JSONObject allAptsJSON = new JSONObject();
			allAptsJSON.put("apts", aptArray);

			stmt.close();
			return allAptsJSON.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
