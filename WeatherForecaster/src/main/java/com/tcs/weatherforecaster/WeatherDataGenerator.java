package com.tcs.weatherforecaster;

import java.util.List;

import org.joda.time.DateTime;

import com.tcs.weatherforecaster.dao.WeatherStationCSVDao;
import com.tcs.weatherforecaster.dao.WeatherStationDao;
import com.tcs.weatherforecaster.exception.ForeCasterDataException;
import com.tcs.weatherforecaster.model.WeatherStation;
import com.tcs.weatherforecaster.service.HistoryBasedForecastingService;
import com.tcs.weatherforecaster.service.WeatherForecastingService;

/**
 * Starting point of the application. This class mainly responsible for generating the weather data for given
 * user/configured inputs.
 * 
 * @author Biju
 *
 */
public class WeatherDataGenerator {

	private WeatherForecastingService service;

	public void setService(WeatherForecastingService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		WeatherDataGenerator dataGenerator = new WeatherDataGenerator();
		dataGenerator.setService(new HistoryBasedForecastingService());

		DateTime today = new DateTime();
		// For next 100 days from now
		for (int i = 0; i < 100; i++) {
			try {
				dataGenerator.generateWeatherData(today.plusDays(i));
			} catch (ForeCasterDataException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * To generate/forecast the weather data for a given day. This will generate data on hourly basis for all configured
	 * weather stations.
	 * 
	 * @param day
	 * @throws ForeCasterDataException
	 */
	public void generateWeatherData(DateTime day) throws ForeCasterDataException {

		WeatherStationDao wsDao = new WeatherStationCSVDao();
		List<WeatherStation> weatherStations = wsDao.getAllWeatherStations();
		if (weatherStations.size() > 0) {
			for (WeatherStation weatherStation : weatherStations) {

				service.forecast(weatherStation, day);

			}
		}

	}

}
