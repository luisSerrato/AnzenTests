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

import com.citibanamex.mafcs.commoditycatalog.formatter.CurrencyFormatter;
import com.citibanamex.mafcs.commoditycatalog.service.CurrencyContinentService;
import com.citibanamex.mafcs.commoditycatalog.service.CurrencyService;
import com.citibanamex.mafcs.commoditycatalog.service.CurrencySummaryService;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.Continent;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.ContinentCurrencyResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencydetail.CurrencyDetail;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencydetail.CurrencyResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary.CurrencySummaryResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = com.citibanamex.mafcs.commoditycatalog.controller.v1.Currency.class)
@ContextConfiguration(classes = MockServletContext.class)
public class CurrencyControllerTest {

	@InjectMocks
	private com.citibanamex.mafcs.commoditycatalog.controller.v1.Currency currency;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CurrencyFormatter currencyFormatter;
	
	@MockBean
	private CurrencyService currencyService;
	
	@MockBean
	private CurrencySummaryService currencySummaryService;
	
	@MockBean
	private CurrencyContinentService currencyContinentService;
	
	private CurrencyResponse currencyResponse;
	
	private CurrencySummaryResponse currencySummaryResponse;
	
	private ContinentCurrencyResponse continentCurrencyResponse;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(currency).build();
		MockitoAnnotations.initMocks(this);	
		
		//Currency
		List<CurrencyDetail> currencyDetailList = new ArrayList<>();
		
		CurrencyDetail currencyDetail = new CurrencyDetail();
		currencyDetail.setcurrencyCode("ARS");
		currencyDetail.setcountryName("Argentina");
		currencyDetail.setcountryNameSpanish("Argentina");
		currencyDetail.setcurrencyDescription("Peso");
		currencyDetail.setcurrencyDescriptionSpanish("Peso");
		currencyDetail.setsalePrice(1.768753573);
		
		currencyDetailList.add(currencyDetail);
		
		currencyResponse = new CurrencyResponse();
		currencyResponse.setCurrencyDetail(currencyDetailList);
		
		//Summary
		List<com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary.Currency> currencySummaryList = new ArrayList<>(); 
		com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary.Currency currency = new com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary.Currency();
		currency.setcurrencyCode("ARS");
		currency.setcountryNameSpanish("Argentina");
		currency.setcountryName("Argentina");
		currency.setcurrencyDescriptionSpanish("Peso");
		currency.setcurrencyDescription("Peso");
		currency.setsalePrice(1.768753573);
		currency.setbuyPrice(1.768753573);
		
		currencySummaryList.add(currency);
		
		currencySummaryResponse = new CurrencySummaryResponse();
		currencySummaryResponse.setCurrency(currencySummaryList);
		
		//Continent
		List<Continent> continentList = new ArrayList<>();
		
		Continent continent = new Continent();
		
		String continentName = "Asia";
		
		List<com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.Currency> currencyContinentList = new ArrayList<>();
		
		com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.Currency currencyContinent = new com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.Currency();
		
		currencyContinent.setcurrencyCode("ARS");
		currencyContinent.setcountryFlagImage("argentina.png");
		currencyContinent.setcountryNameSpanish("Argentina");
		currencyContinent.setcountryName("Argentina");
		currencyContinent.setcurrencyDescriptionSpanish("Peso");
		currencyContinent.setcurrencyDescription("Peso");
		currencyContinent.setsalePrice(1.768753573);
		currencyContinent.setbuyPrice(1.768753573);
		
		continent.setcontinentName(continentName);
		continent.setCurrency(currencyContinentList);
		
		continentList.add(continent);
		
		continentCurrencyResponse = new ContinentCurrencyResponse();
		continentCurrencyResponse.setContinent(continentList);
	}
	
	@Test
	public void currencyTest() throws Exception {
		Mockito.when(currencyFormatter.currencyResponse(Mockito.any())).thenReturn(currencyResponse);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/private/v1/consumer-services/catalogs/currencies"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void currencySummaryTest() throws Exception {
		Mockito.when(currencyFormatter.currencySummaryResponse(Mockito.any())).thenReturn(currencySummaryResponse);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/private/v1/consumer-services/catalogs/currencies/summary"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void currencyContinentTest() throws Exception {
		Mockito.when(currencyFormatter.currencyContinentFormatter(Mockito.any())).thenReturn(continentCurrencyResponse);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/private/v1/consumer-services/catalogs/currencies/continent"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@After
	public void cleanUp() {
		
	}
}
