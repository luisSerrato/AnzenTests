package com.citibanamex.mafcs.commoditycatalog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CurrencyContDto {
	
	private String currencyKey;
	private String countryFlag;
	private String countryNameSp;
	private String countryNameEn;
	private String currencyDescSp;
	private String currencyDescEn;
	private double buyPrice;
	private double salePrice;

	@ApiModelProperty(position = 1, required = true, value = "Currency Key")
	public String getCurrencyKey() {
		return currencyKey;
	}

	public void setCurrencyKey(String currencyKeyValue) {
		currencyKey = currencyKeyValue;
	}

	@ApiModelProperty(position = 2, required = true, value = "Country flag image name")
	public String getCountryFlag() {
		return countryFlag;
	}

	public void setCountryFlag(String countryFlagValue) {
		countryFlag = countryFlagValue;
	}

	@ApiModelProperty(position = 3, required = true, value = "Country name in spanish")
	public String getCountryNameSp() {
		return countryNameSp;
	}

	public void setCountryNameSp(String countryNameSpValue) {
		countryNameSp = countryNameSpValue;
	}

	@ApiModelProperty(position = 4, required = true, value = "Country name in english")
	public String getCountryNameEn() {
		return countryNameEn;
	}

	public void setCountryNameEn(String countryNameEnValue) {
		countryNameEn = countryNameEnValue;
	}

	@ApiModelProperty(position = 5, required = true, value = "Currency description in spanish")
	public String getCurrencyDescSp() {
		return currencyDescSp;
	}

	public void setCurrencyDescSp(String currencyDescSpValue) {
		currencyDescSp = currencyDescSpValue;
	}

	@ApiModelProperty(position = 6, required = true, value = "Domain Url")
	public String getCurrencyDescEn() {
		return currencyDescEn;
	}

	public void setCurrencyDescEn(String currencyDescEnValue) {
		currencyDescEn = currencyDescEnValue;
	}

	@ApiModelProperty(position = 6, required = true, value = "Buy price currency")
	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPriceValue) {
		buyPrice = buyPriceValue;
	}

	@ApiModelProperty(position = 7, required = true, value = "Sale price currency")
	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePriceValue) {
		salePrice = salePriceValue;
	}


}
