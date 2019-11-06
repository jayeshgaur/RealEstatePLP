package com.cg.realestate.service;

import java.math.BigInteger;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cg.realestate.dto.Estate;
import com.cg.realestate.dto.Images;
import com.cg.realestate.dto.User;
import com.cg.realestate.exception.ErrorMessages;
import com.cg.realestate.exception.ValidationException;
import com.cg.realestate.repository.EstateRepository;
import com.cg.realestate.repository.ImagesRepository;
import com.cg.realestate.repository.UserRepository;

@Service
@Transactional
public class EstateServiceImpl implements EstateService {

	@Autowired
	EstateRepository estateRepository;
		
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ImagesRepository imagesRepository;
	
	@Override
	public Estate addEstate(Estate estate) {
		estateRepository.save(estate);
		return estate;
	}

	/*
	 * Author: Jayesh Gaur 
	 * Description: Returns the user object corresponding to the
	 * user email received in input 
	 * Created on: October 9, 2019
	 */
	public User findUser(String userEmail) throws ValidationException {
		Optional<User> user =  userRepository.findByUserEmail(userEmail);
		if(user.isPresent()) {
			return user.get();
		}
		throw new ValidationException(ErrorMessages.userErrorInvalidEmailId);
	}
	
	/*
	 * 
	 */
	public Images storeFile(MultipartFile file) throws ValidationException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
				throw new ValidationException("Could not store file"+fileName+". Please try again");
			}
			Images image = new Images(fileName, file.getContentType(), file.getBytes());
			return imagesRepository.save(image);
		}catch (Exception exception) {
			throw new ValidationException("Could not store file"+fileName+". Please try again");
		}
	}
	
	public Images getFile(BigInteger fileId) {
        return imagesRepository.findById(fileId).get();
    }
}
