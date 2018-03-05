package com.citibanamex.mafcs.commoditycatalog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CurrencyDto {
	private String currencyKey;
	private String countryNameSp;
	private String countryNameEn;
	private String currencyDescSp;
	private String currencyDescEn;
	private double salePrice;

	@ApiModelProperty(position = 1, required = true, value = "Currency Key")
	public String getCurrencyKey() {
		return currencyKey;
	}

	public void setCurrencyKey(String currencyKeyValue) {
		currencyKey = currencyKeyValue;
	}

	@ApiModelProperty(position = 2, required = true, value = "Country name in spanish")
	public String getCountryNameSp() {
		return countryNameSp;
	}

	public void setCountryNameSp(String countryNameSpValue) {
		countryNameSp = countryNameSpValue;
	}

	@ApiModelProperty(position = 3, required = true, value = "Country name in english")
	public String getCountryNameEn() {
		return countryNameEn;
	}

	public void setCountryNameEn(String countryNameEnValue) {
		countryNameEn = countryNameEnValue;
	}

	@ApiModelProperty(position = 4, required = true, value = "Currency description in spanish")
	public String getCurrencyDescSp() {
		return currencyDescSp;
	}

	public void setCurrencyDescSp(String currencyDescSpValue) {
		currencyDescSp = currencyDescSpValue;
	}

	@ApiModelProperty(position = 5, required = true, value = "Currency description english")
	public String getCurrencyDescEn() {
		return currencyDescEn;
	}

	public void setCurrencyDescEn(String currencyDescEnValue) {
		currencyDescEn = currencyDescEnValue;
	}
	
	@ApiModelProperty(position = 6, required = true, value = "Currency sale price")
	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePriceValue) {
		salePrice = salePriceValue;
	}

}
