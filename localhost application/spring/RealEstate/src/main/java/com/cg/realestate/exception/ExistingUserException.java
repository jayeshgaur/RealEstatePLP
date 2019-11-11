package com.cg.realestate.exception;
/*
 * Author: Jayesh Gaur
 * Description: 	Custom exception class for validation duplicate credentials during registration
 * Created on: 		November 9, 2019
 */
public class ExistingUserException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistingUserException() {
		
	}
	
	public ExistingUserException(String string) {
		super(string);
	}

}
