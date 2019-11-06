package com.cg.realestate.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.realestate.dto.Estate;

@Repository
public interface EstateRepository extends JpaRepository<Estate, BigInteger>{

}
