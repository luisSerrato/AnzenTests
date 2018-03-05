package com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:18 AM
 */
public class Currency {

	private double buyPrice;
	private String countryFlagImage;
	/**
	 * Description= country name in English
	 * Example= Canada
	 * Required=
	 */
	private String countryName;
	/**
	 * Description= country name in Spanish
	 * Example= Canada
	 * Required=
	 */
	private String countryNameSpanish;
	/**
	 * Description= currency Code
	 * Example= CAD
	 * Required=
	 */
	private String currencyCode;
	/**
	 * Description= currency description in English
	 * Example= Dollar
	 * Required=
	 */
	private String currencyDescription;
	/**
	 * Description= currency description in Spanish
	 * Example= D�lar
	 * Required=
	 */
	private String currencyDescriptionSpanish;
	/**
	 * Description= sale Price
	 * Example= 1.30
	 * Required=
	 */
	private double salePrice;

	public Currency(){
		//This is a constructor
	}

	public double getbuyPrice(){
		return buyPrice;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setbuyPrice(double newVal){
		buyPrice = newVal;
	}

	public String getcountryFlagImage(){
		return countryFlagImage;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcountryFlagImage(String newVal){
		countryFlagImage = newVal;
	}

	/**
	 * Description= country name in English
	 * Example= Canada
	 * Required=
	 */
	public String getcountryName(){
		return countryName;
	}

	/**
	 * Description= country name in English
	 * Example= Canada
	 * Required=
	 * 
	 * @param newVal
	 */
	public void setcountryName(String newVal){
		countryName = newVal;
	}

	/**
	 * Description= country name in Spanish
	 * Example= Canada
	 * Required=
	 */
	public String getcountryNameSpanish(){
		return countryNameSpanish;
	}

	/**
	 * Description= country name in Spanish
	 * Example= Canada
	 * Required=
	 * 
	 * @param newVal
	 */
	public void setcountryNameSpanish(String newVal){
		countryNameSpanish = newVal;
	}

	/**
	 * Description= currency Code
	 * Example= CAD
	 * Required=
	 */
	public String getcurrencyCode(){
		return currencyCode;
	}

	/**
	 * Description= currency Code
	 * Example= CAD
	 * Required=
	 * 
	 * @param newVal
	 */
	public void setcurrencyCode(String newVal){
		currencyCode = newVal;
	}

	/**
	 * Description= currency description in English
	 * Example= Dollar
	 * Required=
	 */
	public String getcurrencyDescription(){
		return currencyDescription;
	}

	/**
	 * Description= currency description in English
	 * Example= Dollar
	 * Required=
	 * 
	 * @param newVal
	 */
	public void setcurrencyDescription(String newVal){
		currencyDescription = newVal;
	}

	/**
	 * Description= currency description in Spanish
	 * Example= D�lar
	 * Required=
	 */
	public String getcurrencyDescriptionSpanish(){
		return currencyDescriptionSpanish;
	}

	/**
	 * Description= currency description in Spanish
	 * Example= D�lar
	 * Required=
	 * 
	 * @param newVal
	 */
	public void setcurrencyDescriptionSpanish(String newVal){
		currencyDescriptionSpanish = newVal;
	}

	/**
	 * Description= sale Price
	 * Example= 1.30
	 * Required=
	 */
	public double getsalePrice(){
		return salePrice;
	}

	/**
	 * Description= sale Price
	 * Example= 1.30
	 * Required=
	 * 
	 * @param newVal
	 */
	public void setsalePrice(double newVal){
		salePrice = newVal;
	}

}