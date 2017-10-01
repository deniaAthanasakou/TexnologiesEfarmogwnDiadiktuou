package JavaFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import Entities.User;

public class UpdateProfile {
	public void updateImage(User updateUser) throws ClassNotFoundException, SQLException {

		Statement stmt = ConnectionManager.getConnection().createStatement();
		//check for same username before insert
		String checkQuery = "SELECT * FROM User WHERE username=" + "'" + updateUser.getUsername() + "'";
		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;


		if(rowCount==1) {


			if(rs.first()) {
				String updateQuery = "UPDATE User SET photo=" + "'" + updateUser.getPhoto() + "'"
						+ "WHERE username=" + "'" + updateUser.getUsername() + "'";
				stmt.executeUpdate(updateQuery);
			}

		}


		stmt.close();
	}

	public void updatePersonal(User updateUser) throws ClassNotFoundException, SQLException {

		Statement stmt = ConnectionManager.getConnection().createStatement();
		//check for same username before insert
		String checkQuery = "SELECT * FROM User WHERE username=" + "'" + updateUser.getUsername() + "'";
		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;


		if(rowCount==1) {


			if(rs.first()) {
				String updateQuery = "UPDATE User SET name=" + "'" + updateUser.getName() + "'" + ", surname=" + "'" + updateUser.getSurname() + "'"
						+ " WHERE username=" + "'" + updateUser.getUsername() + "'";

				stmt.executeUpdate(updateQuery);
			}

		}


		stmt.close();
	}
	
	public void updateEmail(User updateUser) throws ClassNotFoundException, SQLException {

		Statement stmt = ConnectionManager.getConnection().createStatement();
		//check for same username before insert
		String checkQuery = "SELECT * FROM User WHERE username=" + "'" + updateUser.getUsername() + "'";
		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;


		if(rowCount==1) {


			if(rs.first()) {
				String updateQuery = "UPDATE User SET email=" + "'" + updateUser.getEmail() + "'" 
						+ " WHERE username=" + "'" + updateUser.getUsername() + "'";

				stmt.executeUpdate(updateQuery);
			}

		}


		stmt.close();
	}
	
	public boolean updatePass(User updateUser,String old_pass) throws ClassNotFoundException, SQLException {

		Statement stmt = ConnectionManager.getConnection().createStatement();
		//check for same username before insert
		String checkQuery = "SELECT * FROM User WHERE username=" + "'" + updateUser.getUsername() + "'"
						+ " AND password=" + "'" + old_pass + "'";
		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;


		if(rowCount==1) {


			if(rs.first()) {
				String updateQuery = "UPDATE User SET password=" + "'" + updateUser.getPassword() + "'" 
						+ " WHERE username=" + "'" + updateUser.getUsername() + "'";

				stmt.executeUpdate(updateQuery);
			}

			stmt.close();
			return true;
		}else {
			stmt.close();
			return false;
		}
	}
	
	public boolean updateRoles(User updateUser) throws ClassNotFoundException, SQLException {

		Statement stmt = ConnectionManager.getConnection().createStatement();
		//check for same username before insert
		String checkQuery = "SELECT * FROM User WHERE username=" + "'" + updateUser.getUsername() + "'";

		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;


		if(rowCount==1) {


			if(rs.first()) {
				String updateQuery = "UPDATE User SET role_tenant=" + updateUser.getRoleTenant()
						+ ", role_host=" + updateUser.getRoleHost()
						+ " WHERE username=" + "'" + updateUser.getUsername() + "'";

				stmt.executeUpdate(updateQuery);
			}

			stmt.close();
			return true;
		}else {
			stmt.close();
			return false;
		}
	}
	
	public boolean updateResidence(User updateUser) throws ClassNotFoundException, SQLException {

		Statement stmt = ConnectionManager.getConnection().createStatement();
		//check for same username before insert
		String checkQuery = "SELECT * FROM User WHERE username=" + "'" + updateUser.getUsername() + "'";

		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;


		if(rowCount==1) {


			if(rs.first()) {
				String updateQuery = "UPDATE User SET country=" + "'" + updateUser.getCountry() + "'" 
						+ ", city=" + "'" + updateUser.getCity() + "'" 
						+ ", postal_code=" + "'" + updateUser.getPostalCode() + "'"
						+ ", street_address=" + "'" + updateUser.getStreetAddress() + "'"
						+ ", address_number=" + "'" + updateUser.getAddressNumber() + "'"
						+ " WHERE username=" + "'" + updateUser.getUsername() + "'";

				stmt.executeUpdate(updateQuery);
			}

			stmt.close();
			return true;
		}else {
			stmt.close();
			return false;
		}
	}
	
	
	public boolean updateAdditional(User updateUser) throws ClassNotFoundException, SQLException {

		Statement stmt = ConnectionManager.getConnection().createStatement();
		//check for same username before insert
		String checkQuery = "SELECT * FROM User WHERE username=" + "'" + updateUser.getUsername() + "'";

		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;


		if(rowCount==1) {


			if(rs.first()) {
				Date birthday = updateUser.getBirthday();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String updateQuery = "UPDATE User SET tel=" + "'" + updateUser.getTel() + "'" 
						+ ", birthday=" + "'" + sdf.format(birthday) + "'" 
						+ " WHERE username=" + "'" + updateUser.getUsername() + "'";

				stmt.executeUpdate(updateQuery);
			}

			stmt.close();
			return true;
		}else {
			stmt.close();
			return false;
		}
	}
}
