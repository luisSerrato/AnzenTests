package com.citibanamex.mafcs.commoditycatalog.viewmodel.currencydetail;

import java.util.List;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:19 AM
 */
public class CurrencyResponse {

	private List<CurrencyDetail> currencyDetail;

	public CurrencyResponse(){
		//This is a constructor
	}

	public List<CurrencyDetail> getCurrencyDetail() {
		return currencyDetail;
	}

	public void setCurrencyDetail(List<CurrencyDetail> currencyDetail) {
		this.currencyDetail = currencyDetail;
	}
	
}