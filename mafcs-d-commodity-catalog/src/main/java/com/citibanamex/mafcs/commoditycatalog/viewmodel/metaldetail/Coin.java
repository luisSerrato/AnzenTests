package com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:17 AM
 */
public class Coin {

	/**
	 * Description=Price of purchase of the metal
	 * Example=1.35
	 * Required=True
	 */
	private double buyPrice;
	/**
	 * Description=Metal Coin
	 * Example="OroLibertad"
	 * Required=True
	 */
	private String coinDenomination;
	/**
	 * Description=Metal code
	 * Example=OLIB
	 * Required=True
	 */
	private String metalCode;
	/**
	 * Description=metal cost Value in mexican pesos
	 * Example=1800
	 * Required=True
	 */
	private String metalValue;
	/**
	 * Description=sale price
	 * Example=1.40
	 * Required=TRUE
	 */
	private double salePrice;

	public Coin(){
		// This is a constructor
	}

	/**
	 * Description=Price of purchase of the metal
	 * Example=1.35
	 * Required=True
	 */
	public double getbuyPrice(){
		return buyPrice;
	}

	/**
	 * Description=Price of purchase of the metal
	 * Example=1.35
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setbuyPrice(double newVal){
		buyPrice = newVal;
	}

	/**
	 * Description=Metal Coin
	 * Example="OroLibertad"
	 * Required=True
	 */
	public String getcoinDenomination(){
		return coinDenomination;
	}

	/**
	 * Description=Metal Coin
	 * Example="OroLibertad"
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setcoinDenomination(String newVal){
		coinDenomination = newVal;
	}

	/**
	 * Description=Metal code
	 * Example=OLIB
	 * Required=True
	 */
	public String getmetalCode(){
		return metalCode;
	}

	/**
	 * Description=Metal code
	 * Example=OLIB
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setmetalCode(String newVal){
		metalCode = newVal;
	}

	/**
	 * Description=metal cost Value in mexican pesos
	 * Example=1800
	 * Required=True
	 */
	public String getmetalValue(){
		return metalValue;
	}

	/**
	 * Description=metal cost Value in mexican pesos
	 * Example=1800
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setmetalValue(String newVal){
		metalValue = newVal;
	}

	/**
	 * Description=sale price
	 * Example=1.40
	 * Required=TRUE
	 */
	public double getsalePrice(){
		return salePrice;
	}

	/**
	 * Description=sale price
	 * Example=1.40
	 * Required=TRUE
	 * 
	 * @param newVal
	 */
	public void setsalePrice(double newVal){
		salePrice = newVal;
	}

}