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
import com.citibanamex.mafcs.commoditycatalog.formatter.SqlStatementRequestFormatter;
import com.citibanamex.mafcs.commoditycatalog.model.CurrencyContinentDto;
import com.citibanamex.mafcs.commoditycatalog.service.impl.CurrencyContinentServiceImpl;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
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

	}

	@Autowired
	private CurrencyContinentService currencyContinentService;

	@MockBean
	private DatabaseMsClient databaseMsClient;

	private SqlStatementResponse resultSet;

	@Before
	public void setUp() {

		resultSet = new SqlStatementResponse();
		resultSet.setResultSet(null);
		List<Object[]> lstObj = new ArrayList<>();

		Object[] objAl = new Object[8];
		objAl[0] = "String 0";
		objAl[1] = "String 1";
		objAl[2] = "String 2";
		objAl[3] = "String 3";
		objAl[4] = "String 4";
		objAl[5] = "String 5";
		objAl[6] = 6;
		objAl[7] = 7;

		lstObj.add(objAl);

		resultSet.setResultSet(lstObj);

		Mockito.when(databaseMsClient.query(Mockito.any())).thenReturn(resultSet);

	}

	@Test
	public void testCurrencyContinentService() throws Exception {

		List<CurrencyContinentDto> lstCurrencyContinent = currencyContinentService
				.fetchCurrencyContinentByKey(Util.getContKeyCurrency());
		ObjectMapper om = new ObjectMapper();
		String strOm = om.writeValueAsString(lstCurrencyContinent);

		logger.info("Response " + strOm);
	}

}
