package com.citibanamex.mafcs.commoditycatalog.viewmodel.currencydetail;

/**
 * @author hg48153
 * @version 1.0
 * @created 14-Dec-2017 11:07:19 AM
 */
public class CurrencyDetail {

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

	public CurrencyDetail(){
		//This is a constructor
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