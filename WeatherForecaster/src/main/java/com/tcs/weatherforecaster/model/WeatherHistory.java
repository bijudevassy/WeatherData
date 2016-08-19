package com.tcs.weatherforecaster.model;

import org.joda.time.DateTime;

/**
 * An entity to hold WeatherHistory
 * 
 * @author Biju
 *
 */
public class WeatherHistory {
	private String location;
	private Double temperature;
	private Double minTemp;
	private Double maxTemp;
	private String event;
	private Double minDewPoint;
	private Double maxDewPoint;
	private Double dewPoint;
	private DateTime time;

	public WeatherHistory() {
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	public Double getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(Double minTemp) {
		this.minTemp = minTemp;
	}

	public Double getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(Double maxTemp) {
		this.maxTemp = maxTemp;
	}

	public Double getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(Double dewPoint) {
		this.dewPoint = dewPoint;
	}

	public Double getMinDewPoint() {
		return minDewPoint;
	}

	public void setMinDewPoint(Double minDewPoint) {
		this.minDewPoint = minDewPoint;
	}

	public Double getMaxDewPoint() {
		return maxDewPoint;
	}

	public void setMaxDewPoint(Double maxDewPoint) {
		this.maxDewPoint = maxDewPoint;
	}

	@Override
	public String toString() {
		return "WeatherHistory [location=" + location + ", temperature=" + temperature + ", minTemp=" + minTemp
				+ ", maxTemp=" + maxTemp + ", event=" + event + ", minDewPoint=" + minDewPoint + ", maxDewPoint="
				+ maxDewPoint + ", dewPoint=" + dewPoint + ", time=" + time + "]";
	}

}
