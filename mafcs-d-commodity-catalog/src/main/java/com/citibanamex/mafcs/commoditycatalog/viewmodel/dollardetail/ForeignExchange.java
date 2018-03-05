package com.citibanamex.mafcs.commoditycatalog.viewmodel.dollardetail;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:19 AM
 */
public class ForeignExchange {

	/**
	 * Description=It is numerical, expresses the value of one dollar (USD) in pesos
	 * (MXN), in the indicated period.(Change type dollar)
	 * Example=1.25
	 * Required=True
	 */
	private double foreignExchangeRate;
	/**
	 * Description=month number
	 * Example=01
	 * Required=True
	 */
	private int month;
	/**
	 * Description=It is numerical. It is the year from which the search rank is
	 * established, which is set as: [year - 7 years, year], for example, year entered
	 * 2009, the range would be [2002-2009]
	 * Example=2010
	 * Required=True
	 */
	private int year;

	public ForeignExchange(){
		//This is a constructor
	}

	/**
	 * Description=It is numerical, expresses the value of one dollar (USD) in pesos
	 * (MXN), in the indicated period.(Change type dollar)
	 * Example=1.25
	 * Required=True
	 */
	public double getforeignExchangeRate(){
		return foreignExchangeRate;
	}

	/**
	 * Description=It is numerical, expresses the value of one dollar (USD) in pesos
	 * (MXN), in the indicated period.(Change type dollar)
	 * Example=1.25
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setforeignExchangeRate(double newVal){
		foreignExchangeRate = newVal;
	}

	/**
	 * Description=month number
	 * Example=01
	 * Required=True
	 */
	public int getmonth(){
		return month;
	}

	/**
	 * Description=month number
	 * Example=01
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setmonth(int newVal){
		month = newVal;
	}

	/**
	 * Description=It is numerical. It is the year from which the search rank is
	 * established, which is set as: [year - 7 years, year], for example, year entered
	 * 2009, the range would be [2002-2009]
	 * Example=2010
	 * Required=True
	 */
	public int getyear(){
		return year;
	}

	/**
	 * Description=It is numerical. It is the year from which the search rank is
	 * established, which is set as: [year - 7 years, year], for example, year entered
	 * 2009, the range would be [2002-2009]
	 * Example=2010
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setyear(int newVal){
		year = newVal;
	}

}