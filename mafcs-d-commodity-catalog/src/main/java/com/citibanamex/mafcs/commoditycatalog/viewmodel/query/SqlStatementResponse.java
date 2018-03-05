package com.citibanamex.mafcs.commoditycatalog.viewmodel.query;

import java.util.List;

/**
 * @author sh47704
 * @version 1.0
 * @created 06-Dec-2017 7:34:41 PM
 */
public class SqlStatementResponse {
	
	private List<Object[]> resultSet;

	public SqlStatementResponse() {
		//This is a constructor
	}

	public List<Object[]> getResultSet() {
		return resultSet;
	}

	public void setResultSet(List<Object[]> resultSet) {
		this.resultSet = resultSet;
	}

}