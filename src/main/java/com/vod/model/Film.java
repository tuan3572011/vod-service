package com.vod.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.WRAPPER_OBJECT, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Movie.class, name = "movie"),
		@JsonSubTypes.Type(value = MovieSerie.class, name = "serie"),
		@JsonSubTypes.Type(value = Episodes.class, name = "episode") })
public abstract class Film implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "film_id")
	private Integer id;

	private String type;
	private String name;
	private String engName;
	private String vnName;
	private String image;
	private String info;
	private String trailer;
	private int publishedYear;
	private Date updatedDate = new Date();
	private double imdb;
	private double price;
	private String quality;

	@ManyToOne
	@JoinColumn(name = "director_id")
	private Director director;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "film_starring", joinColumns = { @JoinColumn(name = "film_id") }, inverseJoinColumns = { @JoinColumn(name = "starring_id") })
	private Set<Starring> starrings = new HashSet<Starring>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "film_category", joinColumns = { @JoinColumn(name = "film_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	private Set<Category> categories = new HashSet<Category>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "film_country", joinColumns = { @JoinColumn(name = "film_id") }, inverseJoinColumns = { @JoinColumn(name = "country_id") })
	private Set<Country> countries = new HashSet<Country>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getVnName() {
		return vnName;
	}

	public void setVnName(String vnName) {
		this.vnName = vnName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	public double getImdb() {
		return imdb;
	}

	public void setImdb(double imdb) {
		this.imdb = imdb;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Set<Starring> getStarrings() {
		return starrings;
	}

	public void setStarrings(Set<Starring> starrings) {
		this.starrings = starrings;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Country> getCountries() {
		return countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
