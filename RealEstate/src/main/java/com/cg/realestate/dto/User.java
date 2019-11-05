package com.cg.realestate.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ems_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public User() {

	}

	public User(BigInteger userId, String userEmail, String userName, String userPassword, String userRole) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
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

	
}
