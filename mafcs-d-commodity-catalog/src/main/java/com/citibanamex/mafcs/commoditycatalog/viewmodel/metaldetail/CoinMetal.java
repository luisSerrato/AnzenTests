package com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:18 AM
 */
public class CoinMetal {

	/**
	 * Description=metal coin category
	 * Example=onzasoro
	 * Required=True
	 */
	private String coinCategory;
	private List<Coin> coin;

	public CoinMetal(){
		this.setCoin(new ArrayList<>());
	}

	/**
	 * Description=metal coin category
	 * Example=onzasoro
	 * Required=True
	 */
	public String getcoinCategory(){
		return coinCategory;
	}

	/**
	 * Description=metal coin category
	 * Example=onzasoro
	 * Required=True
	 * 
	 * @param newVal
	 */
	public void setcoinCategory(String newVal){
		coinCategory = newVal;
	}

	public List<Coin> getCoin() {
		return coin;
	}

	public void setCoin(List<Coin> coin) {
		this.coin = coin;
	}

	
	
}