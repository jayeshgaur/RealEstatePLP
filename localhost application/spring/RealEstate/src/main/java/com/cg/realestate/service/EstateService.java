package com.cg.realestate.service;
/*
 * Author: 	Jayesh Gaur
 * Description: 	Service interface of the application
 * Created on: 		November 6, 2019
 */
import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cg.realestate.dto.Estate;
import com.cg.realestate.dto.Images;
import com.cg.realestate.dto.User;
import com.cg.realestate.exception.ValidationException;

public interface EstateService {
	Estate addEstate(Estate estate);
	User findUser(String userEmail) throws ValidationException;
	public Images storeFile(MultipartFile file) throws ValidationException;
	public Images getFile(BigInteger fileId);
	public List<Estate> getListOfEstates();
	List<Estate> getEstate(BigInteger estateId);
	User findUser(BigInteger userId);
	User updateInterests(BigInteger estateId, BigInteger userId);
	List<User> getInterestedUsers();
	boolean changeOfferEstate(BigInteger userId, BigInteger estateId);
	Estate addEstate(Estate estate, BigInteger userId);
}
