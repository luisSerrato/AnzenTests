package com.citibanamex.mafcs.commoditycatalog.model;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class SqlStatementRequest {

	@NotEmpty
	private String sql;
	
	@Valid
	private Object[] params;

	public String getSql() {
		return sql;
	}

	public void setSql(String sqlValue) {
		sql = sqlValue;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] paramsValue) {
		params = paramsValue;
	}

}
