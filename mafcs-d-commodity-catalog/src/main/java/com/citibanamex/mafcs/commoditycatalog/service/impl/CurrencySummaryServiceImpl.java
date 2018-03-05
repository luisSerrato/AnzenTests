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
import com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.service.CurrencySummaryService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;

@Service
public class CurrencySummaryServiceImpl implements CurrencySummaryService {

	private static final Logger logger = LoggerFactory.getLogger(CurrencySummaryServiceImpl.class);

	@Autowired
	private DatabaseMsClient databaseMsClient;
	
	@Autowired
	private SqlStatementRequestFormatter sqlStatementRequestFormatter;

	/**
	 * @param String[] currencyKeys claves de divisas que pasan como parametros
	 * para el query
	 * 
	 * @return List<CurrencyDto> lista de divisas por claves
	 */

	@Override
	public List<CurrencyContDto> fetchCurrencyByKey(String[] currencyKeys) {


		SqlStatementRequest sqlStatementRequest = new SqlStatementRequest();

		sqlStatementRequest.setSql(Util.SQL_SUMMARY);

		Object[] params = currencyKeys;

		sqlStatementRequest.setParams(params);

		com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest request =
				sqlStatementRequestFormatter.sqlStatementRequestFormatter(sqlStatementRequest);
		
		long t0 = System.currentTimeMillis();
		
		SqlStatementResponse resultSet = databaseMsClient.query(request);

		String loggerMessage="Time elapsed databaseMsClient.query: " + (System.currentTimeMillis() - t0) + " ms";
		
		logger.info(loggerMessage);

		if (resultSet == null) {
			throw new DatabaseMsClientException(Util.UNAVAILABLE_DATABASE_MESSAGE);
		}

		List<CurrencyContDto> currencyDtos = new ArrayList<>();

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

			CurrencyContDto currencySummaryDto = new CurrencyContDto();
			currencySummaryDto.setCurrencyKey(currencyKey);
			currencySummaryDto.setCountryFlag(countryFlag);
			currencySummaryDto.setCountryNameSp(countryNameSp);
			currencySummaryDto.setCountryNameEn(countryNameEn);
			currencySummaryDto.setCurrencyDescSp(currencyDescSp);
			currencySummaryDto.setCurrencyDescEn(currencyDescEn);
			currencySummaryDto.setBuyPrice(formatDecimal(buyPrice,Util.DOUBLE_2_DECIMAL));
			currencySummaryDto.setSalePrice(formatDecimal(salePrice,Util.DOUBLE_2_DECIMAL));

			currencyDtos.add(currencySummaryDto);
		}


		return currencyDtos;
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
