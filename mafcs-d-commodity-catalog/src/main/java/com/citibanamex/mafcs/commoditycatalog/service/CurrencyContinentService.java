package com.citibanamex.mafcs.commoditycatalog.service;

import java.util.List;

import com.citibanamex.mafcs.commoditycatalog.model.CurrencyContinentDto;

public interface CurrencyContinentService {

	public List<CurrencyContinentDto> fetchCurrencyContinentByKey(String[] currencyKeys);
	public double formatDecimal(double value, String pattern);
	
}
