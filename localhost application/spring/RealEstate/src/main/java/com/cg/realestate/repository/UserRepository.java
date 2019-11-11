package com.cg.realestate.repository;
/*
 * Author: 	Jayesh Gaur
 * Description: 	Repository for class User
 * Created on: 		November 6, 2019
 */
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.realestate.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {
	public Optional<User> findByUserEmail(String email);
	
	public Optional<User> findByUserContact(String contact);

	public List<User> findUsersByOfferEstateIsNull();

	
}
