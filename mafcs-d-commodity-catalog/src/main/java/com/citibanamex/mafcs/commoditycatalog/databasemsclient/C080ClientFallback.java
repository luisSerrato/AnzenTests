package com.citibanamex.mafcs.commoditycatalog.databasemsclient;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest;

@Component
public class C080ClientFallback implements C080Client {
	
	public Object getInformationC080(@RequestBody @Valid SqlStatementRequest query) {
		return null;
	}

}
