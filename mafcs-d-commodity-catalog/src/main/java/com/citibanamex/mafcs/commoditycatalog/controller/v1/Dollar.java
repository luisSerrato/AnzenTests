package com.citibanamex.mafcs.commoditycatalog.controller.v1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citibanamex.mafcs.commoditycatalog.formatter.DollarFormatter;
import com.citibanamex.mafcs.commoditycatalog.service.DollarService;
import com.citibanamex.mafcs.commoditycatalog.service.DollarSummaryService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.currencysummary.CurrencySummaryResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollardetail.DollarResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.dollarsummary.DollarSummaryResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:19 AM
 */

@RestController
@RequestMapping(value = "api")
@Api(tags = "Dollar")
public class Dollar {
	
	@Autowired
	private DollarFormatter dollarFormatter;
	
	@Autowired
	private DollarService dollarService;
	
	@Autowired
	private DollarSummaryService dollarSummaryService;
	
	public Dollar(){
		//This is a constructor
	}

	/**
	 * Description=This API is used to retreive dollar summary information
	 * HTTP_Method=GET
	 * URI=/private/v1/consumer-services/catalogs/dollar/summary
	 */
	@RequestMapping(value = "/private/v1/consumer-services/catalogs/dollar/summary", method = RequestMethod.GET)
	@ApiOperation(value = "Dollar Summary", produces="application/json")
	@ApiResponses(value={
			@ApiResponse(code = 200, message="Ok", response = CurrencySummaryResponse.class, responseContainer="Object"),
			@ApiResponse(code = 400, message="Bad request"),
			@ApiResponse(code = 500, message="Internal server error")
	})
	public DollarSummaryResponse dollarSummary(			
			@RequestHeader (name = "client-id", required = false) String clientId,
			@RequestHeader (name = "Authorization", required = false) String authorization,
			@RequestHeader (name = "uuid", required = false) String uuid){
		return dollarFormatter.dollarSummaryFormatter(dollarSummaryService.fetchDollarsByKey(Util.getSummKeyDollar()));
	}

	/**
	 * Description=This API is used to retreive dollar information for last 8 years
	 * starting mentioned year
	 * HTTP_Method=GET
	 * URI=/private/v1/consumer-services/catalogs/dollar/{year}
	 * 
	 * @param year    Description=Year to pass to get forex rate for year-8 to year
	 * Example=
	 * Required=True
	 */
	
	@RequestMapping(value = "/private/v1/consumer-services/catalogs/dollar/{year}", method = RequestMethod.GET)
	@ApiOperation(value = "Dollar", produces="application/json")
	@ApiResponses(value={
			@ApiResponse(code = 200, message="Ok", response = CurrencySummaryResponse.class, responseContainer="Object"),
			@ApiResponse(code = 400, message="Bad request"),
			@ApiResponse(code = 500, message="Internal server error")
	})
	public DollarResponse retrieveDollar(			
			@RequestHeader (name = "client-id", required = false) String clientId,
			@RequestHeader (name = "Authorization", required = false) String authorization,
			@RequestHeader (name = "uuid", required = false) String uuid,
			@PathVariable(value = "year") int year){
		return dollarFormatter.dollarFormatter(dollarService.fetchDollarsByYear(Util.validateLenghtInt(year)));
	}

}