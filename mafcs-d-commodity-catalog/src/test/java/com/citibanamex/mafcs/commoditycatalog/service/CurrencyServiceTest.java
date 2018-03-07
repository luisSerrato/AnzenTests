package com.citibanamex.mafcs.commoditycatalog.service;

import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
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
import com.citibanamex.mafcs.commoditycatalog.formatter.CurrencyFormatter;
import com.citibanamex.mafcs.commoditycatalog.formatter.SqlStatementRequestFormatter;
import com.citibanamex.mafcs.commoditycatalog.model.CurrencyContDto;
import com.citibanamex.mafcs.commoditycatalog.model.CurrencyContinentDto;
import com.citibanamex.mafcs.commoditycatalog.model.CurrencyDto;
import com.citibanamex.mafcs.commoditycatalog.service.impl.CurrencyContinentServiceImpl;
import com.citibanamex.mafcs.commoditycatalog.service.impl.CurrencyServiceImpl;
import com.citibanamex.mafcs.commoditycatalog.service.impl.CurrencySummaryServiceImpl;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.ContinentCurrencyResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencydetail.CurrencyResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary.CurrencySummaryResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CurrencyServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceTest.class);

	@Configuration
	static class CurrencyServiceTestConfiguration {

		@Bean
		public CurrencyContinentService currencyContinentService() {
			return new CurrencyContinentServiceImpl();
		}

		@Bean
		public CurrencySummaryService currencySummaryService() {
			return new CurrencySummaryServiceImpl();
		}

		@Bean
		CurrencyService currencyService() {
			return new CurrencyServiceImpl();
		}

		@Bean
		public SqlStatementRequestFormatter sqlStatementRequestFormatter() {
			return new SqlStatementRequestFormatter();
		}

		@Bean
		public CurrencyFormatter currencyFormatter() {
			return new CurrencyFormatter();
		}

	}

	@Autowired
	private CurrencyContinentService currencyContinentService;

	@Autowired
	private CurrencySummaryService currencySummaryService;

	@Autowired
	private CurrencyService currencyService;

	@MockBean
	private DatabaseMsClient databaseMsClient;

	private SqlStatementResponse resultSet;

	private SqlStatementResponse resultSetCurrency;

	@Autowired
	private CurrencyFormatter currencyFormatter;

	private List<Object[]> lstObj;

	private List<CurrencyContDto> currencies;
	private List<CurrencyContinentDto> lstCurrencyContinent;

	private List<CurrencyDto> lstCurrencyDto;

	@Before
	public void setUp() {

		// CurrencyContinent

		resultSet = new SqlStatementResponse();
		resultSet.setResultSet(null);
		lstObj = new ArrayList<>();

		Object[] objAl = new Object[8];
		objAl[0] = "1";
		objAl[1] = "argentina.png";
		objAl[2] = "Argentina";
		objAl[3] = "Argentina";
		objAl[4] = "Peso";
		objAl[5] = "Peso";
		objAl[6] = 6;
		objAl[7] = 7;

		lstObj.add(objAl);

		resultSet.setResultSet(lstObj);

		Mockito.when(databaseMsClient.query(Mockito.any())).thenReturn(resultSet);

		// List<CurrencyContinentDto> Formatter

		CurrencyContDto currencyContDto = new CurrencyContDto();

		currencyContDto.setBuyPrice(1.768753573);
		currencyContDto.setCountryFlag("argentina.png");
		currencyContDto.setCountryNameEn("Argentina");
		currencyContDto.setCountryNameSp("Argentina");
		currencyContDto.setCurrencyDescEn("Peso");
		currencyContDto.setCurrencyDescSp("Peso");
		currencyContDto.setCurrencyKey("1");
		currencyContDto.setSalePrice(1.768753573);

		currencies = new ArrayList<>();
		currencies.add(currencyContDto);

		CurrencyContinentDto currencyContinentDto = new CurrencyContinentDto();

		currencyContinentDto.setContinent("America");
		currencyContinentDto.setCurrencies(currencies);

		lstCurrencyContinent = new ArrayList<>();
		lstCurrencyContinent.add(currencyContinentDto);

		// Currency Service

		Object[] objCurrencyService = new Object[8];
		objCurrencyService[0] = "1.768753573";
		objCurrencyService[1] = "Canada";
		objCurrencyService[2] = "Canada";
		objCurrencyService[3] = "Dollar";
		objCurrencyService[4] = "Dollar";
		objCurrencyService[5] = 1.7683;

		resultSetCurrency = new SqlStatementResponse();
		resultSetCurrency.setResultSet(null);
		lstObj = new ArrayList<>();

		lstObj.add(objCurrencyService);

		resultSetCurrency.setResultSet(lstObj);

		// CurrencyService Formatter

		CurrencyDto currencyDto = new CurrencyDto();
		currencyDto.setCurrencyKey("1.768753573");
		currencyDto.setCountryNameSp("Canada");
		currencyDto.setCountryNameEn("Canada");
		currencyDto.setCurrencyDescSp("Dollar");
		currencyDto.setCurrencyDescEn("Dollar");
		currencyDto.setSalePrice(1.7683);

		lstCurrencyDto = new ArrayList<>();
		lstCurrencyDto.add(currencyDto);

	}

	@Test
	public void testCurrencyContinentService() throws Exception {

		List<CurrencyContinentDto> lstCurrencyContinent = currencyContinentService
				.fetchCurrencyContinentByKey(Util.getContKeyCurrency());
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(lstCurrencyContinent);

		logger.info("Response testCurrencyContinentService" + strMapper);
	}

	@Test
	public void testCurrencyContinentFormatter() throws Exception {

		ContinentCurrencyResponse response = currencyFormatter.currencyContinentFormatter(lstCurrencyContinent);
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(response);

		logger.info("Response Continent formatter" + strMapper);

	}

	@Test
	public void testCurrencySummaryService() throws Exception {

		List<CurrencyContDto> lstCurrencySummary = currencySummaryService.fetchCurrencyByKey(Util.getSummKeyCurrency());
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(lstCurrencySummary);

		logger.info("Response summary " + strMapper);
	}

	@Test
	public void testCurrencySummaryFormatter() throws Exception {

		CurrencySummaryResponse response = currencyFormatter.currencySummaryResponse(currencies);
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(response);

		logger.info("Response summary formatter" + strMapper);

	}

	@Test
	public void testCurrencyService() throws Exception {

		Mockito.when(databaseMsClient.query(Mockito.any())).thenReturn(resultSetCurrency);
		List<CurrencyDto> lstCurrency = currencyService.fetchCurrencyByNotKey(Util.getCalcKeyCurrency());
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(lstCurrency);

		logger.info("Response test CurrencyService" + strMapper);
	}

	@Test
	public void testCurrencyServiceFormatter() throws Exception {

		CurrencyResponse response = currencyFormatter.currencyResponse(lstCurrencyDto);
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(response);

		logger.info("Response CurrencyService formatter" + strMapper);

	}

}
