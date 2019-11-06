package com.cg.realestate.dto;
/*
 * Author: 			Jayesh Gaur
 * Description: 	JWT UserDetails subclass for authentication/authorization purposes
 * Created on: 		November 6, 2019
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userEmail;
	private String userPassword;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl() {
	}

	public UserDetailsImpl(User user) {
		this.userEmail = user.getUserEmail();
		this.userPassword = user.getUserPassword();
		this.authorities = Arrays.stream(user.getUserRole().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return userPassword;
	}

	@Override
	public String getUsername() {
		return userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}