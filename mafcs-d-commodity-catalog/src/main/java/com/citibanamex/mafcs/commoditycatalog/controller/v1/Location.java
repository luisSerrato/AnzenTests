package com.citibanamex.mafcs.commoditycatalog.controller.v1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citibanamex.mafcs.commoditycatalog.formatter.LocationFormatter;
import com.citibanamex.mafcs.commoditycatalog.service.LocationService;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.countrydetail.RetrieveCountryDetailResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author hg48153
 * @version 1.0
 * @created 14-Dec-2017 11:07:19 AM
 */
@RestController
@Api(tags = "Location")
@RequestMapping("/api/private/v1")
public class Location {

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private LocationFormatter locationFormatter;
	
	public Location(){
		// This is a constructor
	}

	/**
	 * Description = This API will be used to fetch the country details of the
	 * customer.
	 * HTTP_Method = GET
	 * Request_URI=/private/v1/consumer-services/catalogs/country
	 */
	@ApiOperation(value = "location", produces="application/json")
	@ApiResponses(value={
			@ApiResponse(code = 200, message="Ok", response = RetrieveCountryDetailResponse.class, responseContainer="Object"),
			@ApiResponse(code = 400, message="Bad request"),
			@ApiResponse(code = 500, message="Internal server error")
	})
	@RequestMapping(value="/consumer-services/catalogs/country", method = RequestMethod.GET)
	public RetrieveCountryDetailResponse retrieveCountry(
			@RequestHeader (name = "client-id", required = false) String clientId,
			@RequestHeader (name = "Authorization", required = false) String authorization,
			@RequestHeader (name = "uuid", required = false) String uuid){		
		return locationFormatter.locationResponseFormatter(locationService.getCountriesInfo());
	}

}