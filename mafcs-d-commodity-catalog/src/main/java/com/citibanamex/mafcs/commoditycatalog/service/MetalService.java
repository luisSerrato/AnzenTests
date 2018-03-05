package com.citibanamex.mafcs.commoditycatalog.service;

import java.util.List;

import com.citibanamex.mafcs.commoditycatalog.model.MetalDto;

public interface MetalService {
	
	public List<MetalDto> fetchMetalsByKeys(String[] metalNames, String[] metalKeys);
	
}
