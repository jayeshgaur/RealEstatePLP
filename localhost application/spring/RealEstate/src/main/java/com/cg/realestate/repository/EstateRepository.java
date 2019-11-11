package com.cg.realestate.repository;
/*
 * Author: 	Jayesh Gaur
 * Description: 	Repository for class Estate
 * Created on: 		November 6, 2019
 */
import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.realestate.dto.Estate;

@Repository
public interface EstateRepository extends JpaRepository<Estate, BigInteger>{

}
