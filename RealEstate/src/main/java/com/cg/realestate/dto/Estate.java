package com.cg.realestate.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ems_estate")
@SequenceGenerator(name = "estate_id_generator", sequenceName = "estate_id_generator",  initialValue = 6100)
@EntityListeners(AuditingEntityListener.class)
public class Estate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "estate_id_generator")
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
	
	@ManyToOne
	@JoinColumn(name = "fk_owner")
	private User estateOwner;
	
	@OneToMany
	@JoinColumn(name="fk_estatez")
	private List<Images> imageList = new ArrayList<Images>();
	
	@CreatedBy
	protected String createdBy;
	
	@CreatedDate	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	
	@LastModifiedBy
	protected String lastModifiedBy;
	
	@LastModifiedDate
	protected String lastModifiedDate;
		
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

	public User getEstateOwner() {
		return estateOwner;
	}

	public void setEstateOwner(User estateOwner) {
		this.estateOwner = estateOwner;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	

}
