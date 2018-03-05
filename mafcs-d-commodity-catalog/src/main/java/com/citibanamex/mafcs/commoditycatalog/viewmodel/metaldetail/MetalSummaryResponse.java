package com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:20 AM
 */
public class MetalSummaryResponse {

	private List<Coin> coin;

	public MetalSummaryResponse(){
		this.setCoin(new ArrayList<>());
	}

	public List<Coin> getCoin() {
		return coin;
	}

	public void setCoin(List<Coin> coin) {
		this.coin = coin;
	}

	
}