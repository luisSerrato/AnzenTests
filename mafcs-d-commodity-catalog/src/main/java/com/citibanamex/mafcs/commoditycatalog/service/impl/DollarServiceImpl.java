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
import com.citibanamex.mafcs.commoditycatalog.model.DollarDto;
import com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.service.DollarService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;

@Service
public class DollarServiceImpl implements DollarService {

	private static final Logger logger = LoggerFactory.getLogger(DollarServiceImpl.class);

	@Autowired
	private DatabaseMsClient databaseMsClient;
	
	@Autowired
	private SqlStatementRequestFormatter sqlStatementRequestFormatter;
	
	/**
	 * @param int year entero de 4 digitos el cual se le restan 7 años para
	 * establecer los peridos en el query
	 * 
	 * @return List<DollarDto> periodos del historico del dolar
	 */

	@Override
	public List<DollarDto> fetchDollarsByYear(int yearParam) {

		SqlStatementRequest sqlStatementRequest = new SqlStatementRequest();

		Object[] params = { yearParam - 7, yearParam };

		sqlStatementRequest.setSql(Util.SQL_DOLLAR);
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

		List<DollarDto> dollarDtos = new ArrayList<>();

		List<Object[]> dollars = resultSet.getResultSet();

		for (Object[] dollar : dollars) {
			
			String month = (String) dollar[0];
			int year = (int) dollar[1];

			double tChange;
			Object buyPriceObject = dollar[2];
			if (buyPriceObject instanceof Integer) {
				Integer buyPriceInteger = (Integer) buyPriceObject;
				tChange = buyPriceInteger.doubleValue();
			} else {
				tChange = (double) buyPriceObject;
			}

			DollarDto dollarDto = new DollarDto();

			dollarDto.setMonth(Util.getMonthNumber(month));
			dollarDto.setYear(year);
			dollarDto.setChangeType(formatDecimal(tChange, Util.DOUBLE_2_DECIMAL));

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
