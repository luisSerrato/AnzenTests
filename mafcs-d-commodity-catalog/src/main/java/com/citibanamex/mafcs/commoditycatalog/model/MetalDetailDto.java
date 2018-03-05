package com.citibanamex.mafcs.commoditycatalog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MetalDetailDto {
	private String metalKey;
	private String coin;
	private String pesosMetal;
	private double buyPrice;
	private double salePrice;

	@ApiModelProperty(position = 1, required = true, value = "Metal key")
	public String getMetalKey() {
		return metalKey;
	}

	public void setMetalKey(String metalKeyValue) {
		metalKey = metalKeyValue;
	}

	@ApiModelProperty(position = 2, required = true, value = "Coin name")
	public String getCoin() {
		return coin;
	}

	public void setCoin(String coinValue) {
		coin = coinValue;
	}

	@ApiModelProperty(position = 3, required = true, value = "Metal value in pesos")
	public String getPesosMetal() {
		return pesosMetal;
	}

	public void setPesosMetal(String pesosMetalValue) {
		pesosMetal = pesosMetalValue;
	}

	@ApiModelProperty(position = 4, required = true, value = "Metal buy price")
	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPriceValue) {
		buyPrice = buyPriceValue;
	}

	@ApiModelProperty(position = 5, required = true, value = "Metal sale price")
	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePriceValue) {
		salePrice = salePriceValue;
	}
}
