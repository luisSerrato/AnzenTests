package com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:20 AM
 */
public class MetalResponse {

	private List<Metal> metal;

	public MetalResponse(){
		this.setMetal(new ArrayList<>());
	}

	public List<Metal> getMetal() {
		return metal;
	}

	public void setMetal(List<Metal> metal) {
		this.metal = metal;
	}

	
	
}