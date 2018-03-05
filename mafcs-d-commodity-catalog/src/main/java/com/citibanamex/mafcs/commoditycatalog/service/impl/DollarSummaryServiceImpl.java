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
import com.citibanamex.mafcs.commoditycatalog.model.DollarSummaryDto;
import com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.service.DollarSummaryService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;


@Service
public class DollarSummaryServiceImpl implements DollarSummaryService {

	private static final Logger logger = LoggerFactory.getLogger(DollarSummaryServiceImpl.class);

	@Autowired
	private DatabaseMsClient databaseMsClient;
	
	@Autowired
	private SqlStatementRequestFormatter sqlStatementRequestFormatter;
	
	/**
	 * @param String[] currencyKey claves del dolar params para query
	 * 
	 * @return List<DollarDto> lista con los detalles del dolar para mostrar en
	 * el resumen
	 */

	@Override
	public List<DollarSummaryDto> fetchDollarsByKey(String[] currencyKeys) {


		SqlStatementRequest sqlStatementRequest = new SqlStatementRequest();

		sqlStatementRequest.setSql(Util.SQL_DOLLAR_SUMMARY);

		Object[] params = currencyKeys;

		sqlStatementRequest.setParams(params);

		com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest request =
				sqlStatementRequestFormatter.sqlStatementRequestFormatter(sqlStatementRequest);
		
		long t0 = System.currentTimeMillis();

		SqlStatementResponse resultSet = databaseMsClient.query(request);

		String loggMessage="Time elapsed databaseMsClient.query: " + (System.currentTimeMillis() - t0) + " ms";
		
		logger.info(loggMessage);
		
		if (resultSet == null) {
			throw new DatabaseMsClientException(Util.UNAVAILABLE_DATABASE_MESSAGE);
		}

		List<DollarSummaryDto> dollarDtos = new ArrayList<>();

		List<Object[]> dollars = resultSet.getResultSet();

		for (Object[] dollar : dollars) {

			String dollarKey = (String) dollar[0];
			String countryFlag = (String) dollar[1];
			String countryNameSp = (String) dollar[2];
			String countryNameEn = (String) dollar[3];
			String dollarDescSp = (String) dollar[4];
			String dollarDescEn = (String) dollar[5];

			double buyPrice;
			Object buyPriceObject = dollar[6];
			if (buyPriceObject instanceof Integer) {
				Integer buyPriceInteger = (Integer) buyPriceObject;
				buyPrice = buyPriceInteger.doubleValue();
			} else {
				buyPrice = (double) buyPriceObject;
			}

			double salePrice;
			Object salePriceObject = dollar[7];
			if (salePriceObject instanceof Integer) {
				Integer salePriceInteger = (Integer) salePriceObject;
				salePrice = salePriceInteger.doubleValue();
			} else {
				salePrice = (double) salePriceObject;
			}

			DollarSummaryDto dollarDto = new DollarSummaryDto();
			dollarDto.setDollarKey(dollarKey);
			dollarDto.setCountryFlag(countryFlag);
			dollarDto.setCountryNameSp(countryNameSp);
			dollarDto.setCountryNameEn(countryNameEn);
			dollarDto.setDollarDescSp(dollarDescSp);
			dollarDto.setDollarDescEn(dollarDescEn);
			dollarDto.setBuyPrice(formatDecimal(buyPrice,Util.DOUBLE_2_DECIMAL));
			dollarDto.setSalePrice(formatDecimal(salePrice,Util.DOUBLE_2_DECIMAL));

			dollarDtos.add(dollarDto);
		}


		return dollarDtos;
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
