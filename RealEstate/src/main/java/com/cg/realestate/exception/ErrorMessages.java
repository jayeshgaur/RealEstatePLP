package com.cg.realestate.exception;
/*
 * Author:  Jayesh Gaur
 * Description: 	All final string variables of error messages to avoid sonar cube error
 * Created on: 		November 8, 2019
 */
public class ErrorMessages {
	public static final String userErrorSecret = "Enter valid password.\\n Password should must contain one number,one capital letter,one small letter and one special character";
	public static final String userErrorUserName = "Enter a valid name.\n Name should start with a capital letter";
	public static final String userErrorStringContactNo = "Phone number can only have numbers. Please enter a 10 digit numeric phone number!\nExample: 1234567890";
	public static final String userErrorContactNoLength = "Contact number should have 10 digits\nExample: 1234567890";
	public static final String userErrorEmailId = "Enter valid email id.\n Example: sampleemail@gmail.com";
	public static final String userErrorUserAge = "You are not eligible to use our system!";
	public static final String userErrorUserGender = "Select from the given options only. Your choice can only be M or O or F.";
	public static final String userErrorInvalidCenterId = "Enter a proper center id. Refer the center list above.";
	public static final String userErrorInvalidTestId = "Enter correct test id. Refer the test list above.";
	public static final String userErrorInvalidAppointmentId = "Enter correct appointment id! Refer the appointment list above.";
	public static final String userErrorAddTestFailed = "Failed to add test";
	public static final String userErrorInvalidUserId = "Invalid User Id. Your user id was displayed to you after you completed your registration";
	public static final String userErrorInvalidDateFormat = "Please enter the date in the specified format";
	public static final String userErrorPastDate = "You cannot book an appointment today or in the past!";
	public static final String userErrorInvalidTimeFormat = "Please enter the time in the specified format\n Example: 12-12-2019 12:12:12";
	public static final String userErrorNonWorkingHours = "Our working hours are only from 10am to 8pm.";
	public static final String userErrorNoCenterAdded="Add center failed";
	public static final String userErrorNoCenterDeleted="Delete center failed";
	public static final String userErrorNoTestAdded="Add test failed";
	public static final String userErrorNoTestDeleted="Delete test failed";
	public static final String userErrorDuplicateEmail = "This email is already registered in the system";
	public static final String userErrorDuplicatePhoneNumber = "This phone number is already linked with one of our accounts";
	public static final String userErrorInvalidEmailId="Wrong email Id";
}
