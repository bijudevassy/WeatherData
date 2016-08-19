package com.tcs.weatherforecaster.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains Common utility methods.
 * 
 * @author Biju
 *
 */
public class Util {

	/**
	 * Returns a random number which will be a number between given 2 numbers.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static double rand(double min, double max) {
		double randomNum = min + (Math.random() * ((max - min)));
		return randomNum;
	}

	/**
	 * Converts ResultSet to HashMap of List<String,Object>
	 * 
	 * @param row
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */

	public static List<Map<String, Object>> getResultMap(ResultSet resultSet) throws SQLException {
		List<Map<String, Object>> row = new ArrayList<Map<String, Object>>();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int colCount = metaData.getColumnCount();

		while (resultSet.next()) {
			Map<String, Object> columns = new HashMap<String, Object>();
			for (int i = 1; i <= colCount; i++) {
				columns.put(metaData.getColumnLabel(i), resultSet.getObject(i));
			}

			row.add(columns);
		}
		return row;
	}
}
