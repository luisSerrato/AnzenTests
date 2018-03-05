package com.citibanamex.mafcs.commoditycatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;

@Configuration
public class BackendSystemsConnConfig {

	 @Bean
	  public Request.Options options() {
	    Request.Options options = new Request.Options(
	        30000, 30000
	    );
	    return options;
	  }
}
