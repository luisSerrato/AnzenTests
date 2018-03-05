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
import com.citibanamex.mafcs.commoditycatalog.model.CurrencyDto;
import com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.service.CurrencyService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;

@Service
public class CurrencyServiceImpl implements CurrencyService{

	private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

	@Autowired
	private DatabaseMsClient databaseMsClient;
	
	@Autowired
	private SqlStatementRequestFormatter sqlStatementRequestFormatter;

	/**
	 * @param String currencyKeys claves de divisas que se pasan como parametros al query
	 * @return List<CurrencyDto> retorna una lista de divisas
	 */
	@Override
	public List<CurrencyDto> fetchCurrencyByNotKey(String[] currencyKeys) {


		SqlStatementRequest sqlStatementRequest = new SqlStatementRequest();

		sqlStatementRequest.setSql(Util.SQL_CURRENCY);

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

		List<CurrencyDto> currencyDtos = new ArrayList<>();

		List<Object[]> currencies = resultSet.getResultSet();

		for (Object[] currency : currencies) {

			String currencyKey = (String) currency[0];
			String countryNameSp = (String) currency[1];
			String countryNameEn = (String) currency[2];
			String currencyDescSp = (String) currency[3];
			String currencyDescEn = (String) currency[4];
			double salePrice;
			
			Object salePriceObject = currency[5];
			if (salePriceObject instanceof Integer) {
				Integer salePriceInteger = (Integer) salePriceObject;
				salePrice = salePriceInteger.doubleValue();
			} else {
				salePrice = (double) salePriceObject;
			}

			CurrencyDto currencyDto = new CurrencyDto();
			currencyDto.setCurrencyKey(currencyKey);
			currencyDto.setCountryNameSp(countryNameSp);
			currencyDto.setCountryNameEn(countryNameEn);
			currencyDto.setCurrencyDescSp(currencyDescSp);
			currencyDto.setCurrencyDescEn(currencyDescEn);
			currencyDto.setSalePrice(formatDecimal(salePrice,Util.DOUBLE_4_DECIMAL));

			currencyDtos.add(currencyDto);
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
