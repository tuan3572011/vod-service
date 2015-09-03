/**
 * 
 */
package com.vod.model;

/**
 * @author approved
 *
 */
public class FilterBean {

	private int orderBy;
	private Category category;
	private int year;
	private int countryId;
	private int page;

	public FilterBean(int orderBy, Category categoryId, int year, int countryId, int page) {
		this.orderBy = orderBy;
		this.category = categoryId;
		this.countryId = countryId;
		this.year = year;
		this.page = page;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public boolean isSearchAny() {
		return this.isSearchByCategory() || isSearchByCountry() || isSearchByYear();
	}

	public boolean isSearchByCategory() {
		if (this.category == null) {
			return false;
		}
		return true;
	}

	public boolean isSearchByYear() {
		if (this.year == 0) {
			return false;
		}
		return true;
	}

	public boolean isSearchByCountry() {
		if (this.countryId == 13) {
			return false;
		}
		return true;
	}

	public boolean isSearchByOrder() {
		if (this.orderBy == 0) {
			return false;
		}
		return true;
	}
}
