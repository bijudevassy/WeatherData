package com.tcs.weatherforecaster;

import junit.framework.TestCase;

import com.tcs.weatherforecaster.util.Util;

/**
 * Unit test class for Util.
 */
public class UtilTest extends TestCase {

	/**
	 * Tests weather the random values are generating as per the given conditions.
	 */
	public void testRandomValue() {
		Double randomValue = Util.rand(10, 20);
		assertTrue(randomValue > 10 && randomValue < 20);
	}

}
