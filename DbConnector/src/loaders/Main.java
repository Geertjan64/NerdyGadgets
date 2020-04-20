package loaders;

import java.sql.SQLException;

import db.DatabaseHandler;

/**
 * @author Dylan || 26 apr. 2018 || Swaghetti
 */

public class Main {

	static DatabaseHandler dbh = new DatabaseHandler();

	public static void main(String[] args) throws SQLException {
		dbh.getDataRows();
	}
}
