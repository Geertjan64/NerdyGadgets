package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Dylan
 */

public class DatabaseConnector {

	/**
	 * Initializers
	 */
	private static Connection con;

	/**
	 * Connection to the database
	 */
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onzedbwwi", "root", "");
			} catch (SQLException e) {
				System.out.println("No connection with database!");
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver was not found");
		}
		return con;
	}

}
