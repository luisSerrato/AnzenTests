package com.citibanamex.mafcs.commoditycatalog.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MetalDto {
	private String metalName;
	private List<CoinDto> coinMetals;

	@ApiModelProperty(position = 1, required = true, value = "Metal name")
	public String getMetal() {
		return metalName;
	}

	public void setMetal(String metalValue) {
		metalName = metalValue;
	}

	@ApiModelProperty(position = 2, required = true, value = "Coins collection")
	public List<CoinDto> getCoins() {
		return coinMetals;
	}

	public void setCoins(List<CoinDto> coinsValue) {
		coinMetals = coinsValue;
	}
}
