package com.citibanamex.mafcs.commoditycatalog.databasemsclient;

import javax.validation.Valid;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;

@FunctionalInterface
@FeignClient(name = "mafmo-d-treasurydatabase", url = "${feign.database.url}", fallback = DatabaseMsClientFallback.class)
public interface DatabaseMsClient {

	@RequestMapping(value = "/api/v1/crud/market-operations/ef/query", method = RequestMethod.POST)
	public SqlStatementResponse query(@RequestBody @Valid SqlStatementRequest sqlStatementRequest);

}