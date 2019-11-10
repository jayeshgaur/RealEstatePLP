package com.cg.realestate.dto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ems_user")
@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_generator",  initialValue = 4700)
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_generator")
	
	@Column(name = "user_id")
	private BigInteger userId;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_role")
	private String userRole;
	
	@Column(name="user_contact")
	private String userContact;
	
	@Column(name="user_birthdate")
	private LocalDate birthDate;
	
	public User(String userEmail, String userName, String userPassword, String userContact, LocalDate birthDate) {
		super();
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userContact = userContact;
		this.birthDate = birthDate;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "estateOwner")
	private List<Estate> ownedProperties = new ArrayList<Estate>();
	
	@JsonIgnore
	@OneToMany 
	private List<Estate> interestedList = new ArrayList<Estate>();
	
	@JsonIgnore
	@OneToOne
	private Estate offerEstate;
	
	@CreatedBy
	protected String createdBy;
	
	@CreatedDate	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	
	@LastModifiedBy
	protected String lastModifiedBy;
	
	@LastModifiedDate
	protected String lastModifiedDate;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User() {

	}
	
	public User(String userEmail, String userName, String userPassword, String userRole, String userContact) {
		super();
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userContact = userContact;
	}


	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<Estate> getOwnedProperties() {
		return ownedProperties;
	}

	public void setOwnedProperties(List<Estate> ownedProperties) {
		this.ownedProperties = ownedProperties;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Estate> getInterestedList() {
		return interestedList;
	}

	public void setInterestedList(List<Estate> interestedList) {
		this.interestedList = interestedList;
	}

	public Estate getOfferEstate() {
		return offerEstate;
	}

	public void setOfferEstate(Estate offerEstate) {
		this.offerEstate = offerEstate;
	}

	
}
