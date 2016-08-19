package com.tcs.weatherforecaster.service;

import org.joda.time.DateTime;

import com.tcs.weatherforecaster.exception.ForeCasterDataException;
import com.tcs.weatherforecaster.model.WeatherStation;

/**
 * General Contract defined for forecasting service.
 * 
 * @author Biju
 *
 */

public interface WeatherForecastingService {

	/**
	 * To predict and print hourly weather data for a given date for a
	 * workstation.
	 * 
	 * @param workStation
	 * @param time
	 * @return
	 * @throws ForeCasterDataException
	 */

	public void forecast(WeatherStation station, DateTime time) throws ForeCasterDataException;
}
