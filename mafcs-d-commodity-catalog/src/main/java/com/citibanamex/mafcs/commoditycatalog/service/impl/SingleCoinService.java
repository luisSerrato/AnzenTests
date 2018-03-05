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
import com.citibanamex.mafcs.commoditycatalog.model.CoinDto;
import com.citibanamex.mafcs.commoditycatalog.model.MetalDetailDto;
import com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;


@Service("singleCoinService")
public class SingleCoinService {

	private static final Logger logger = LoggerFactory.getLogger(SingleCoinService.class);

	@Autowired
	private DatabaseMsClient databaseMsClient;
	
	@Autowired
	private SqlStatementRequestFormatter sqlStatementRequestFormatter;	
	
	public CoinDto fetchSingleCoinByNames(String[] metalKeys) {
		CoinDto coinDto = new CoinDto();

		coinDto.setCoinName("");

		SqlStatementRequest sqlStatementRequest = new SqlStatementRequest();

		Object[] paramKeys = metalKeys;

		sqlStatementRequest.setSql(Util.SQLBYMETALKEYS);
		sqlStatementRequest.setParams(paramKeys);

		
		long t0 = System.currentTimeMillis();
		
		com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest request =
				sqlStatementRequestFormatter.sqlStatementRequestFormatter(sqlStatementRequest);
				
		SqlStatementResponse resultSet = databaseMsClient.query(request);
		
		String loggMessage="Time elapsed databaseMsClient.query: " + (System.currentTimeMillis() - t0) + " ms";
		
		logger.info(loggMessage);

		if (resultSet == null) {
			throw new DatabaseMsClientException(Util.UNAVAILABLE_DATABASE_MESSAGE);
		}

		List<MetalDetailDto> metalDetailDtos = new ArrayList<>();

		coinDto.setCoins(metalDetailDtos);

		List<Object[]> metals = resultSet.getResultSet();

		for (Object[] metal : metals) {
			
			String metalKey = (String) metal[0];
			String coin = (String) metal[1];
			String pesosMetal = (String) metal[2];
			int buyPrice = (int) metal[3];
			int salePrice = (int) metal[4];

			MetalDetailDto metaDetail = new MetalDetailDto();
			metaDetail.setMetalKey(metalKey);
			metaDetail.setCoin(coin);
			metaDetail.setPesosMetal(pesosMetal);
			metaDetail.setBuyPrice(buyPrice);
			metaDetail.setSalePrice(salePrice);

			metalDetailDtos.add(metaDetail);
		}

		return coinDto;

	}



}