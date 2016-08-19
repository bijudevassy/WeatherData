package com.tcs.weatherforecaster.model;

/**
 * WeatherStation entity
 * 
 * @author Biju
 *
 */
public class WeatherStation {

	private String name;
	private String nameAbbreviation;
	private Double latitude;
	private Double longitude;
	private Double elevation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAbbreviation() {
		return nameAbbreviation;
	}

	public void setNameAbbreviation(String nameAbbreviation) {
		this.nameAbbreviation = nameAbbreviation;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

}
