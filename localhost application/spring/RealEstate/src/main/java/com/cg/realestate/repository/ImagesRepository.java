package com.cg.realestate.repository;
/*
 * Author: 	Jayesh Gaur
 * Description: 	Repository for class Images
 * Created on: 		November 6, 2019
 */
import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.realestate.dto.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, BigInteger> {

}
