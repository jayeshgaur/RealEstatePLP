package com.cg.realestate.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.realestate.dto.Estate;
import com.cg.realestate.dto.User;
import com.cg.realestate.exception.ErrorMessages;
import com.cg.realestate.exception.ValidationException;
import com.cg.realestate.repository.EstateRepository;
import com.cg.realestate.repository.UserRepository;

@Service
@Transactional
public class EstateServiceImpl implements EstateService {

	@Autowired
	EstateRepository estateRepository;
		
	@Autowired
	UserRepository userRepository;
	
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

}
