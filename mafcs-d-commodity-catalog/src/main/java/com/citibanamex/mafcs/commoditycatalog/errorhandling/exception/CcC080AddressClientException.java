package com.citibanamex.mafcs.commoditycatalog.errorhandling.exception;

public class CcC080AddressClientException extends RuntimeException {

	private static final long serialVersionUID = -2783727869968231370L;

	public CcC080AddressClientException() {
		 // Do nothing because is required
	}

	public CcC080AddressClientException(String message) {
		super(message);
	}

	public CcC080AddressClientException(Throwable throwable) {
		super(throwable);
	}

}
