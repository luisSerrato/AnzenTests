package com.citibanamex.mafcs.commoditycatalog.viewmodel.countrydetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sk72342
 * @version 1.0
 * @created 14-Dec-2017 11:07:20 AM
 */
public class RetrieveCountryDetailResponse {

	private List<Country> country;

	public RetrieveCountryDetailResponse(){
		this.setCountry(new ArrayList<>());
	}

	public List<Country> getCountry() {
		return country;
	}

	public void setCountry(List<Country> country) {
		this.country = country;
	}

}