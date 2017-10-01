package JavaFiles;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class ApartmentWithDetailsSql {
	public String getApt(String room_id, String from_dateTenant, String to_dateTenant) {
		
		try {
			Statement stmt = ConnectionManager.getConnection().createStatement();
			//String query = "SELECT * FROM Location NATURAL JOIN Apartment NATURAL JOIN Facilities NATURAL JOIN Rule NATURAL JOIN FreeDates WHERE";
			String query = "SELECT * from apartment natural join Location natural join facilities natural join rule natural join freedates where room_id="+room_id;
			System.out.println("query is "+query);
		
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
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDate = sdf.parse(from_dateTenant.replaceAll("/", "-"));
			Date toDate = sdf.parse(to_dateTenant.replaceAll("/", "-"));
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			
			String fromTenant = sdf2.format(fromDate);
			String toTenant = sdf2.format(toDate);


			HashMap <String,String> apt = new HashMap <String,String>();   

			rs.beforeFirst();
			int i=0;
			JSONArray aptArray = new JSONArray();
			while (rs.next()) {
				System.out.println("inside while");
				i++;
				 	
				//get all fields
				ImgToBArray image = new ImgToBArray();//getRoomPhotoAsString
				//String imgPath = image.convertBArrayToImage(rs.getBytes("room_photo"));
				
				String costPerDay = rs.getString("cost_per_day");
				String type = rs.getString("type");
				String number_rooms = rs.getString("number_rooms");
				String critics = rs.getString("number_critics");
				String avgCritic = rs.getString("average_critic");
				String beds = rs.getString("number_beds");
				String bathrooms = rs.getString("number_bathrooms");
				String bedrooms = rs.getString("number_bedrooms");
				
				String capacity = rs.getString("capacity");
				
				
				String description = rs.getString("description");
				if(description==null)
					description="Δεν υπάρχει κάποια περιγραφή του χώρου.";
				
				
				String max_tenants = rs.getString("max_tenants");
				String min_cost_booking = rs.getString("min_cost_booking");
				String cost_per_person = rs.getString("cost_per_person");
				String host_id = rs.getString("host_id");
				String map = rs.getString("map");
				String address_number = rs.getString("address_number");
				String street = rs.getString("street");
				String postal_code = rs.getString("postal_code");
				String city = rs.getString("city");
				String country = rs.getString("country");
				String neighborhood = rs.getString("neighborhood");
				String transportation = rs.getString("transportation");
				if(transportation==null)
					transportation="Δεν υπάρχει κάποια περιγραφή για μετακίνηση στον χώρο μας.";
				
				String min_days_booking = rs.getString("min_days_booking");
				String from_date = rs.getString("from_date");
				String to_date = rs.getString("to_date");
				String fullAddress = street + " " + address_number + ", " + neighborhood + " "+postal_code+ ", " + city +", " + country;
				String fullDates = from_date + "  μέχρι  " + to_date;
				
				
				
				String livingroom ="Όχι";
				if(rs.getString("livingroom").equals("1"))
					livingroom="Ναι";
				String wifi ="Όχι";
				if(rs.getString("wifi").equals("1"))
					wifi="Ναι";
				String aircondition ="Όχι";
				if(rs.getString("aircondition").equals("1"))
					aircondition="Ναι";
				String heating ="Όχι";
				if(rs.getString("heating").equals("1"))
					heating="Ναι";
				String kitchen ="Όχι";
				if(rs.getString("kitchen").equals("1"))
					kitchen="Ναι";
				String tv ="Όχι";
				if(rs.getString("tv").equals("1"))
					tv="Ναι";
				String parking ="Όχι";
				if(rs.getString("parking").equals("1"))
					parking="Ναι";
				String elevator ="Όχι";
				if(rs.getString("elevator").equals("1"))
					elevator="Ναι";
				String smoking ="Όχι";
				if(rs.getString("smoking_allowed").equals("1"))
					smoking="Ναι";
				String pets ="Όχι";
				if(rs.getString("pets_allowed").equals("1"))
					pets="Ναι";
				String events ="Όχι";
				if(rs.getString("events").equals("1"))
					events="Ναι";
				

				//insert data into inner HashMap
				apt.put("room_id",room_id);
				//apt.put("photo",imgPath);				//photo
				apt.put("costPerDay",costPerDay);
				apt.put("type",type);
				apt.put("number_rooms",number_rooms);
				apt.put("critics",critics);
				apt.put("avgCritic",avgCritic);
				apt.put("beds",beds);
				apt.put("bathrooms",bathrooms);
				apt.put("bedrooms",bedrooms);
				apt.put("livingroom",livingroom);
				apt.put("capacity",capacity);
				apt.put("description",description);
				apt.put("max_tenants",max_tenants);
				apt.put("min_cost_booking",min_cost_booking);
				apt.put("cost_per_person",cost_per_person);
				apt.put("host_id",host_id);
				apt.put("map",map);
				apt.put("fullAddress",fullAddress);
				apt.put("transportation",transportation);
				apt.put("wifi",wifi);
				apt.put("aircondition",aircondition);
				apt.put("heating",heating);
				apt.put("kitchen",kitchen);
				apt.put("tv",tv);
				apt.put("wifi",wifi);
				apt.put("parking",parking);
				apt.put("elevator",elevator);
				apt.put("smoking",smoking);
				apt.put("pets",pets);
				apt.put("events",events);
				apt.put("min_days_booking",min_days_booking);
				apt.put("from_date",fromTenant);
				apt.put("to_date",toTenant);
				apt.put("fullDates",fullDates);
				

			}
			
			
			
			
			
			
			JSONObject aptJSON = new JSONObject(apt);

			System.out.println("JSON:" + aptJSON.toString());

			stmt.close();
			return aptJSON.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
