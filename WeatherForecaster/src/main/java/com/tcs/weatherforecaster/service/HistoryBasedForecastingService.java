package com.tcs.weatherforecaster.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

import com.tcs.weatherforecaster.constants.Constants;
import com.tcs.weatherforecaster.dao.WeatherHistoryCSVDao;
import com.tcs.weatherforecaster.dao.WeatherHistoryDao;
import com.tcs.weatherforecaster.enums.Event;
import com.tcs.weatherforecaster.exception.ForeCasterDataException;
import com.tcs.weatherforecaster.helper.ForecastingHelper;
import com.tcs.weatherforecaster.model.Environment;
import com.tcs.weatherforecaster.model.WeatherHistory;
import com.tcs.weatherforecaster.model.WeatherStation;

/**
 * HistoryBased forecasting implementation of WeatherForecastingService
 * 
 * 
 * @author Biju
 *
 */
public class HistoryBasedForecastingService implements WeatherForecastingService {

	/**
	 * Calls the required prediction methods to encapsulate environment model and prints the result.
	 * 
	 */
	public final void forecast(WeatherStation station, DateTime day) throws ForeCasterDataException {

		Map<Integer, Double> hourlyTemperatures = predictHourlyTemperature(station, day);

		if (hourlyTemperatures != null) {

			Set<Integer> keys = hourlyTemperatures.keySet();
			Iterator<Integer> iterator = keys.iterator();
			while (iterator.hasNext()) {
				Integer hour = iterator.next();
				Double temperature = hourlyTemperatures.get(hour);

				Double dewPoint = getDewPoint(station, day);

				Double pressure = predictPressure(temperature, station.getElevation());

				Double humidity = predictHumidity(temperature, dewPoint);

				Event event = predictEvent(temperature, humidity);

				printData(new Environment(station, temperature, pressure, humidity, event, day.withHourOfDay(hour)));
			}
		}
	}

	/**
	 * 
	 * Forecasting temperature for a given workstation and date. Returns hourly temperatures.
	 */
	protected Map<Integer, Double> predictHourlyTemperature(WeatherStation station, DateTime time)
			throws ForeCasterDataException {
		DateTime timeWithbaseYear = time.withYear(Constants.BASE_YEAR);
		WeatherHistoryDao dao = new WeatherHistoryCSVDao();
		WeatherHistory weatherHistory = dao.getHistoricalWeather(station.getName(), timeWithbaseYear);
		Map<Integer, Double> hourlyTemperatures = null;
		if (weatherHistory != null) {

			hourlyTemperatures = ForecastingHelper.getHourlyTemperaturePrediction(weatherHistory);
		}
		return hourlyTemperatures;
	}

	/**
	 * 
	 * Getting dewpoint from base date. temperatures.
	 */
	protected Double getDewPoint(WeatherStation station, DateTime time) throws ForeCasterDataException {
		Double dewPoint = ForecastingHelper.getDewPoint(station.getName(), time);
		return dewPoint;
	}

	/**
	 * 
	 * Predicting pressure using temperature and elevation.
	 */
	protected Double predictPressure(Double temperature, Double elevation) {

		Double pressure = ForecastingHelper.predictPressure(temperature, elevation);

		return pressure;
	}

	/**
	 * Predicting humidity from temperature and dewpoint.
	 * 
	 */

	protected Double predictHumidity(Double temperature, Double dewPoint) {
		Double humidity = ForecastingHelper.predictHumidity(temperature, dewPoint);

		return humidity;
	}

	/**
	 * Predicting event from temperature and humidity.
	 * 
	 */

	protected Event predictEvent(Double temperature, Double humidity) {

		Event event = ForecastingHelper.predictEvent(temperature, humidity);

		return event;
	}

	/**
	 * 
	 * Will print the environment data in below format. SYD|-33.865143|151.2099|2016
	 * -07-24T21:43:37.720+05:30|RAIN|14.48139004575324|1012.8151285891958 |77.00051642576999
	 * 
	 */
	protected void printData(Environment environment) {
		// TODO change to logger with configuration
		System.out.println(environment);

	}

}
