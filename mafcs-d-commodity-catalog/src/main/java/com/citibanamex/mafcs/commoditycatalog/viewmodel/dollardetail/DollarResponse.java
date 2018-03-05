package com.citibanamex.mafcs.commoditycatalog.viewmodel.dollardetail;

import java.util.List;

/**
 * @author vs75119
 * @version 1.0
 * @created 14-Dec-2017 11:07:19 AM
 */
public class DollarResponse {

	private List<ForeignExchange> foreignExchange;

	public DollarResponse(){
		//This is a constructor
	}

	public List<ForeignExchange> getForeignExchange() {
		return foreignExchange;
	}

	public void setForeignExchange(List<ForeignExchange> foreignExchange) {
		this.foreignExchange = foreignExchange;
	}
	
}