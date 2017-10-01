package JavaFiles;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class GetAptsOfHostSql {
	public String getApts(String host) {
		try {
			Statement stmt = ConnectionManager.getConnection().createStatement();
			String query = "SELECT room_id, room_photo, address_number , street, neighborhood, city, number_critics, average_critic FROM Location NATURAL JOIN Apartment  WHERE host_id="+host;
						
			HashMap<Integer, HashMap<String, String>> allApts = new HashMap<Integer, HashMap<String,String>>();
			
			ResultSet rs = stmt.executeQuery(query);


			if (rs.next() == false) { 
				System.out.println("ResultSet is empty in Java"); 
			}
			else if (rs.next() == true) {
				System.out.println("ResultSet is NOT empty"); 
			}


			rs.beforeFirst();
			int i=0;
			JSONArray aptArray = new JSONArray();
			while (rs.next()) {

				i++;
				
				HashMap <String,String> apt = new HashMap <String,String>();    	  
				String id = rs.getString("room_id");
				ImgToBArray image = new ImgToBArray();//getRoomPhotoAsString
				String addressNumber = rs.getString("address_number");
				String street = rs.getString("street");
				String neighborhood = rs.getString("neighborhood");
				String city = rs.getString("city");
				String critics = rs.getString("number_critics");
				String avgCritic = rs.getString("average_critic");

				//insert data into inner HashMap
				apt.put("room_id",id);
				String fullAddress = street + " " + addressNumber + ", " + neighborhood +", " + city;
				apt.put("fullAddress",fullAddress);
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
