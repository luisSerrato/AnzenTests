package com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:19 AM
 */
public class Metal {

	/**
	 * Description=coin metal Type
	 * Example=Oro
	 * Required=True
	 */
	private String metalType;
	private List<CoinMetal> coinMetal;

	public Metal(){
		this.setCoinMetal(new ArrayList<>());
	}

	/**
	 * Description=coin metal Type
	 * Example=Oro
	 * Required=True
	 */
	public String getmetalType(){
		return metalType;
	}

	/**
	 * Description=coin metal Type
	 * Example=Oro
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setmetalType(String newVal){
		metalType = newVal;
	}

	public List<CoinMetal> getCoinMetal() {
		return coinMetal;
	}

	public void setCoinMetal(List<CoinMetal> coinMetal) {
		this.coinMetal = coinMetal;
	}

	
}