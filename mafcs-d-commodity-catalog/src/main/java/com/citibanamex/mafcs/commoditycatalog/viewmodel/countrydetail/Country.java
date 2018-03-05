package com.citibanamex.mafcs.commoditycatalog.viewmodel.countrydetail;

/**
 * @author hg48153
 * @version 1.0
 * @created 14-Dec-2017 11:07:18 AM
 */
public class Country {

	/**
	 * Description= Country code of address
	 * Example= AFGHANISTAN
	 * Required= False
	 */
	private String countryName;

	public Country(){
		//This is a constructor
	}

	/**
	 * Description= Country code of address
	 * Example= AFGHANISTAN
	 * Required= False
	 */
	public String getcountryName(){
		return countryName;
	}

	/**
	 * Description= Country code of address
	 * Example= AFGHANISTAN
	 * Required= False
	 * 
	 * @param newVal
	 */
	public void setcountryName(String newVal){
		countryName = newVal;
	}

}