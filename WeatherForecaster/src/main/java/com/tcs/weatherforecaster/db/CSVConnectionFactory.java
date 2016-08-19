package com.tcs.weatherforecaster.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Responsible for providing connection objects for accessing CSV db.
 * 
 * @author Biju
 *
 */
public class CSVConnectionFactory {
	private static CSVConnectionFactory instance = new CSVConnectionFactory();
	public static final String URL = "jdbc:relique:csv:./resources/";
	public static final String DRIVER_CLASS = "org.relique.jdbc.csv.CsvDriver";

	// private constructor
	private CSVConnectionFactory() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL);

		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}

	/**
	 * Create and returns connection.
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		return instance.createConnection();
	}
}