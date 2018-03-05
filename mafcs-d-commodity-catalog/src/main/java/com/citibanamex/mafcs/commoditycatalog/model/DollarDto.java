package com.citibanamex.mafcs.commoditycatalog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DollarDto {

	private int month;
	private int year;
	private double changeType;

	@ApiModelProperty(position = 1, required = true, value = "Month")
	public int getMonth() {
		return month;
	}

	public void setMonth(int monthValue) {
		month = monthValue;
	}

	@ApiModelProperty(position = 2, required = true, value = "Year")
	public int getYear() {
		return year;
	}

	public void setYear(int yearValue) {
		year = yearValue;
	}

	@ApiModelProperty(position = 3, required = true, value = "Change type dollar")
	public double getChangeType() {
		return changeType;
	}

	public void setChangeType(double changeTypeValue) {
		changeType = changeTypeValue;
	}

}
