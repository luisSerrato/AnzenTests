package com.citibanamex.mafcs.commoditycatalog.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citibanamex.mafcs.commoditycatalog.databasemsclient.DatabaseMsClient;
import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.DatabaseMsClientException;
import com.citibanamex.mafcs.commoditycatalog.formatter.SqlStatementRequestFormatter;
import com.citibanamex.mafcs.commoditycatalog.model.CurrencyContDto;
import com.citibanamex.mafcs.commoditycatalog.model.CurrencyContinentDto;
import com.citibanamex.mafcs.commoditycatalog.service.CurrencyContinentService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;

@Service
public class CurrencyContinentServiceImpl implements CurrencyContinentService{
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyContinentServiceImpl.class);

	@Autowired
	private DatabaseMsClient databaseMsClient;
	
	@Autowired
	private SqlStatementRequestFormatter sqlStatementRequestFormatter;

	/**
	 * @param String[] currencyKeys params para el sql
	 * 
	 * @return List<CurrencyDto> Lista con las divisas mapeadas por continente
	 */
	
	@Override
	public List<CurrencyContinentDto> fetchCurrencyContinentByKey(String[] currencyKeys) {


		List<CurrencyContinentDto> lsCurrencies = new ArrayList<>();

		com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest sqlStatementRequest;

		for (int i = 0; i < currencyKeys.length; i++) {

			sqlStatementRequest = new com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest();

			sqlStatementRequest.setSql(Util.SQL_CONTINENT);

			Object[] params = new Object[1];
			params[0] = currencyKeys[i];

			sqlStatementRequest.setParams(params);
			
			com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest request =
					sqlStatementRequestFormatter.sqlStatementRequestFormatter(sqlStatementRequest);

			CurrencyContinentDto currencyContinent = new CurrencyContinentDto();

			currencyContinent.setContinent(currencyKeys[i]);

			List<CurrencyContDto> currencyDtos = new ArrayList<>();

			currencyContinent.setCurrencies(currencyDtos);

			long t0 = System.currentTimeMillis();
			
			SqlStatementResponse resultSet = databaseMsClient.query(request);
			
			String loggerMessage="Time elapsed databaseMsClient.query: " + (System.currentTimeMillis() - t0) + " ms";
			
			logger.info(loggerMessage);
			
			if (resultSet == null) {
				throw new DatabaseMsClientException(Util.UNAVAILABLE_DATABASE_MESSAGE);
			}

			List<Object[]> currencies = resultSet.getResultSet();

			for (Object[] currency : currencies) {

				String currencyKey = (String) currency[0];
				String countryFlag = (String) currency[1];
				String countryNameSp = (String) currency[2];
				String countryNameEn = (String) currency[3];
				String currencyDescSp = (String) currency[4];
				String currencyDescEn = (String) currency[5];
				double buyPrice;
				double salePrice;
				
				Object buyPriceObject = currency[6];
				if (buyPriceObject instanceof Integer) {
					Integer buyPriceInteger = (Integer) buyPriceObject;
					buyPrice = buyPriceInteger.doubleValue();
				} else {
					buyPrice = (double) buyPriceObject;
				}
				
				Object salePriceObject = currency[7];
				if (salePriceObject instanceof Integer) {
					Integer salePriceInteger = (Integer) salePriceObject;
					salePrice = salePriceInteger.doubleValue();
				} else {
					salePrice = (double) salePriceObject;
				}

				CurrencyContDto currencyContDto = new CurrencyContDto();
				currencyContDto.setCurrencyKey(currencyKey);
				currencyContDto.setCountryFlag(countryFlag);
				currencyContDto.setCountryNameSp(countryNameSp);
				currencyContDto.setCountryNameEn(countryNameEn);
				currencyContDto.setCurrencyDescSp(currencyDescSp);
				currencyContDto.setCurrencyDescEn(currencyDescEn);
				currencyContDto.setBuyPrice(formatDecimal(buyPrice,Util.DOUBLE_4_DECIMAL));
				currencyContDto.setSalePrice(formatDecimal(salePrice,Util.DOUBLE_4_DECIMAL));

				currencyDtos.add(currencyContDto);
			}

			lsCurrencies.add(currencyContinent);
		}



		return lsCurrencies;
	}
	
	@Override
	public double formatDecimal(double value, String pattern){
		DecimalFormat decimalFormat= new DecimalFormat(pattern);
		
		String doubleStr=decimalFormat.format(value);
		
		double doubleFormat = 0;
		try{
			doubleFormat= Double.parseDouble(doubleStr);
		}catch(NumberFormatException e){
			logger.error(e.getMessage(), e);
		}
		return doubleFormat;
	}
}
