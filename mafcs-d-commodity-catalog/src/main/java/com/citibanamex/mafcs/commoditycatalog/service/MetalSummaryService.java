package com.citibanamex.mafcs.commoditycatalog.service;

import java.util.List;

import com.citibanamex.mafcs.commoditycatalog.model.MetalSummaryDto;

public interface MetalSummaryService {

	public List<MetalSummaryDto> fetchMetalsByKey(String[] metalKeys);

	
	
}
