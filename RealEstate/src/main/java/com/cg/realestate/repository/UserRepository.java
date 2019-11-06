package com.cg.realestate.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.realestate.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {
	public Optional<User> findByUserEmail(String email);
	
	public Optional<User> findByUserContact(String contact);
}
