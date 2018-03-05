package com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent;

import java.util.List;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:18 AM
 */
public class ContinentCurrencyResponse {

	private List<Continent> continent;

	public ContinentCurrencyResponse(){
		//This is a constructor
	}

	public List<Continent> getContinent() {
		return continent;
	}

	public void setContinent(List<Continent> continent) {
		this.continent = continent;
	}
	
}