package com.citibanamex.mafcs.commoditycatalog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DollarSummaryDto {
	private String dollarKey;
	private String countryFlag;
	private String countryNameSp;
	private String countryNameEn;
	private String dollarDescSp;
	private String dollarDescEn;
	private double buyPrice;
	private double salePrice;

	@ApiModelProperty(position = 1, required = true, value = "Dollar key")
	public String getDollarKey() {
		return dollarKey;
	}

	public void setDollarKey(String dollarKeyValue) {
		dollarKey = dollarKeyValue;
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

	@ApiModelProperty(position = 5, required = true, value = "Dollar description in spanish")
	public String getDollarDescSp() {
		return dollarDescSp;
	}

	public void setDollarDescSp(String dollarDescSpValue) {
		dollarDescSp = dollarDescSpValue;
	}

	@ApiModelProperty(position = 6, required = true, value = "Dollar description in english")
	public String getDollarDescEn() {
		return dollarDescEn;
	}

	public void setDollarDescEn(String dollarDescEnValue) {
		dollarDescEn = dollarDescEnValue;
	}

	@ApiModelProperty(position = 7, required = true, value = "Dollar buy price")
	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPriceValue) {
		buyPrice = buyPriceValue;
	}

	@ApiModelProperty(position = 8, required = true, value = "Dollar sale price")
	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePriceValue) {
		salePrice = salePriceValue;
	}

}
