package com.citibanamex.mafcs.commoditycatalog.service;

import java.util.List;

import com.citibanamex.mafcs.commoditycatalog.model.CurrencyContDto;

public interface CurrencySummaryService {

	public List<CurrencyContDto> fetchCurrencyByKey(String[] currencyKeys);
	public double formatDecimal(double value, String pattern);
	
}
