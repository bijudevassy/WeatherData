package com.tcs.weatherforecaster;

import com.tcs.weatherforecaster.util.IntRange;

import junit.framework.TestCase;

/**
 * Unit test class for IntRange.
 */
public class IntRangeTest extends TestCase {

	/**
	 * Tests whether the given number is in the Range
	 */
	public void testContains() {
		IntRange box = new IntRange(0, 100);
		assertTrue(box.contains(50));
	}

	/**
	 * Tests whether the given number is not in the Range
	 */
	public void testNotContains() {
		IntRange box = new IntRange(0, 100);
		assertFalse(box.contains(101));
	}

}
