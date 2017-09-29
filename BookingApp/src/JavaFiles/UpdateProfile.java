package JavaFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entities.User;

public class UpdateProfile {
	public int updateImage(User updateUser) throws ClassNotFoundException, SQLException {
		
		System.out.println("change image");
		Statement stmt = ConnectionManager.getConnection().createStatement();
		//check for same username before insert
		String checkQuery = "SELECT * FROM User WHERE username=" + "'" + updateUser.getUsername() + "'";
		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;
		
		if(rowCount==1) {
			System.out.println("USER EXISTS");
			
			
			if(rs.first()) {
				String updateQuery = "UPDATE User SET photo=" + "'" + updateUser.getPhoto() + "'";
				stmt.executeUpdate(updateQuery);
			}
			
			System.out.println("QUERY DONE");
		}
		
		if(rs.first()) {
			if(rs.getByte("role_host")==1) {
				return 0;
			}else if(rs.getByte("role_host")==0 && rs.getByte("role_tenant")==1) {
				return 1;
			}else if(rs.getByte("role_admin")==1) {
				return 2;
			}
		}
		
		System.out.println("DONE DONE");
		
		stmt.close();
		return 0;
	}
}
