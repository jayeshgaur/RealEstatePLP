package com.cg.realestate.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.realestate.dto.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, BigInteger> {

}
