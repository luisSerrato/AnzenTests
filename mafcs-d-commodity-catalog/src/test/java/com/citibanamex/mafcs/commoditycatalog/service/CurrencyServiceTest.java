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
import com.citibanamex.mafcs.commoditycatalog.service.impl.CurrencyContinentServiceImpl;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.ContinentCurrencyResponse;
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

	@MockBean
	private DatabaseMsClient databaseMsClient;

	private SqlStatementResponse resultSet;

	@Autowired
	private CurrencyFormatter currencyFormatter;

	private List<Object[]> lstObj;

	private List<CurrencyContDto> currencies;
	private List<CurrencyContinentDto> lstCurrencyContinent;

	@Before
	public void setUp() {

		resultSet = new SqlStatementResponse();
		resultSet.setResultSet(null);
		lstObj = new ArrayList<>();

		Object[] objAl = new Object[8];
		objAl[0] = "currencyKey";
		objAl[1] = "countryFlag";
		objAl[2] = "countryNameSp";
		objAl[3] = "countryNameEn";
		objAl[4] = "currencyDescSp";
		objAl[5] = "currencyDescEn";
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

	}

	@Test
	public void testCurrencyContinentService() throws Exception {

		List<CurrencyContinentDto> lstCurrencyContinent = currencyContinentService
				.fetchCurrencyContinentByKey(Util.getContKeyCurrency());
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(lstCurrencyContinent);

		logger.info("Response " + strMapper);
	}

	@Test
	public void testCurrencyContinentFormatter() throws Exception {

		ContinentCurrencyResponse response = currencyFormatter.currencyContinentFormatter(lstCurrencyContinent);
		ObjectMapper mapper = new ObjectMapper();
		String strMapper = mapper.writeValueAsString(response);

		logger.info("Response formatter Continent"+ strMapper);

	}

}
