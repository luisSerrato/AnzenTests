package com.citibanamex.mafcs.commoditycatalog.formatter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.citibanamex.mafcs.commoditycatalog.model.DollarDto;
import com.citibanamex.mafcs.commoditycatalog.model.DollarSummaryDto;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollardetail.DollarResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollardetail.ForeignExchange;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollarsummary.Currency;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollarsummary.DollarSummaryResponse;

@Service
public class DollarFormatter {

	public DollarResponse dollarFormatter(List<DollarDto> dollarList) {
		DollarResponse dollarResponse = new DollarResponse();
		List<ForeignExchange> foreignExchange = new ArrayList<>();
		
		for(DollarDto dollar: dollarList) {
			ForeignExchange foreignExchangeDetail = new ForeignExchange();
			foreignExchangeDetail.setforeignExchangeRate(dollar.getChangeType());
			foreignExchangeDetail.setmonth(dollar.getMonth());
			foreignExchangeDetail.setyear(dollar.getYear());
			
			foreignExchange.add(foreignExchangeDetail);
		}
		
		dollarResponse.setForeignExchange(foreignExchange);
		
		return dollarResponse;
	}
	
	public DollarSummaryResponse dollarSummaryFormatter(List<DollarSummaryDto> dollarList) {
		DollarSummaryResponse dollarSummaryResponse = new DollarSummaryResponse();
		List<Currency> currency = new ArrayList<>();
		
		for(DollarSummaryDto dollar: dollarList) {
			Currency currencyDetail = new Currency();
			currencyDetail.setcurrencyCode(dollar.getDollarKey());
			currencyDetail.setcountryFlagImage(dollar.getCountryFlag());
			currencyDetail.setcountryNameSpanish(dollar.getCountryNameSp());
			currencyDetail.setcountryName(dollar.getCountryNameEn());
			currencyDetail.setcurrencyDescriptionSpanish(dollar.getDollarDescSp());
			currencyDetail.setcurrencyDescription(dollar.getDollarDescEn());
			currencyDetail.setsalePrice(dollar.getSalePrice());
			currencyDetail.setbuyPrice(dollar.getBuyPrice());
			
			currency.add(currencyDetail);
		}
		
		dollarSummaryResponse.setCurrency(currency);
		return dollarSummaryResponse;
	}
	
}
