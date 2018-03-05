package com.citibanamex.mafcs.commoditycatalog.formatter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.citibanamex.mafcs.commoditycatalog.model.SqlStatementRequest;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlParameter;

@Service
public class SqlStatementRequestFormatter {

	public com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest sqlStatementRequestFormatter(
			SqlStatementRequest sqlStatement
			) {
		
		List<SqlParameter> params = new ArrayList<>();
		com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest statementRequest = new com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest();
		statementRequest.setSql(sqlStatement.getSql());
		
		if(sqlStatement.getParams()!=null) {
			
			for(int i=0; i < sqlStatement.getParams().length; i++) {
			
				SqlParameter parameter = new SqlParameter();
				parameter.setParameter(String.valueOf(sqlStatement.getParams()[i]));
				params.add(parameter);
				
			}
			
			statementRequest.setSqlParameter(params);
		}
		
		return statementRequest;
	}
	
}
