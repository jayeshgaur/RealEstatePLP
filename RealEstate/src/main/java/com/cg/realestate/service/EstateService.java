package com.cg.realestate.service;

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
}
