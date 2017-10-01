package JavaFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class BookingRoom {
	public boolean bookARoom(String from, String to, String room_id,String username) throws SQLException, ClassNotFoundException {
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
				
		String checkQuery = "SELECT * FROM Booking WHERE tenant_id=" + id
				+	" AND room_id=" + room_id
				+	" AND date_from=" + "'" + from +"'"
				+	" AND date_to=" + "'" + to +"'";
		
		rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		rowCount = rs.last() ? rs.getRow() : 0;
		if(rowCount==0) {
			String insertQuery = "INSERT INTO Booking(room_id,tenant_id,date_from,date_to)"
						+	" VALUES ("
						+ room_id + ", "
						+ id + ", "
						+"'" + from +"',"
						+"'" + to +"')";
			
			stmt.executeUpdate(insertQuery);
			stmt.close();
			return true;
		}
		stmt.close();
		return false;
	}
}
