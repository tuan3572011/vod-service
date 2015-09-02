package com.vod.model;

import java.io.Serializable;

public class ParaSearch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeSearch;
	private String dataSearch;

	public String getTypeSearch() {
		return typeSearch;
	}

	public void setTypeSearch(String typeSearch) {
		this.typeSearch = typeSearch;
	}

	public String getDataSearch() {
		return dataSearch;
	}

	public void setDataSearch(String dataSearch) {
		this.dataSearch = dataSearch;
	}

	@Override
	public String toString() {
		return "ParaSearch [typeSearch=" + typeSearch + ", dataSearch="
				+ dataSearch + "]";
	}

}
