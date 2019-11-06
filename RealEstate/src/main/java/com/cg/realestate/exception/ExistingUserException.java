package com.cg.realestate.exception;

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
