package com.citibanamex.mafcs.commoditycatalog.errorhandling.exception;

public class DatabaseMsClientException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7883283395096400811L;

	public DatabaseMsClientException() {
	}

	public DatabaseMsClientException(String message) {
		super(message);
	}

	public DatabaseMsClientException(Throwable throwable) {
		super(throwable);
	}

}
