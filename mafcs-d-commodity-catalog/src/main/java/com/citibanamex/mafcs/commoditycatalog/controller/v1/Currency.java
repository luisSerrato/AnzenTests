package com.citibanamex.mafcs.commoditycatalog.controller.v1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citibanamex.mafcs.commoditycatalog.formatter.CurrencyFormatter;
import com.citibanamex.mafcs.commoditycatalog.service.CurrencyContinentService;
import com.citibanamex.mafcs.commoditycatalog.service.CurrencyService;
import com.citibanamex.mafcs.commoditycatalog.service.CurrencySummaryService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencycontinent.ContinentCurrencyResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencydetail.CurrencyResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary.CurrencySummaryResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:18 AM
 */
@RestController
@RequestMapping("api")
@Api(tags = "Currencies")
public class Currency {
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private CurrencySummaryService currencySummaryService;
	
	@Autowired
	private CurrencyContinentService currencyContinentService;
	
	@Autowired
	private CurrencyFormatter currencyFormatter;
	
	public Currency(){
		//This is a constructor
	}

	/**
	 * Description=This API is used to retreive all  currencies by continent
	 * information
	 * HTTP_Method=GET
	 * URI=/private/v1/consumer-services/catalogs/currencies/continent
	 */
	@RequestMapping(value = "/private/v1/consumer-services/catalogs/currencies/continent", method = RequestMethod.GET)
	@ApiOperation(value = "Currencies Continent", produces="application/json")
	@ApiResponses(value={
			@ApiResponse(code = 200, message="Ok", response = ContinentCurrencyResponse.class, responseContainer="Object"),
			@ApiResponse(code = 400, message="Bad request"),
			@ApiResponse(code = 500, message="Internal server error")
	})
	public ContinentCurrencyResponse currencyContinent(
			@RequestHeader (name = "client-id", required = false) String clientId,
			@RequestHeader (name = "Authorization", required = false) String authorization,
			@RequestHeader (name = "uuid", required = false) String uuid){
		return currencyFormatter.currencyContinentFormatter(currencyContinentService.fetchCurrencyContinentByKey(Util.getContKeyCurrency()));
	}

	/**
	 * Description=This API is used to retreive currency summary information
	 * HTTP_Method=GET
	 * URI=/private/v1/consumer-services/catalogs/currencies/summary
	 */
	@RequestMapping(value = "/private/v1/consumer-services/catalogs/currencies/summary", method = RequestMethod.GET)
	@ApiOperation(value = "Currencies Summary", produces="application/json")
	@ApiResponses(value={
			@ApiResponse(code = 200, message="Ok", response = CurrencySummaryResponse.class, responseContainer="Object"),
			@ApiResponse(code = 400, message="Bad request"),
			@ApiResponse(code = 500, message="Internal server error")
	})
	public CurrencySummaryResponse currencySummary(
			@RequestHeader (name = "client-id", required = false) String clientId,
			@RequestHeader (name = "Authorization", required = false) String authorization,
			@RequestHeader (name = "uuid", required = false) String uuid){
		return currencyFormatter.currencySummaryResponse(currencySummaryService.fetchCurrencyByKey(Util.getSummKeyCurrency()));
	}

	/**
	 * Description=This API is used to retreive all currencies information
	 * HTTP_Method=GET
	 * URI=/private/v1/consumer-services/catalogs/currencies
	 */
	@RequestMapping(value = "/private/v1/consumer-services/catalogs/currencies", method = RequestMethod.GET)
	@ApiOperation(value = "Currencies", produces="application/json")
	@ApiResponses(value={
			@ApiResponse(code = 200, message="Ok", response = CurrencyResponse.class, responseContainer="Object"),
			@ApiResponse(code = 400, message="Bad request"),
			@ApiResponse(code = 500, message="Internal server error")
	})
	public CurrencyResponse retrieveCurrency(			
			@RequestHeader (name = "client-id", required = false) String clientId,
			@RequestHeader (name = "Authorization", required = false) String authorization,
			@RequestHeader (name = "uuid", required = false) String uuid){
		
		return currencyFormatter.currencyResponse(currencyService.fetchCurrencyByNotKey(Util.getCalcKeyCurrency()));
	}

}