package com.citibanamex.mafcs.commoditycatalog.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CurrencyContinentDto {
	
	private String continent;
	private List<CurrencyContDto> currencies;

	@ApiModelProperty(position = 1, required = true, value = "Continent")
	public String getContinent() {
		return continent;
	}

	public void setContinent(String continentValue) {
		continent = continentValue;
	}

	@ApiModelProperty(position = 2, required = true, value = "Collection of currencies")
	public List<CurrencyContDto> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<CurrencyContDto> currenciesValue) {
		currencies = currenciesValue;
	}

}
