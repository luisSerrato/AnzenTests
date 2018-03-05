package com.citibanamex.mafcs.commoditycatalog.controller.v1;

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

import com.citibanamex.mafcs.commoditycatalog.formatter.LocationFormatter;
import com.citibanamex.mafcs.commoditycatalog.service.LocationService;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.countrydetail.Country;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.countrydetail.RetrieveCountryDetailResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = Location.class)
@ContextConfiguration(classes = MockServletContext.class)
public class LocationControllerTest {

	@InjectMocks
	private Location location;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LocationFormatter locationFormatter;
	
	@MockBean
	private LocationService locationService;
	
	private RetrieveCountryDetailResponse countryResponse;
	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(location).build();
		MockitoAnnotations.initMocks(this);	
		
		countryResponse = new RetrieveCountryDetailResponse();
		Country country = new Country();
		country.setcountryName("Mexico");
		countryResponse.getCountry().add(country);
	}
	
	
	@Test
	public void testRetrieveCountryDetailResponse() throws Exception{
		Mockito.when(locationFormatter.locationResponseFormatter(Mockito.any())).thenReturn(countryResponse);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/private/v1/consumer-services/catalogs/country"))
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		
	}
	
	
	@After
	public void cleanUp() {
		
	}
	

}
