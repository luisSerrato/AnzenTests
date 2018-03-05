package com.citibanamex.mafcs.commoditycatalog.databasemsclient;

import org.springframework.stereotype.Component;

import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementResponse;

@Component
public class DatabaseMsClientFallback implements DatabaseMsClient {

	@Override
	public SqlStatementResponse query(SqlStatementRequest sqlStatementRequest) {
		return null;
	}

}
