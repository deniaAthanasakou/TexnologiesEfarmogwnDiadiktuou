package JavaFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChooseUserPage {
	public int getUser(String username) throws ClassNotFoundException, SQLException {
		System.out.println("INSIDE CHOOSE PAGE");
		Statement stmt = ConnectionManager.getConnection().createStatement();

		//check for same username before insert
		String checkQuery = "SELECT * FROM User WHERE username=" + "'" + username + "'";
		System.out.println(checkQuery);

		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;
	
		if(rowCount==1) {
			if(rs.first()) {
				byte roleHost = rs.getByte("role_host");
				byte roleAdmin = rs.getByte("role_admin");
				byte roleTenant = rs.getByte("role_tenant");
				
				if(roleHost==1) {
					return 1;
				}else if(roleAdmin==1) {
					return 2;
				}else if(roleHost==0 && roleAdmin==0) {
					return 0;
				}
			}	
		}else {
			return 3;
		}
		return 3;
	}
}
