package com.citibanamex.mafcs.commoditycatalog.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citibanamex.mafcs.commoditycatalog.databasemsclient.DatabaseMsClient;
import com.citibanamex.mafcs.commoditycatalog.formatter.MetalFormatter;
import com.citibanamex.mafcs.commoditycatalog.formatter.MetalSummaryFormatter;
import com.citibanamex.mafcs.commoditycatalog.formatter.SqlStatementRequestFormatter;
import com.citibanamex.mafcs.commoditycatalog.model.CoinDto;
import com.citibanamex.mafcs.commoditycatalog.model.MetalDetailDto;
import com.citibanamex.mafcs.commoditycatalog.model.MetalDto;
import com.citibanamex.mafcs.commoditycatalog.model.MetalSummaryDto;
import com.citibanamex.mafcs.commoditycatalog.service.impl.MetalServiceImpl;
import com.citibanamex.mafcs.commoditycatalog.service.impl.MetalSummaryServiceImpl;
import com.citibanamex.mafcs.commoditycatalog.service.impl.SingleCoinService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalSummaryResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MetalServiceTest {

	@Configuration
	static class MetalServiceTestConfigurarion{
		
		@Bean
		public MetalSummaryService metalSummaryService(){
			return new MetalSummaryServiceImpl();
		}
		
		@Bean
		public MetalService metalService(){
			return new MetalServiceImpl();
		}
		
		@Bean
		public SingleCoinService singleCoinService(){
			return new SingleCoinService();
		}
		
		@Bean
		public MetalFormatter metalFormatter(){
			return new MetalFormatter();
		}
		
		
		@Bean
		public MetalSummaryFormatter metalSummaryFormatter(){
			return new MetalSummaryFormatter();
		}
		
		@Bean
		public SqlStatementRequestFormatter sqlStatementRequestFormatter(){
			return new SqlStatementRequestFormatter();
		}
	}
	
	
	@Autowired
	private MetalSummaryService metalSummaryService;
	
	@Autowired
	private MetalService metalService;
	
	@Autowired
	private MetalFormatter metalFormatter;
	
	@Autowired 
	private MetalSummaryFormatter metalSummaryFormatter;
	
	@MockBean
	private DatabaseMsClient databaseMsClient;
	
	private List<MetalDto> allMetals;
	
	private List<MetalSummaryDto> ltMetalDetailDto;
	
	private SqlStatementResponse resultSet;
	
	private static final Logger logger = LoggerFactory.getLogger(MetalService.class);
	
	
	@Before
	public void setUp(){
		
		allMetals = new ArrayList<>();
		
		for(int i = 0; i < 3; i++){
			MetalDto metal = new MetalDto();
			metal.setMetal("Metal Value: " + i);
			metal.setCoins(new ArrayList<>());
			
			for(int j = 0; j < 1; j++){
				CoinDto coin = new CoinDto();
				coin.setCoinName("Coin Name: " + j);
				coin.setCoins(new ArrayList<>());
				
				for(int k = 0; k <1; k ++){
					MetalDetailDto metalDetail = new MetalDetailDto();
					
					metalDetail.setBuyPrice(new Double(100));
					metalDetail.setCoin("Coin: " + k);
					metalDetail.setMetalKey("Metal Key: " + k);
					metalDetail.setPesosMetal("Pesos Metal: " + k);
					metalDetail.setSalePrice(new Double(100));
					
					coin.getCoins().add(metalDetail);									
				}
				
				metal.getCoins().add(coin);
			}
			
			allMetals.add(metal);
		}
		
		
		ltMetalDetailDto = new ArrayList<>();
		
		for(int i = 0; i < 3; i ++){
			MetalSummaryDto metalSummary = new MetalSummaryDto();
			
			metalSummary.setBuyPrice(new Double(100));
			metalSummary.setCoin("Coin: " + i);
			metalSummary.setMetalKey("Metal Key: " + i);
			metalSummary.setPesosMetal("Pesos Metal: " + i);
			metalSummary.setSalePrice(new Double(100));
			
			ltMetalDetailDto.add(metalSummary);
		}
		
		resultSet = new SqlStatementResponse();
		resultSet.setResultSet(new ArrayList<>());
		
		Object[] objResultSet = new Object[5];
		objResultSet[0] = new String("metal key");
		objResultSet[1] = new String("coin");
		objResultSet[2] = new String("pesos metal");
		objResultSet[3] = new Integer(100);
		objResultSet[4] = new Integer(1000);
		
		resultSet.getResultSet().add(objResultSet);
		
	}
	
	@Test
	public void testMetalFormater() throws Exception{
		
		MetalResponse response = 
				metalFormatter.metalResponseFormatter(allMetals);
  
		
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(response);
		
		logger.info(strMapper);		
		
	}
	
	@Test
	public void testMetalSummaryFormatter() throws Exception{
		
		MetalSummaryResponse response = 
				metalSummaryFormatter.metalResponseFormatter(ltMetalDetailDto);
		
		
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(response);
		
		logger.info(strMapper);		
	}
	
	
	@Test
	public void testMetalSummaryService() throws Exception{
		
		Mockito.when(databaseMsClient.query(Mockito.any())).thenReturn(resultSet);
		
		List<MetalSummaryDto> ltMetal = 
				metalSummaryService.fetchMetalsByKey(Util.getSummaryKeyMetal());
		
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(ltMetal);
		
		logger.info(strMapper);		
	}
	
	@Test
	public void testMetalService() throws Exception{
		Mockito.when(databaseMsClient.query(Mockito.any())).thenReturn(resultSet);
		
		List<MetalDto> ltMetal = 
				metalService.fetchMetalsByKeys(Util.getSummNameMetal(), Util.getSummKeyMetal());
		
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(ltMetal);
		
		logger.info(strMapper);		
		
	}
	
}
