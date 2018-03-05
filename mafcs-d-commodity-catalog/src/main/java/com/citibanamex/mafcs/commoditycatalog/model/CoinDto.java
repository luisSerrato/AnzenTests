package com.citibanamex.mafcs.commoditycatalog.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CoinDto {
	private String coinName;
	private List<MetalDetailDto> metalDetails;

	@ApiModelProperty(position = 1, required = true, value = "Coin name")
	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinNameValue) {
		coinName = coinNameValue;
	}

	@ApiModelProperty(position = 2, required = true, value = "Coin collection")
	public List<MetalDetailDto> getCoins() {
		return metalDetails;
	}

	public void setCoins(List<MetalDetailDto> coinsValue) {
		metalDetails = coinsValue;
	}
}
