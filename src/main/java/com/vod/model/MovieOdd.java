package com.vod.model;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;


@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.WRAPPER_OBJECT, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Movie.class, name = "movie"),
		@JsonSubTypes.Type(value = Episodes.class, name = "episode") })
public abstract class MovieOdd extends Film implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int view;
	private int rate;
	private String movie_key;

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getMovie_key() {
		return movie_key;
	}

	public void setMovie_key(String movie_key) {
		this.movie_key = movie_key;
	}

}
