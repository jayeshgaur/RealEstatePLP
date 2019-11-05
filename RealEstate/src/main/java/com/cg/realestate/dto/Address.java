package com.cg.realestate.dto;

import java.math.BigInteger;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "")
public class Address {
	private String addressLine;
	private String city;
	private String state;
	private BigInteger pincode;

	public Address() {

	}

	public Address(String addressLine, String city, String state, BigInteger pincode) {
		super();
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public String getAddressLine() {
		return this.addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigInteger getPincode() {
		return this.pincode;
	}

	public void setPincode(BigInteger pincode) {
		this.pincode = pincode;
	}

}
