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
import com.citibanamex.mafcs.commoditycatalog.model.MetalDto;
import com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.service.MetalService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;

@Service("metalService")
public class MetalServiceImpl implements MetalService{

	
	@Autowired
	private DatabaseMsClient databaseMsClient;
	
	@Autowired
	private SingleCoinService singleCoinService;
	
	@Autowired
	private SqlStatementRequestFormatter sqlStatementRequestFormatter;
	
	private static final Logger logger = LoggerFactory.getLogger(MetalService.class);

	@Override
	public List<MetalDto> fetchMetalsByKeys(String[] metalNames, String[] metalKeys) {

		List<CoinDto> coinsOro = new ArrayList<>();
		List<CoinDto> coinsPlata = new ArrayList<>();

		MetalDto oro = new MetalDto();
		oro.setMetal(Util.getMetalNames()[0]);
		oro.setCoins(coinsOro);

		MetalDto plata = new MetalDto();
		plata.setMetal(Util.getMetalNames()[1]);
		plata.setCoins(coinsPlata);

		List<MetalDto> allMetals = new ArrayList<>();

		allMetals.add(oro);
		allMetals.add(plata);

		coinsOro.add(singleCoinService.fetchSingleCoinByNames(metalKeys));

		SqlStatementRequest sqlStatementRequest = new SqlStatementRequest();

		for (int i = 0; i < metalNames.length; i++) {

			Object[] params = new Object[Util.PARAMS_LENGTH];
			params[0] = metalNames[i];

			sqlStatementRequest.setSql(Util.SQLBYCOINNAME);
			sqlStatementRequest.setParams(params);

			List<MetalDetailDto> metalOroDetailDtos = new ArrayList<>();

			List<MetalDetailDto> metalPlataDetailDtos = new ArrayList<>();

			CoinDto coinDto = new CoinDto();

			long t0 = System.currentTimeMillis();

			com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest request =
					sqlStatementRequestFormatter.sqlStatementRequestFormatter(sqlStatementRequest);	
			
			SqlStatementResponse resultSet = databaseMsClient.query(request);
			
			String loggMessage="Time elapsed databaseMsClient.query: " + (System.currentTimeMillis() - t0) + " ms";
			
			logger.info(loggMessage);

			if (resultSet == null) {
				throw new DatabaseMsClientException(Util.UNAVAILABLE_DATABASE_MESSAGE);
			}

			List<Object[]> metals =  resultSet.getResultSet();
			

			for (Object[] metal : metals) {
								
				MetalDetailDto metalDetail = loadMetalDetail(metal);

				if (i < Util.COINS_POSITION) {
					metalOroDetailDtos.add(metalDetail);
				} else {
					metalPlataDetailDtos.add(metalDetail);
				}

			}
			coinDto.setCoinName(metalNames[i]);

			if (i < Util.COINS_POSITION) {
				coinDto.setCoins(metalOroDetailDtos);
				coinsOro.add(coinDto);

			} else {
				coinDto.setCoins(metalPlataDetailDtos);
				coinsPlata.add(coinDto);
			}
		}



		return allMetals;
	}

	
	public MetalDetailDto loadMetalDetail(Object[] metalDetailList){
		String metalKey = (String) metalDetailList[0];
		String coin = (String) metalDetailList[1];
		String pesosMetal = (String) metalDetailList[2];
		double buyPrice;
		double salePrice;
		
		Object buyPriceObject = metalDetailList[3];
		if (buyPriceObject instanceof Integer) {
			Integer buyPriceInteger = (Integer) buyPriceObject;
			buyPrice = buyPriceInteger.doubleValue();
		} else {
			buyPrice = (double) buyPriceObject;
		}
		
		Object salePriceObject = metalDetailList[4];
		if (salePriceObject instanceof Integer) {
			Integer salePriceInteger = (Integer) salePriceObject;
			salePrice = salePriceInteger.doubleValue();
		} else {
			salePrice = (double) salePriceObject;
		}

		MetalDetailDto metalDetail = new MetalDetailDto();
		metalDetail.setMetalKey(metalKey);
		metalDetail.setCoin(coin);
		metalDetail.setPesosMetal(pesosMetal);
		metalDetail.setBuyPrice(buyPrice);
		metalDetail.setSalePrice(salePrice);
		
		return metalDetail;
	}

}

