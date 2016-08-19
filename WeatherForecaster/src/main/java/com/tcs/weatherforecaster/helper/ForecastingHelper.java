package com.tcs.weatherforecaster.helper;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.tcs.weatherforecaster.constants.Constants;
import com.tcs.weatherforecaster.dao.WeatherHistoryCSVDao;
import com.tcs.weatherforecaster.dao.WeatherHistoryDao;
import com.tcs.weatherforecaster.enums.Event;
import com.tcs.weatherforecaster.enums.TimeSlot;
import com.tcs.weatherforecaster.exception.ForeCasterDataException;
import com.tcs.weatherforecaster.model.WeatherHistory;
import com.tcs.weatherforecaster.util.Util;

/**
 * Helper class which provides methods to forecast various weather attributes.
 * 
 * @author Biju
 *
 */
public class ForecastingHelper {

	/**
	 * To get the hourly temperatures for a given day by considering the base years temperature. For each hour in a day,
	 * the temperature is assigned based on timeslot configuration.
	 * 
	 * @param weatherHistory
	 * @return
	 */
	public static Map<Integer, Double> getHourlyTemperaturePrediction(WeatherHistory weatherHistory) {
		Map<String, Double> temperatureMap = new HashMap<String, Double>();
		temperatureMap.put(Constants.MINIMUM_TEMPERATURE, weatherHistory.getMinTemp());
		temperatureMap.put(Constants.MAXIMUM_TEMPERATURE, weatherHistory.getMaxTemp());
		temperatureMap.put(Constants.AVERAGE_TEMPERATURE, weatherHistory.getTemperature());

		Map<Integer, Double> hourlyTemperatures = new HashMap<Integer, Double>();

		for (int hour = 0; hour < 24; hour++) {
			TimeSlot timeSlot = TimeSlot.getTimeSlot(hour);
			if (timeSlot != null) {
				Double slotMinimumTemperatue = temperatureMap.get(timeSlot.getMinTemperature());
				Double slotMaximumTemperatue = temperatureMap.get(timeSlot.getMaxTemperature());
				hourlyTemperatures.put(hour, Util.rand(slotMinimumTemperatue, slotMaximumTemperatue));
				// TODO sorting of temperatures within slots can be applied to get a gradient effect on the temperatures
			}

		}

		return hourlyTemperatures;
	}

	/**
	 * Using hypsometric formula to get pressure. Ref: http://keisan.casio.com/exec/system/1224579725
	 * 
	 * @param temperature
	 * @param elevation
	 * @return
	 */

	public static Double predictPressure(Double temperature, Double elevation) {
		double numerator = (0.0065 * elevation);
		double denominator = temperature + numerator + Constants.TEMPERATURE_CONSTANT;
		double pressure = 1 - (numerator / denominator);
		double pressureInPascal = Constants.TO_PASCAL * pressure;
		return pressureInPascal;
	}

	/**
	 * 
	 * To get dewpoint based on given parameters
	 * 
	 * @param wsName
	 * @param time
	 * @return
	 * @throws ForeCasterDataException
	 */
	public static Double getDewPoint(String wsName, DateTime time) throws ForeCasterDataException {
		DateTime yearBack = time.minusYears(1);
		WeatherHistoryDao dao = new WeatherHistoryCSVDao();
		WeatherHistory historyData = dao.getHistoricalWeather(wsName, yearBack);
		return historyData.getDewPoint();
	}

	/**
	 * To find Relative Humidity,RH: =100*(EXP((17.625*TD)/(243.04+TD))/EXP((17.625*T)/(243.04+T))) *
	 * Ref:http://andrew.rsmas.miami.edu/bmcnoldy/Humidity.html
	 * 
	 * @param temperature
	 * @param location
	 * @param proximityToSeaOrLake
	 * @return
	 */
	public static Double predictHumidity(Double temperature, Double dewPoint) {

		// TODO externalise the constants
		Double humidity = 100 * (((17.625 * dewPoint) / (243.04 + dewPoint)) / ((17.625 * temperature) / (243.04 + temperature)));

		return humidity;

	}

	/**
	 * To predict event based on given parameters
	 * 
	 * @param temperature
	 * @param humidity
	 * @return
	 */
	// FIXME need to consider more parameters like dew and time for proper
	// prediction of events
	public static Event predictEvent(Double temperature, Double humidity) {
		Event event = null;
		// TODO Externalise the rules and predict based on that.
		if (temperature < 5) {
			event = Event.SNOW;
		} else if (temperature > 5 && temperature < 25 && humidity > 50) {
			event = Event.RAIN;
		} else {
			event = Event.SUNNY;
		}
		return event;

	}
}
