package com.citibanamex.mafcs.commoditycatalog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citibanamex.mafcs.commoditycatalog.databasemsclient.DatabaseMsClient;
import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.DatabaseMsClientException;
import com.citibanamex.mafcs.commoditycatalog.formatter.SqlStatementRequestFormatter;
import com.citibanamex.mafcs.commoditycatalog.model.MetalSummaryDto;
import com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.service.MetalService;
import com.citibanamex.mafcs.commoditycatalog.service.MetalSummaryService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;

@Service("metalSummaryService")
public class MetalSummaryServiceImpl implements MetalSummaryService{
	
	@Autowired
	private DatabaseMsClient databaseMsClient;
	
	@Autowired
	private SqlStatementRequestFormatter sqlStatementRequestFormatter;
	
	private static final Logger logger = LoggerFactory.getLogger(MetalService.class);

	@Override
	public List<MetalSummaryDto> fetchMetalsByKey(String[] metalKeys){
	
		
		SqlStatementRequest sqlStatementRequest = new SqlStatementRequest();
		
		sqlStatementRequest.setSql(Util.SQL);
		
		Object[] params = metalKeys;
		
		sqlStatementRequest.setParams(params);
		
		long t0=System.currentTimeMillis();

		com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest request =
				sqlStatementRequestFormatter.sqlStatementRequestFormatter(sqlStatementRequest);
						
		SqlStatementResponse resultSet = databaseMsClient.query(request);
		
		String loggMessage="Time elapsed databaseMsClient.query: " + (System.currentTimeMillis() - t0) + " ms";
		
		logger.info(loggMessage);

		if (resultSet == null) {
			throw new DatabaseMsClientException(Util.UNAVAILABLE_DATABASE_MESSAGE);
		}
		
		List<MetalSummaryDto> metalDtos=new ArrayList<>();
		
		List<Object[]> metals =  resultSet.getResultSet();
		
		for(Object[] metal: metals){
			
			String metalKey = (String) metal[0];
			String coin = (String) metal[1];
			String pesosMetal = (String) metal[2];
			
			MetalSummaryDto metalDto = new MetalSummaryDto();
			metalDto.setMetalKey(metalKey);
			metalDto.setCoin(coin);
			metalDto.setPesosMetal(pesosMetal);
			
			double buyPrice;
			
			Object buyPriceObject = metal[3];
			if (buyPriceObject instanceof Integer) {
				Integer buyPriceInteger = (Integer) buyPriceObject;
				buyPrice = buyPriceInteger.doubleValue();
			} else {
				buyPrice = (double) buyPriceObject;
			}
			
			double salePrice;
			
			Object salePriceObject = metal[4];
			if (salePriceObject instanceof Integer) {
				Integer salePriceInteger = (Integer) salePriceObject;
				salePrice = salePriceInteger.doubleValue();
			} else {
				salePrice = (double) salePriceObject;
			}
			
			metalDto.setBuyPrice(buyPrice);
			metalDto.setSalePrice(salePrice);
			
			metalDtos.add(metalDto);
		}
		
		
		return metalDtos;
	}


}
