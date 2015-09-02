package com.vod.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Movie extends MovieOdd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Movie() {
		setType("movie");
	}
}
