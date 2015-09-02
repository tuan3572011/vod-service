package com.vod.model;

import java.io.Serializable;

public class RegisInfor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String movieName;
	private String regisDate;
	private String minutesLeft;

	/**
	 * @return the movieName
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * @param movieName
	 *            the movieName to set
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	/**
	 * @return the regisDate
	 */
	public String getRegisDate() {
		return regisDate;
	}

	/**
	 * @param regisDate
	 *            the regisDate to set
	 */
	public void setRegisDate(String regisDate) {
		this.regisDate = regisDate;
	}

	/**
	 * @return the minutesLeft
	 */
	public String getMinutesLeft() {
		return minutesLeft;
	}

	/**
	 * @param minutesLeft
	 *            the minutesLeft to set
	 */
	public void setMinutesLeft(String minutesLeft) {
		this.minutesLeft = minutesLeft;
	}

}
