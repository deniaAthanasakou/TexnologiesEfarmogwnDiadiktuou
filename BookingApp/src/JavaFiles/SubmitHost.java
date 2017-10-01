package JavaFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import Entities.Criticshost;

public class SubmitHost {
	public boolean submitCritic(Criticshost info,String username) throws ClassNotFoundException, SQLException {
		
		Statement stmt = ConnectionManager.getConnection().createStatement();
		
		
		String idQuery ="SELECT user_id FROM User WHERE username='"+ username +"'";
		ResultSet rs = stmt.executeQuery(idQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;
		
		String id="";
		if(rowCount==1) {
			rs.first();
			id = rs.getString("user_id");
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String checkQuery = "SELECT * FROM CriticsHost WHERE host_id="+ info.getId().getHostId()
						+ " AND tenant_id=" + id
						+ " AND date=" + "'" + sdf.format(info.getId().getDate()) +"'";
		
	    rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		rowCount = rs.last() ? rs.getRow() : 0;
		
		if(rowCount==0) {
			String insertQuery = "INSERT INTO CriticsHost(host_id,tenant_id,date,critic)"
					+ " VALUES ("
					+ info.getId().getHostId() + ","
					+ id + ","
					+ "'" + sdf.format(info.getId().getDate()) + "',"
					+ info.getCritic() +")";
			

			
			stmt.executeUpdate(insertQuery);
			stmt.close();
			return true;
		}else {
			return false;
		}
		
		
		
	}
}
