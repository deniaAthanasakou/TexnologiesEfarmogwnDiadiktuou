package JavaFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PopulateLocationDropDown {
	public String populateDropdown() throws ClassNotFoundException {


		try
		{
			System.out.println("INSIDE POP DROPDOWN");
			Statement stmt = ConnectionManager.getConnection().createStatement();

			//check for same username before insert
			String checkQuery = "SELECT DISTINCT neighborhood FROM Location";
			System.out.println(checkQuery);

			ResultSet rs = stmt.executeQuery(checkQuery);
			rs.beforeFirst();
			int rowCount = rs.last() ? rs.getRow() : 0;

			if(rowCount!=0) {
				rs.beforeFirst();
				ArrayList<String> recs = new ArrayList<String>();
				HashMap<String,ArrayList<String>> results = new HashMap<String,ArrayList<String>>();
				while(rs.next()) {
					recs.add(rs.getString("neighborhood"));
				}
				results.put("results", recs);
				return results.toString();
			}
			else {
				System.out.println("Database does not have neighborhoods");
				return null;
			}
		}
		catch(SQLException e) {
			System.err.println("Got an SQL exception! ");
			System.err.println(e.getMessage());
			return null;
		}
	}
}
