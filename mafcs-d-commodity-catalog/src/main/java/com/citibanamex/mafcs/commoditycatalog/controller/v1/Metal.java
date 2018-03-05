package com.citibanamex.mafcs.commoditycatalog.controller.v1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citibanamex.mafcs.commoditycatalog.formatter.MetalFormatter;
import com.citibanamex.mafcs.commoditycatalog.formatter.MetalSummaryFormatter;
import com.citibanamex.mafcs.commoditycatalog.service.MetalService;
import com.citibanamex.mafcs.commoditycatalog.service.MetalSummaryService;
import com.citibanamex.mafcs.commoditycatalog.util.Util;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalResponse;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalSummaryResponse;

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
@Api(tags = "Metals")
@RequestMapping("/api/private/v1")
public class Metal {
	
	@Autowired
	private MetalFormatter metalFormatter;
	
	@Autowired
	private MetalService metalService;
	
	@Autowired
	private MetalSummaryService metalSummaryService;
	
	@Autowired
	private MetalSummaryFormatter metalSummaryFormatter;

	public Metal(){
		// This is a constructor
	}

	/**
	 * Description=This API is to fetch metal and its coin summary information
	 * HTTP_Method=GET
	 * URI=/private/v1/consumer-services/catalogs/metals/summary
	 */
	@ApiOperation(value = "metalsSummary", produces="application/json")
	@ApiResponses(value={
			@ApiResponse(code = 200, message="Ok", response = MetalSummaryResponse.class, responseContainer="Object"),
			@ApiResponse(code = 400, message="Bad request"),
			@ApiResponse(code = 500, message="Internal server error")
	})
	@RequestMapping(value="/consumer-services/catalogs/metals/summary", method = RequestMethod.GET)
	public MetalSummaryResponse metalSummary(
			@RequestHeader (name = "client-id", required = false) String clientId,
			@RequestHeader (name = "Authorization", required = false) String authorization,
			@RequestHeader (name = "uuid", required = false) String uuid){
		return metalSummaryFormatter.metalResponseFormatter(
				metalSummaryService.fetchMetalsByKey(Util.getSummaryKeyMetal()));		
	}

	/**
	 * Description=This API is to get metal and its coin information
	 * HTTP_Method=GET
	 * URI=/private/v1/consumer-services/catalogs/metals
	 */
	@ApiOperation(value = "metals", produces="application/json")
	@ApiResponses(value={
			@ApiResponse(code = 200, message="Ok", response = MetalResponse.class, responseContainer="Object"),
			@ApiResponse(code = 400, message="Bad request"),
			@ApiResponse(code = 500, message="Internal server error")
	})
	@RequestMapping(value="/consumer-services/catalogs/metals", method = RequestMethod.GET)
	public MetalResponse retrieveMetal(
			@RequestHeader (name = "client-id", required = false) String clientId,
			@RequestHeader (name = "Authorization", required = false) String authorization,
			@RequestHeader (name = "uuid", required = false) String uuid){		
		return metalFormatter.metalResponseFormatter(
				metalService.fetchMetalsByKeys(Util.getSummNameMetal(), Util.getSummKeyMetal()));		
	}

}