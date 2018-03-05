package com.citibanamex.mafcs.commoditycatalog.viewmodel.dollarsummary;

import java.util.List;

/**
 * @author jp34651
 * @version 1.0
 * @created 14-Dec-2017 11:07:19 AM
 */
public class DollarSummaryResponse {

	private List<Currency> currency;

	public DollarSummaryResponse(){
		//This is a constructor
	}

	public List<Currency> getCurrency() {
		return currency;
	}

	public void setCurrency(List<Currency> currency) {
		this.currency = currency;
	}
	
}