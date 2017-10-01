package JavaFiles;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class PopulateHostInfo {
	public String populateHostInfo(String hostId)
	{
		try
		{
			Statement stmt = ConnectionManager.getConnection().createStatement();
			//check for same username before insert
			String query = "SELECT DISTINCT name,surname,email,tel,photo FROM User,criticshost where user_id ="
					+ hostId + " AND host_id=" + hostId;
			System.out.println(query);

			ResultSet rs = stmt.executeQuery(query);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;
		
			if(rowCount==1) {
				
				HashMap<String,String> profileInfo = new HashMap<String,String>();
				rs.first();
				
				profileInfo.put("name", rs.getString("name"));
				profileInfo.put("surname", rs.getString("surname"));
				profileInfo.put("email", rs.getString("email"));
				String tel = rs.getString("tel");
				if( tel==null) {
					profileInfo.put("tel", "Δεν παρέχεται τηλέφωνο επικοινωνίας.");
				}else {
					profileInfo.put("tel", rs.getString("tel"));
				}
				ImgToBArray image = new ImgToBArray();
				
				String img = rs.getString("photo");
				if(img!=null) {
					profileInfo.put("photo",image.convertBArrayToImage(rs.getBytes("photo")));
				}else {
					profileInfo.put("photo", null);
				}
				
				String criticsQuery = "SELECT t1.username,critic "
						+ "FROM User as t1,CriticsHost "
						+ "WHERE host_id=" + hostId
						+ " AND tenant_id=t1.user_id";
				
				ResultSet rsCritics = stmt.executeQuery(criticsQuery);
				rsCritics.beforeFirst();
				int rowCountCritics = rsCritics.last() ? rsCritics.getRow() : 0;
				
				if(rowCountCritics!=0) {

					rsCritics.beforeFirst();
					JSONArray criticsJSON = new JSONArray();
					while(rsCritics.next()) {
						JSONObject eachCritic = new JSONObject();
						eachCritic.put("username", rsCritics.getString("username"));
						eachCritic.put("critic", rsCritics.getString("critic"));
						criticsJSON.put(eachCritic);
					}
					
					JSONObject json = new JSONObject(profileInfo);
					json.put("critics", criticsJSON);
					stmt.close();
					return json.toString();
				}
				else {
					JSONObject json = new JSONObject(profileInfo);
					json.append("critics", "");
					return json.toString();
				}

			}
			else {
				System.out.println("User does not exist.");
				return null;
			}
		}
		catch (Exception e)
		{
			System.err.println("Got an SQL exception! ");
			System.err.println(e.getMessage());
			return null;
		}
	}
}
