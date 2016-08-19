package com.tcs.weatherforecaster.dao;

import org.joda.time.DateTime;

import com.tcs.weatherforecaster.exception.ForeCasterDataException;
import com.tcs.weatherforecaster.model.WeatherHistory;

public interface WeatherHistoryDao {

	public WeatherHistory getHistoricalWeather(String location, DateTime time)
			throws ForeCasterDataException;
}
