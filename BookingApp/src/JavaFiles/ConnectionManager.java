package JavaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection connection;

	public static Connection openConnection() throws SQLException, ClassNotFoundException {
		String myDriver = "com.mysql.jdbc.Driver";
		String myUrl = "jdbc:mysql://localhost:3306/mydb?useSSL=true";

		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, "root", "root1@");
		return conn;

	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		if (connection == null || connection.isClosed() || !connection.isValid(5)) {
			connection = openConnection();
		}

		return connection;

	}
}
