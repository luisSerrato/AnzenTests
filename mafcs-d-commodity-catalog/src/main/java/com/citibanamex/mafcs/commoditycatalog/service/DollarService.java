package com.citibanamex.mafcs.commoditycatalog.service;

import java.util.List;

import com.citibanamex.mafcs.commoditycatalog.model.DollarDto;

public interface DollarService {

	public List<DollarDto> fetchDollarsByYear(int yearParam);
	public double formatDecimal(double value, String pattern);
	
}
