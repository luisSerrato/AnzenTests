package com.citibanamex.mafcs.commoditycatalog.viewmodel.query;

import java.util.List;


/**
 * @author sh47704
 * @version 1.0
 * @created 06-Dec-2017 7:34:41 PM
 */
public class SqlStatementRequest {

	private String sql;
	private List<SqlParameter> sqlParameter;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<SqlParameter> getSqlParameter() {
		return sqlParameter;
	}

	public void setSqlParameter(List<SqlParameter> sqlParameter) {
		this.sqlParameter = sqlParameter;
	}

	public SqlStatementRequest(){
		//This is a constructor
	}

}