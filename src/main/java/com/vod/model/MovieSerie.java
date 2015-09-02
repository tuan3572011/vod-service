package com.vod.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class MovieSerie extends Film implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numEpisodes;

	public MovieSerie() {
		setType("serie");
	}

	public int getNumEpisodes() {
		return numEpisodes;
	}

	public void setNumEpisodes(int numEpisodes) {
		this.numEpisodes = numEpisodes;
	}

}
