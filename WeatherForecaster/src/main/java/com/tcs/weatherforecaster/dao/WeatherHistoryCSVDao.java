package com.tcs.weatherforecaster.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.tcs.weatherforecaster.constants.Constants;
import com.tcs.weatherforecaster.db.CSVConnectionFactory;
import com.tcs.weatherforecaster.exception.ForeCasterDataException;
import com.tcs.weatherforecaster.helper.EntityMappingHelper;
import com.tcs.weatherforecaster.model.WeatherHistory;

/**
 * This class responsible for retrieving the data from weatherhistory csv file. Which is placed in
 * resources/weatherhistory.csv file.
 * 
 * @author Biju
 *
 */

public class WeatherHistoryCSVDao implements WeatherHistoryDao {

	public WeatherHistory getHistoricalWeather(String location, DateTime time) throws ForeCasterDataException {
		Connection conn = null;
		WeatherHistory history = null;
		ResultSet resultSet = null;
		Statement stmt = null;
		try {
			conn = CSVConnectionFactory.getConnection();
			stmt = conn.createStatement();
			DateTimeFormatter fmt = DateTimeFormat.forPattern(Constants.DAY_FORMAT);
			String str = time.toString(fmt);
			String sql = String
					.format(
							"SELECT Location,Event,Time,MinTemp,MaxTemp,AverageTemp,MaxDew,AverageDew,MinDew from weatherhistory where Location ='%s' and  Time='%s'",
							location, str);
			resultSet = stmt.executeQuery(sql);

			List<WeatherHistory> weatherHistory = EntityMappingHelper.getWeatherHistory(resultSet);

			if (weatherHistory.size() > 0) {
				history = weatherHistory.get(0);
			}

		} catch (SQLException se) {
			throw new ForeCasterDataException("Error while fetching data" + se.getMessage());
		} catch (Exception e) {
			throw new ForeCasterDataException("Error while fetching data" + e.getMessage());
		} finally {
			try {
				DbUtils.close(resultSet);
				DbUtils.close(stmt);
				DbUtils.close(conn);
			} catch (SQLException se) {
				throw new ForeCasterDataException("Error while fetching data" + se.getMessage());
			}
		}
		return history;
	}
}
