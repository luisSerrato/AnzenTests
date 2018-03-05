package com.citibanamex.mafcs.commoditycatalog.controller.v1;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.citibanamex.mafcs.commoditycatalog.formatter.MetalFormatter;
import com.citibanamex.mafcs.commoditycatalog.formatter.MetalSummaryFormatter;
import com.citibanamex.mafcs.commoditycatalog.service.MetalService;
import com.citibanamex.mafcs.commoditycatalog.service.MetalSummaryService;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.Coin;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.CoinMetal;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.Metal;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalSummaryResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = com.citibanamex.mafcs.commoditycatalog.controller.v1.Metal.class)
@ContextConfiguration(classes = MockServletContext.class)
public class MetalControllerTest {

	@InjectMocks
	private com.citibanamex.mafcs.commoditycatalog.controller.v1.Metal metal;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MetalFormatter metalFormatter;
	
	@MockBean
	private MetalSummaryFormatter metalSummaryFormatter;
	
	@MockBean
	private MetalService metalService;

	@MockBean
	private MetalSummaryService metalSummaryService;

	private MetalResponse metalResponse;
	
	private MetalSummaryResponse metalSummaryResponse;
	
	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(metal).build();
		MockitoAnnotations.initMocks(this);	
		
		metalSummaryResponse = new MetalSummaryResponse();
		metalResponse = new MetalResponse();
		List<Metal> ltMetal = new ArrayList<>();
		
		Metal metalDto = new Metal();
		metalDto.setmetalType("gold");
		
		CoinMetal coinMetal = new CoinMetal();
		coinMetal.setcoinCategory("category");
		
		Coin coin = new Coin();
		coin.setbuyPrice(new Double(1000));
		coin.setsalePrice(new Double(1500));
		coin.setcoinDenomination("pesos");
		coin.setmetalCode("mx");
		coin.setmetalValue("value");
		
		coinMetal.getCoin().add(coin);
		metalDto.getCoinMetal().add(coinMetal);
		ltMetal.add(metalDto);
		metalResponse.setMetal(ltMetal);
		
		metalSummaryResponse.getCoin().add(coin);
		
	}
	
	@Test
	public void testRetrieveMetal() throws Exception{
		Mockito.when(metalFormatter.metalResponseFormatter(Mockito.any())).thenReturn(metalResponse);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/private/v1/consumer-services/catalogs/metals"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		
	}

	@Test
	public void testMetalSummary() throws Exception{
		Mockito.when(metalSummaryFormatter.metalResponseFormatter(Mockito.any())).thenReturn(metalSummaryResponse);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/private/v1/consumer-services/catalogs/metals/summary"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		
	}
	
	@After
	public void cleanUp() {
		
	}
	
	
}
