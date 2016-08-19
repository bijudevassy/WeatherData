package com.tcs.weatherforecaster.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.tcs.weatherforecaster.constants.Constants;
import com.tcs.weatherforecaster.model.WeatherHistory;
import com.tcs.weatherforecaster.model.WeatherStation;

public class EntityMappingHelper {

	/**
	 * Mapping the results of the weatherHistory query to corresponding WeatherHistory entity
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public static List<WeatherHistory> getWeatherHistory(ResultSet resultSet) throws SQLException {
		List<WeatherHistory> weatherHistory = new ArrayList<WeatherHistory>();
		while (resultSet.next()) {
			WeatherHistory weather = new WeatherHistory();
			weather.setLocation(resultSet.getString("Location"));
			weather.setEvent(resultSet.getString("Event"));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(Constants.DAY_FORMAT);
			weather.setTime(fmt.parseDateTime(resultSet.getString("Time")));
			weather.setMinTemp(resultSet.getDouble("MinTemp"));
			weather.setMaxTemp(resultSet.getDouble("MaxTemp"));
			weather.setTemperature(resultSet.getDouble("AverageTemp"));
			weather.setMaxDewPoint(resultSet.getDouble("MaxDew"));
			weather.setDewPoint(resultSet.getDouble("AverageDew"));
			weather.setMinDewPoint(resultSet.getDouble("MinDew"));
			weatherHistory.add(weather);
		}
		return weatherHistory;
	}

	/**
	 * 
	 * Mapping the results of the WeatherStation query to corresponding WeatherStation entity
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public static List<WeatherStation> getWeatherStations(ResultSet resultSet) throws SQLException {

		List<WeatherStation> weatherStations = new ArrayList<WeatherStation>();
		while (resultSet.next()) {
			WeatherStation weatherStation = new WeatherStation();
			weatherStation.setElevation(resultSet.getDouble("elevation"));
			weatherStation.setLatitude(resultSet.getDouble("latitude"));
			weatherStation.setLongitude(resultSet.getDouble("longitude"));
			weatherStation.setName(resultSet.getString("name"));
			weatherStation.setNameAbbreviation(resultSet.getString("nameshort"));
			weatherStations.add(weatherStation);
		}

		return weatherStations;
	}
}
