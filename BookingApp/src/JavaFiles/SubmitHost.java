package JavaFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import Entities.Criticshost;

public class SubmitHost {
	public boolean submitCritic(Criticshost info) throws ClassNotFoundException, SQLException {
		
		Statement stmt = ConnectionManager.getConnection().createStatement();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("DATE: " + sdf.format(info.getId().getDate()));
		String checkQuery = "SELECT * FROM CriticsHost WHERE host_id="+ info.getId().getHostId()
						+ " AND tenant_id=" + info.getId().getTenantId()
						+ " AND date=" + "'" + sdf.format(info.getId().getDate()) +"'";
		
		System.out.println("CHECK: "+ checkQuery);
		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;
		
		if(rowCount==0) {
			String insertQuery = "INSERT INTO CriticsHost(host_id,tenant_id,date,critic)"
					+ " VALUES("
					+ info.getId().getHostId() + ","
					+ info.getId().getTenantId() + ","
					+ "'" + sdf.format(info.getId().getDate()) + "',"
					+ info.getCritic() +")";
			
			System.out.println("insertQuery: "+ insertQuery);

			
			stmt.executeUpdate(insertQuery);
			return true;
		}else {
			System.out.println("Critic has already been made");
			return false;
		}
		
		
		
	}
}
