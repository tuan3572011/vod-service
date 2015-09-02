package com.vod.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rate")
public class Rate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rate_id")
	private Integer id;

	private RateEnum starNo;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	private MovieOdd movieOdd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RateEnum getStarNo() {
		return starNo;
	}

	public void setStarNo(RateEnum starNo) {
		this.starNo = starNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MovieOdd getMovieOdd() {
		return movieOdd;
	}

	public void setMovieOdd(MovieOdd movieOdd) {
		this.movieOdd = movieOdd;
	}

}
