package com.tcs.weatherforecaster.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.tcs.weatherforecaster.db.CSVConnectionFactory;
import com.tcs.weatherforecaster.exception.ForeCasterDataException;
import com.tcs.weatherforecaster.helper.EntityMappingHelper;
import com.tcs.weatherforecaster.model.WeatherStation;

/**
 * This class responsible for retrieving the data from workstation csv file. Which is placed in
 * resources/workstation.csv file.
 * 
 * @author Biju
 *
 */
public class WeatherStationCSVDao implements WeatherStationDao {

	/**
	 * To retrieve all workstations.
	 */
	public List<WeatherStation> getAllWeatherStations() throws ForeCasterDataException {
		Connection conn = CSVConnectionFactory.getConnection();
		List<WeatherStation> weatherStations = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT name,nameshort,latitude,longitude,elevation from weatherstation");
			weatherStations = EntityMappingHelper.getWeatherStations(resultSet);
		} catch (SQLException e) {
			throw new ForeCasterDataException("Error while fetching data" + e.getMessage());
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException se) {
				throw new ForeCasterDataException("Error while fetching data" + se.getMessage());
			}

		}
		return weatherStations;
	}

}
