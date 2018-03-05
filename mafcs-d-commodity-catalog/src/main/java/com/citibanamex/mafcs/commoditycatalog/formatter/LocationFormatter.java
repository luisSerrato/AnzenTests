package com.citibanamex.mafcs.commoditycatalog.formatter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.citibanamex.mafcs.commoditycatalog.model.CountryEntity;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.countrydetail.Country;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.countrydetail.RetrieveCountryDetailResponse;

@Component
public class LocationFormatter {

	public RetrieveCountryDetailResponse locationResponseFormatter(List<CountryEntity> ltCountryEntity){
		RetrieveCountryDetailResponse countryResponse =  new RetrieveCountryDetailResponse();
		
		for (CountryEntity countryEntity : ltCountryEntity) {
			Country country = new Country();
			
			country.setcountryName(countryEntity.getPais());
			countryResponse.getCountry().add(country);		
		}
	
		return countryResponse;
	}
	
}
