package com.tcs.weatherforecaster.dao;

import java.util.List;

import com.tcs.weatherforecaster.exception.ForeCasterDataException;
import com.tcs.weatherforecaster.model.WeatherStation;

public interface WeatherStationDao {

	public List<WeatherStation> getAllWeatherStations()
			throws ForeCasterDataException;

}
