package com.citibanamex.mafcs.commoditycatalog.model;

import javax.validation.constraints.NotNull;

public class CurrenciesRequest {
	@NotNull
	private String[] currencyKeys;

	public String[] getCurrencyKeys() {
		return currencyKeys;
	}

	public void setCurrencyKeys(String[] currencyKeysValue) {
		currencyKeys = currencyKeysValue;
	}

}
