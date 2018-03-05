package com.citibanamex.mafcs.commoditycatalog.service;

import java.util.List;

import com.citibanamex.mafcs.commoditycatalog.model.CurrencyDto;

public interface CurrencyService {

	public List<CurrencyDto> fetchCurrencyByNotKey(String[] currencyKeys);
	public double formatDecimal(double value, String pattern);
	
}
