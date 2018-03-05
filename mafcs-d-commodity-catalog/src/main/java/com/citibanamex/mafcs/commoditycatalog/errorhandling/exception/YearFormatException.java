package com.citibanamex.mafcs.commoditycatalog.errorhandling.exception;

public class YearFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -973326510443550294L;

	public YearFormatException(){}
	
	public YearFormatException(String message){
		super(message);
	}
	
	public YearFormatException(Throwable e){
		super(e);
	}
	
}
