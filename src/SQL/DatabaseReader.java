package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Dylan || 25 apr. 2018 || Swaghetti
 */

public class DatabaseReader {

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

	/**
	 * Creating and submitting a query to the database connection
	 * @throws SQLException
	 */
//	public void getDataRows() throws SQLException {
//			Connection dbc = getConnection();
//			Statement st = dbc.createStatement();
//			ResultSet r = st.executeQuery("SELECT * FROM users");
//
//		while (r.next()) {
//			String username = r.getString("username");
//			String password = r.getString("password");
//			String email = r.getString("email");
//			int age = r.getInt("age");
//			System.out.format("--------- \n Account number "+ r.getRow() +" \n Username: %s \n Password: %s \n Email: %s \n Age: %s \n", username, password, email, age);
//		}
//	}

}
