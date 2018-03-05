package com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent;

import java.util.List;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:18 AM
 */
public class Continent {

	/**
	 * Description=Continent
	 * Example=NORTH AMERICA
	 * Required=True
	 */
	private String continentName;
	private List<Currency> currency;

	public Continent(){
		//This is a constructor
	}

	/**
	 * Description=Continent
	 * Example=NORTH AMERICA
	 * Required=True
	 */
	public String getcontinentName(){
		return continentName;
	}

	/**
	 * Description=Continent
	 * Example=NORTH AMERICA
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setcontinentName(String newVal){
		continentName = newVal;
	}

	public List<Currency> getCurrency() {
		return currency;
	}

	public void setCurrency(List<Currency> currency) {
		this.currency = currency;
	}

	
}