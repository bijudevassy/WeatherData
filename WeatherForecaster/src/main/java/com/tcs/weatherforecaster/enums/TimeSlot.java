package com.tcs.weatherforecaster.enums;

import static com.tcs.weatherforecaster.constants.Constants.*;

import com.tcs.weatherforecaster.util.IntRange;

/**
 * Each day is divided into different time slots on hourly start and end basis. Timeslots are used to map the
 * temperature into different slots. This needs to be externalised by considering location and other parameters like
 * seasons. Day light saving and location specific aspects are not considered in scope. Values are in 24 hour
 * formats.eg:0-7 means 0am to 7am. For each slot starting and ending temperature is mapped.This helps in randomization
 * of values. Used in ForecastingHelper class.
 * 
 * @author Biju
 *
 */
public enum TimeSlot {
	SLOT1(new IntRange(0, 7), MINIMUM_TEMPERATURE, AVERAGE_TEMPERATURE), SLOT2(new IntRange(7, 13), AVERAGE_TEMPERATURE,
			MAXIMUM_TEMPERATURE), SLOT3(new IntRange(13, 20), MAXIMUM_TEMPERATURE, AVERAGE_TEMPERATURE), SLOT4(
			new IntRange(20, 24), AVERAGE_TEMPERATURE, MINIMUM_TEMPERATURE);
	private IntRange timeRange;
	private String minTemperature;
	private String maxTemperature;

	TimeSlot(IntRange timeRange, String minTemperature, String maxTemperature) {
		this.timeRange = timeRange;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
	}

	public IntRange getTimeRange() {
		return timeRange;
	}

	public String getMinTemperature() {
		return minTemperature;
	}

	public String getMaxTemperature() {
		return maxTemperature;
	}

	/**
	 * Will return the timeslot for the given hour. Checks whether the given hour is in the time range of each slot, when
	 * matches returns that timeslot instance.
	 * 
	 * @param hour
	 * @return
	 */
	public static TimeSlot getTimeSlot(int hour) {
		TimeSlot timeSlot = null;
		for (TimeSlot slot : TimeSlot.values()) {
			if (slot.getTimeRange().contains(hour)) {
				timeSlot = slot;
			}
		}
		return timeSlot;
	}

}
