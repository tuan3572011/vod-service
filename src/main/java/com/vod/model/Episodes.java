package com.vod.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Episodes extends MovieOdd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int noEpisode;

	public Episodes() {
		setType("episode");
	}

	@ManyToOne
	private MovieSerie movieSerie;

	public int getNoEpisode() {
		return noEpisode;
	}

	public void setNoEpisode(int noEpisode) {
		this.noEpisode = noEpisode;
	}

	public MovieSerie getMovieSerie() {
		return movieSerie;
	}

	public void setMovieSerie(MovieSerie movieSerie) {
		this.movieSerie = movieSerie;
	}
}
