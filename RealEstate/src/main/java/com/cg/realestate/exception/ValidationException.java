package com.cg.realestate.exception;
/*
 * AUthor: jayesh gaur
 * Description: 	Custom Exception class
 */
public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException() {
		
	}
	
	public ValidationException(String string) {
		super(string);
	}
}
