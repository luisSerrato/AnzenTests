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

import com.citibanamex.mafcs.commoditycatalog.formatter.DollarFormatter;
import com.citibanamex.mafcs.commoditycatalog.service.DollarService;
import com.citibanamex.mafcs.commoditycatalog.service.DollarSummaryService;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollardetail.DollarResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollardetail.ForeignExchange;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollarsummary.Currency;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollarsummary.DollarSummaryResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = com.citibanamex.mafcs.commoditycatalog.controller.v1.Dollar.class)
@ContextConfiguration(classes = MockServletContext.class)
public class DollarControllerTest {

	@InjectMocks
	private com.citibanamex.mafcs.commoditycatalog.controller.v1.Dollar dollarController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DollarService dollarService; 
	
	@MockBean
	private DollarSummaryService dollarSummaryService;
	
	@MockBean
	private DollarFormatter dollarFormatter;
	
	private DollarResponse dollarResponse;
	
	private DollarSummaryResponse dollarSummaryResponse;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(dollarController).build();
		MockitoAnnotations.initMocks(this);
		
		//Dollar
		List<ForeignExchange> foreignExchangeList = new ArrayList<>();
		ForeignExchange foreignExchange = new ForeignExchange();
		foreignExchange.setforeignExchangeRate(19.3564);
		foreignExchange.setmonth(06);
		foreignExchange.setyear(2017);
		
		ForeignExchange foreignExchange2 = new ForeignExchange();
		foreignExchange2.setforeignExchangeRate(17.5334);
		foreignExchange2.setmonth(01);
		foreignExchange2.setyear(2017);
		
		foreignExchangeList.add(foreignExchange);
		foreignExchangeList.add(foreignExchange2);
		
		dollarResponse = new DollarResponse();
		dollarResponse.setForeignExchange(foreignExchangeList);
		
		//DollarSummary
		List<Currency> currencyList = new ArrayList<>();
		Currency currency = new Currency();
		currency.setcurrencyCode("HOY");
		currency.setcountryFlagImage("USA.GIF");
		currency.setcountryNameSpanish("EUA");
		currency.setcountryName("USA");
		currency.setcurrencyDescriptionSpanish("Dllr.Int.Hoy");
		currency.setcurrencyDescription("Today");
		currency.setbuyPrice(12.12);
		currency.setsalePrice(15.234);
		
		currencyList.add(currency);
		
		dollarSummaryResponse = new DollarSummaryResponse();
		dollarSummaryResponse.setCurrency(currencyList);
	}
	
	@Test
	public void dollarTest() throws Exception {
		int yearParamService=2017;
		Mockito.when(dollarFormatter.dollarFormatter(Mockito.any())).thenReturn(dollarResponse);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/private/v1/consumer-services/catalogs/dollar/{year}",yearParamService))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void dollarSummaryTest() throws Exception {
		Mockito.when(dollarFormatter.dollarSummaryFormatter(Mockito.any())).thenReturn(dollarSummaryResponse);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/private/v1/consumer-services/catalogs/dollar/summary"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	@After
	public void cleanUp() {
		
	}
	
}