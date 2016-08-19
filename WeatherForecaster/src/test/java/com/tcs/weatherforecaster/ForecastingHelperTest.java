package com.tcs.weatherforecaster;

import com.tcs.weatherforecaster.enums.Event;
import com.tcs.weatherforecaster.helper.ForecastingHelper;

import junit.framework.TestCase;

/**
 * Unit test class for ForecastingHelper.
 */
public class ForecastingHelperTest extends TestCase {

	/**
	 * Positive test case to check predict event method
	 */
	public void testProperWeatherEvent() {
		assertTrue(ForecastingHelper.predictEvent(-5d, 10d) == Event.SNOW);
	}

	/**
	 * Negative test case to check predict event method
	 */
	public void testWeatherEvent() {
		assertFalse(ForecastingHelper.predictEvent(0d, 10d) == Event.SUNNY);
	}

}
