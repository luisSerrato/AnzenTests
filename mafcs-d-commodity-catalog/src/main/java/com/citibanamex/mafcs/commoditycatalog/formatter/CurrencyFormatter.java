package com.citibanamex.mafcs.commoditycatalog.formatter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.citibanamex.mafcs.commoditycatalog.model.CurrencyContDto;
import com.citibanamex.mafcs.commoditycatalog.model.CurrencyContinentDto;
import com.citibanamex.mafcs.commoditycatalog.model.CurrencyDto;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.Continent;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.ContinentCurrencyResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencydetail.CurrencyDetail;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencydetail.CurrencyResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary.Currency;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary.CurrencySummaryResponse;

@Service
public class CurrencyFormatter {

	public CurrencyResponse currencyResponse(List<CurrencyDto> currencies) {
		
		CurrencyResponse currencyResponse = new CurrencyResponse();
		
		List<CurrencyDetail> currencyList = new ArrayList<>();
		
		for(CurrencyDto currency: currencies) {
			
			CurrencyDetail currencyDetail = new CurrencyDetail();
			currencyDetail.setcurrencyCode(currency.getCurrencyKey());
			currencyDetail.setcountryName(currency.getCountryNameEn());
			currencyDetail.setcountryNameSpanish(currency.getCurrencyDescSp());
			currencyDetail.setcurrencyDescription(currency.getCurrencyDescEn());
			currencyDetail.setcurrencyDescriptionSpanish(currency.getCurrencyDescSp());
			currencyDetail.setsalePrice(currency.getSalePrice());
			
			currencyList.add(currencyDetail);
		}
		
		currencyResponse.setCurrencyDetail(currencyList);
		return currencyResponse;
		
	}
	
	public CurrencySummaryResponse currencySummaryResponse(List<CurrencyContDto> currencySummary){
		
		CurrencySummaryResponse currencySummaryResponse = new CurrencySummaryResponse();
		List<Currency> currencyList = new ArrayList<>();
		
		for(CurrencyContDto currencyDetail: currencySummary) {
			Currency currency = new Currency();
			currency.setcurrencyCode(currencyDetail.getCurrencyKey());
			currency.setcountryFlagImage(currencyDetail.getCountryFlag());
			currency.setcountryName(currencyDetail.getCountryNameEn());
			currency.setcountryNameSpanish(currencyDetail.getCountryNameSp());
			currency.setcurrencyDescriptionSpanish(currencyDetail.getCurrencyDescSp());
			currency.setcurrencyDescription(currencyDetail.getCurrencyDescEn());
			currency.setbuyPrice(currencyDetail.getBuyPrice());
			currency.setsalePrice(currencyDetail.getSalePrice());
			
			currencyList.add(currency);
		}
		
		currencySummaryResponse.setCurrency(currencyList);
		
		return currencySummaryResponse;
	}
	
	public ContinentCurrencyResponse currencyContinentFormatter(List<CurrencyContinentDto> currencyContinentList) {
		
		ContinentCurrencyResponse continentCurrencyResponse = new ContinentCurrencyResponse();
		List<Continent> continentList = new ArrayList<>();
		
		for(CurrencyContinentDto currencyContinentDto: currencyContinentList){
			Continent continent = new Continent();
			continent.setcontinentName(currencyContinentDto.getContinent());
			continent.setCurrency(currencyList(currencyContinentDto.getCurrencies()));
			continentList.add(continent);
		}
		
		continentCurrencyResponse.setContinent(continentList);
		return continentCurrencyResponse;
	}
	
	public List<com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.Currency> currencyList(List<CurrencyContDto> currenciesList){
		List<com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.Currency> currency = new ArrayList<>();		
		
		for(CurrencyContDto currencyDetail: currenciesList){
			com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.Currency currencyDto 
			= new com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.Currency();
			currencyDto.setcountryFlagImage(currencyDetail.getCountryFlag());
			currencyDto.setcountryNameSpanish(currencyDetail.getCountryNameSp());
			currencyDto.setcountryName(currencyDetail.getCountryNameEn());
			currencyDto.setcurrencyCode(currencyDetail.getCurrencyKey());
			currencyDto.setcurrencyDescriptionSpanish(currencyDetail.getCurrencyDescSp());
			currencyDto.setcurrencyDescription(currencyDetail.getCurrencyDescEn());
			currencyDto.setsalePrice(currencyDetail.getSalePrice());
			currencyDto.setbuyPrice(currencyDetail.getBuyPrice());
			
			currency.add(currencyDto);
		}
		
		return currency;
	}
	
}
