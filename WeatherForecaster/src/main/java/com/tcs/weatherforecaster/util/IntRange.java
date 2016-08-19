package com.tcs.weatherforecaster.util;

/**
 * Defines an Integer Range, provides a method contains to check whether the
 * given number is within the range or not.
 * 
 * @author 538366
 *
 */
public class IntRange {
	private int startNumber;
	private int endNumber;

	public IntRange(int startNumber, int endNumber) {
		this.startNumber = startNumber;
		this.endNumber = endNumber;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	public int getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(int endNumber) {
		this.endNumber = endNumber;
	}

	public boolean contains(int number) {
		return (number >= startNumber && number <= endNumber);
	}
}
