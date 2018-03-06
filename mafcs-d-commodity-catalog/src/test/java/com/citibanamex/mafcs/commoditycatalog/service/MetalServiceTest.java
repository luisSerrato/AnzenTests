package com.citibanamex.mafcs.commoditycatalog.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citibanamex.mafcs.commoditycatalog.formatter.MetalFormatter;
import com.citibanamex.mafcs.commoditycatalog.formatter.MetalSummaryFormatter;
import com.citibanamex.mafcs.commoditycatalog.model.CoinDto;
import com.citibanamex.mafcs.commoditycatalog.model.MetalDetailDto;
import com.citibanamex.mafcs.commoditycatalog.model.MetalDto;
import com.citibanamex.mafcs.commoditycatalog.model.MetalSummaryDto;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalSummaryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
public class MetalServiceTest {

	@Configuration
	static class MetalServiceTestConfigurarion{
		
		@Bean
		public MetalFormatter metalFormatter(){
			return new MetalFormatter();
		}
		
		
		@Bean
		public MetalSummaryFormatter metalSummaryFormatter(){
			return new MetalSummaryFormatter();
		}
	}
	
	
	@Autowired
	private MetalFormatter metalFormatter;
	
	@Autowired 
	private MetalSummaryFormatter metalSummaryFormatter;
	
	private List<MetalDto> allMetals;
	
	private List<MetalSummaryDto> ltMetalDetailDto;
	
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
	
	
	
	
}
