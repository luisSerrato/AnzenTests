package com.citibanamex.mafcs.commoditycatalog.service;

import java.util.List;

import com.citibanamex.mafcs.commoditycatalog.model.DollarSummaryDto;

public interface DollarSummaryService {

	public List<DollarSummaryDto> fetchDollarsByKey(String[] currencyKeys);
	public double formatDecimal(double value, String pattern);
	
}
