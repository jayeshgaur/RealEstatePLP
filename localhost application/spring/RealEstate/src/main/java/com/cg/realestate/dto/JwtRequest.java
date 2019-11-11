package com.cg.realestate.dto;

/*
 * Author: 			Jayesh Gaur
 * Description: 	DTO class for Login RequestBody for JWT Authentication
 * Created on: 		November 8, 2019
 */
import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String userEmail;
	private String password;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String userEmail, String password) {
		this.setUserEmail(userEmail);
		this.setPassword(password);
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}