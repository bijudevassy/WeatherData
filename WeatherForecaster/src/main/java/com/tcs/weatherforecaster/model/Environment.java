package com.tcs.weatherforecaster.model;

import org.joda.time.DateTime;

import com.tcs.weatherforecaster.enums.Event;

/**
 * Environment entity
 * 
 * @author Biju
 *
 */
public class Environment {
	private WeatherStation workStation;
	private DateTime time;
	private Double temperature;
	private Double pressure;
	private Double humidity;
	private Event event;

	public Environment(WeatherStation station, Double temperature, Double pressure, Double humidity, Event event,
			DateTime hourOfDay) {
		this.workStation = station;
		this.time = hourOfDay;
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		this.event = event;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public WeatherStation getWorkStation() {
		return workStation;
	}

	public void setWorkStation(WeatherStation workStation) {
		this.workStation = workStation;
	}

	@Override
	public String toString() {
		return workStation.getNameAbbreviation() + "|" + workStation.getLatitude() + "|" + workStation.getLongitude()
				+ "|" + time + "|" + event + "|" + temperature + "|" + pressure + " |" + humidity;
	}

}
