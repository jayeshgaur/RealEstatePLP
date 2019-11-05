package com.cg.realestate.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ems_estate")
public class Estate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "estate_id")
	private BigInteger estateId;
	
	@Column(name = "estate_name")
	private String estateName;
	
	@Embedded
	@Column(name = "estate_address")
	private Address estateAddress;
	
	@Column(name = "estate_area")
	private BigInteger estateArea;
	
	@Column(name = "estate_price")
	private BigInteger estatePrice;
		
	public Estate() {
		
	}

	public Estate(BigInteger estateId, String estateName, Address estateAddress, BigInteger estateArea,
			BigInteger estatePrice) {
		super();
		this.estateId = estateId;
		this.estateName = estateName;
		this.estateAddress = estateAddress;
		this.estateArea = estateArea;
		this.estatePrice = estatePrice;
	}

	public BigInteger getEstateId() {
		return estateId;
	}

	public void setEstateId(BigInteger estateId) {
		this.estateId = estateId;
	}

	public String getEstateName() {
		return estateName;
	}

	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}

	public Address getEstateAddress() {
		return estateAddress;
	}

	public void setEstateAddress(Address estateAddress) {
		this.estateAddress = estateAddress;
	}

	public BigInteger getEstateArea() {
		return estateArea;
	}

	public void setEstateArea(BigInteger estateArea) {
		this.estateArea = estateArea;
	}

	public BigInteger getEstatePrice() {
		return estatePrice;
	}

	public void setEstatePrice(BigInteger estatePrice) {
		this.estatePrice = estatePrice;
	}
	

}
