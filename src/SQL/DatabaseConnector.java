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
			} catch (SQLException ignored) {
			}
		} catch (ClassNotFoundException ignored) {
		}
		return con;
	}

}
