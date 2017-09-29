package JavaFiles;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class PopulateUser {

	public HashMap<String, String> populateProfile(String username)
	{
		try
		{
			Statement stmt = ConnectionManager.getConnection().createStatement();
			//check for same username before insert
			String checkQuery = "SELECT * FROM User WHERE username=" + "'" + username + "'";
			System.out.println(checkQuery);

			ResultSet rs = stmt.executeQuery(checkQuery);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;
		
			if(rowCount==1) {
				
				System.out.println("EXISTS");
				HashMap<String,String> profileInfo = new HashMap<String,String>();
				//return resultset for populate
				ResultSet profileRs = stmt.executeQuery(checkQuery);
				
				//get first row
				profileRs.first();
				
				String user_id = profileRs.getString("user_id");
				
				//put values to hashmap
				//put personal info
				profileInfo.put("username", profileRs.getString("username"));
								
				//convert imageByteArray to image
				String photoBytes = profileRs.getString("photo");
				byte[] photo = profileRs.getBytes("photo");
				ImgToBArray image = new ImgToBArray();
				if(photoBytes!=null) {
					String imgPth = image.convertBArrayToImage(photo);
					if(imgPth!=null) {
						profileInfo.put("image", imgPth);
					}
				}else {
					profileInfo.put("image", null);
				}
				
				
				profileInfo.put("name", profileRs.getString("name"));
				profileInfo.put("surname", profileRs.getString("surname"));
				profileInfo.put("email", profileRs.getString("email"));
				profileInfo.put("tel", profileRs.getString("tel"));
				
				//put birthday
				Date birthday = profileRs.getDate("birthday");
				if(birthday!=null) {
					SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
					String birthdayStr = sdf.format(birthday);
					profileInfo.put("birthday", birthdayStr);
				}else {
					profileInfo.put("birthday", null);
				}
							
				
				//put roles
				String roles = "";
				byte tenant = profileRs.getByte("role_tenant");
				if(profileRs.getByte("role_tenant")==1) {
					roles += "Ενοικιαστής";
				}
				if(profileRs.getByte("role_host")==1) {
					roles += " Οικοδεσπότης";
				}
				profileInfo.put("roles", roles);
				
				//put country,city
				profileInfo.put("country",profileRs.getString("country"));
				profileInfo.put("city",profileRs.getString("city"));
				
				//put full address
				String fullAddress = profileRs.getInt("address_number") + " " + profileRs.getString("street_address");
				profileInfo.put("full_address",fullAddress);
				
				//put postal code
				profileInfo.put("postal_code",profileRs.getString("postal_code"));
				
				//get critics
				String criticsQuery = "SELECT tenant_id, max(critic), min(critic), avg(critic), COUNT(*) FROM critics WHERE tenant_id=" + user_id;
				ResultSet critics = stmt.executeQuery(criticsQuery);
				
				if(tenant==1) {
					if(critics.first()) {
						profileInfo.put("statistics", "true");
						profileInfo.put("max_critic", critics.getString("max(critic)"));
						profileInfo.put("min_critic", critics.getString("min(critic)"));
						profileInfo.put("avg_critic", critics.getString("avg(critic)"));
						profileInfo.put("no_critics", critics.getString("COUNT(*)"));
					}else {
						profileInfo.put("statistics", "true");
						profileInfo.put("max_critic", null);
						profileInfo.put("min_critic", null);
						profileInfo.put("avg_critic", null);
						profileInfo.put("no_critics", null);
					}
				}
				else {
					profileInfo.put("statistics", "false");
				}
				
				//get messages
				String messagesQuery = "SELECT COUNT(*) FROM messagesusers,subjects,messages "
						+ "WHERE messagesusers.msg_users_id=subjects.msg_users_id AND subjects.subject_id=messages.subject_id "
						+ "AND  (messagesusers.tenant_id=" + user_id + " OR messagesusers.host_id=" + user_id + ")";

				ResultSet messages = stmt.executeQuery(messagesQuery);
				if(messages.first()) {
					profileInfo.put("messages", "true");
					profileInfo.put("no_messages", messages.getString("COUNT(*)"));
				}
				else {
					profileInfo.put("messages", "false");
				}
				
				stmt.close();
				return profileInfo;
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
	
	public HashMap<String, String> populateProfileAdmin()
	{
		try
		{
			Statement stmt = ConnectionManager.getConnection().createStatement();
			//check for same username before insert
			String checkQuery = "SELECT * FROM User WHERE username='Admin'";
			System.out.println(checkQuery);

			ResultSet rs = stmt.executeQuery(checkQuery);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;
		
			if(rowCount==1) {
				
				System.out.println("EXISTS");
				HashMap<String,String> profileInfo = new HashMap<String,String>();
				//return resultset for populate
				ResultSet profileRs = stmt.executeQuery(checkQuery);
				
				//get first row
				profileRs.first();
				
				String user_id = profileRs.getString("user_id");
				
				//put values to hashmap
				//put personal info
				profileInfo.put("username", profileRs.getString("username"));
								
				//convert imageByteArray to image
				String photoBytes = profileRs.getString("photo");
				byte[] photo = profileRs.getBytes("photo");
				ImgToBArray image = new ImgToBArray();
				if(photoBytes!=null) {
					String imgPth = image.convertBArrayToImage(photo);
					if(imgPth!=null) {
						profileInfo.put("image", imgPth);
					}
				}else {
					profileInfo.put("image", null);
				}
				
				
				profileInfo.put("name", profileRs.getString("name"));
				profileInfo.put("surname", profileRs.getString("surname"));
				profileInfo.put("email", profileRs.getString("email"));
				profileInfo.put("tel", profileRs.getString("tel"));
				
				//put birthday
				Date birthday = profileRs.getDate("birthday");
				if(birthday!=null) {
					SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
					String birthdayStr = sdf.format(birthday);
					profileInfo.put("birthday", birthdayStr);
				}else {
					profileInfo.put("birthday", null);
				}
				
				//put country,city
				profileInfo.put("country",profileRs.getString("country"));
				profileInfo.put("city",profileRs.getString("city"));
				
				//put full address
				String fullAddress = profileRs.getInt("address_number") + " " + profileRs.getString("street_address");
				profileInfo.put("full_address",fullAddress);
				
				//put postal code
				profileInfo.put("postal_code",profileRs.getString("postal_code"));

				//get messages
				String messagesQuery = "SELECT COUNT(*) FROM messagesusers,subjects,messages "
						+ "WHERE messagesusers.msg_users_id=subjects.msg_users_id AND subjects.subject_id=messages.subject_id "
						+ "AND  (messagesusers.tenant_id=" + user_id + " OR messagesusers.host_id=" + user_id + ")";

				ResultSet messages = stmt.executeQuery(messagesQuery);
				if(messages.first()) {
					profileInfo.put("messages", "true");
					profileInfo.put("no_messages", messages.getString("COUNT(*)"));
				}
				else {
					profileInfo.put("messages", "false");
				}
				
				//get site's statistics
				//put no of tenants
				String sitesTenants = "SELECT COUNT(*) as no_tenants FROM User WHERE role_tenant = 1";
				ResultSet statsTenants = stmt.executeQuery(sitesTenants);
				statsTenants.first();
				profileInfo.put("no_tenants", statsTenants.getString("no_tenants"));
				
				//put no of hosts
				String sitesHosts = "SELECT COUNT(*) as no_hosts FROM User WHERE role_host = 1";
				ResultSet statsHosts = stmt.executeQuery(sitesHosts);
				statsHosts.first();
				profileInfo.put("no_hosts", statsHosts.getString("no_hosts"));
				
				//put no of apts
				String sitesApts = "SELECT COUNT(*) as no_apts FROM Apartment";
				ResultSet statsApts = stmt.executeQuery(sitesApts);
				statsApts.first();
				profileInfo.put("no_apts", statsApts.getString("no_apts"));
				
				//put no of users
				String sitesUsers = "SELECT COUNT(*) as no_users FROM User";
				ResultSet statsUsers = stmt.executeQuery(sitesUsers);
				statsUsers.first();
				profileInfo.put("no_users", statsUsers.getString("no_users"));
				
				//put no of users
				String sitesApproved = "SELECT COUNT(*) as no_approved from User WHERE approved = 0 and role_host=1";
				ResultSet statsApproved = stmt.executeQuery(sitesApproved);
				statsApproved.first();
				profileInfo.put("no_approved", statsApproved.getString("no_approved"));
				
				//put no of users
				String sitesUnapproved = "SELECT COUNT(*) as no_unapproved from User WHERE approved = 1 and role_host=1";
				ResultSet statsUnapproved = stmt.executeQuery(sitesUnapproved);
				statsUnapproved.first();
				profileInfo.put("no_unapproved", statsUnapproved.getString("no_unapproved"));

				
				stmt.close();
				return profileInfo;
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
	
	public HashMap<String, String> populateChangeProf(String username)
	{
		try
		{
			Statement stmt = ConnectionManager.getConnection().createStatement();
			//check for same username before insert
			String checkQuery = "SELECT * FROM User WHERE username=" + "'" + username + "'";
			System.out.println(checkQuery);

			ResultSet rs = stmt.executeQuery(checkQuery);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;
		
			if(rowCount==1) {
				
				System.out.println("EXISTS");
				HashMap<String,String> profileInfo = new HashMap<String,String>();
				//return resultset for populate
				ResultSet profileRs = stmt.executeQuery(checkQuery);
				
				//get first row
				profileRs.first();
				
				String user_id = profileRs.getString("user_id");
				
				//put values to hashmap
				//put personal info
				profileInfo.put("username", profileRs.getString("username"));
								
				//convert imageByteArray to image
				String photoBytes = profileRs.getString("photo");
				byte[] photo = profileRs.getBytes("photo");
				ImgToBArray image = new ImgToBArray();
				if(photoBytes!=null) {
					String imgPth = image.convertBArrayToImage(photo);
					if(imgPth!=null) {
						profileInfo.put("image", imgPth);
					}
				}else {
					profileInfo.put("image", null);
				}
				
				
				profileInfo.put("name", profileRs.getString("name"));
				profileInfo.put("surname", profileRs.getString("surname"));
				profileInfo.put("email", profileRs.getString("email"));
				profileInfo.put("tel", profileRs.getString("tel"));
				
				//put birthday
				Date birthday = profileRs.getDate("birthday");
				if(birthday!=null) {
					SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
					String birthdayStr = sdf.format(birthday);
					profileInfo.put("birthday", birthdayStr);
				}else {
					profileInfo.put("birthday", null);
				}
							
				
				//put roles
				String tenant = profileRs.getString("role_tenant");
				String host = profileRs.getString("role_host");
				profileInfo.put("tenant", tenant);
				profileInfo.put("host", host);
				
				//put country,city
				profileInfo.put("country",profileRs.getString("country"));
				profileInfo.put("city",profileRs.getString("city"));
				
				//put address
				String streetAddress = profileRs.getString("street_address");
				profileInfo.put("street_address",streetAddress);
				String addressNum = profileRs.getString("address_number");
				profileInfo.put("street_address",addressNum);
				
				
				//put postal code
				profileInfo.put("postal_code",profileRs.getString("postal_code"));
				
				
				stmt.close();
				return profileInfo;
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
