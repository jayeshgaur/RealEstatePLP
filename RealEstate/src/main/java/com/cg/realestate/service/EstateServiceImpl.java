package com.cg.realestate.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
/*  Author: 		Jayesh Gaur
 *  Description:  	Service class of the program
 *  Created on: 	November 6, 2019
 */
@Service
@Transactional
public class EstateServiceImpl implements EstateService {

	@Autowired
	EstateRepository estateRepository;
		
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ImagesRepository imagesRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(EstateServiceImpl.class);
	
	
	@Override
	public Estate addEstate(Estate estate) {
		estateRepository.save(estate);
		return estate;
	}
	

	@Override
	public Estate addEstate(Estate estate, BigInteger userId) {
		User user = userRepository.findById(userId).get();
		estate.setEstateOwner(user);
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
	 * Author: JayeshGaur
	 * Description: returns user role of the user associated with the email in parameter
	 * Created on: October 23, 2019
	 * Input: User Email
	 * Output: user role
	 */
	public String findUserRole(String userEmail) {
		Optional<User> user = userRepository.findByUserEmail(userEmail);
		if(user.isPresent()) {
			return user.get().getUserRole();
		}
		else {
			return "ROLE_Customer";
		}
	}
	
	/*
	 * Description:  Persists the images into the database and associates it with database.
	 * Created on: November 7, 2019
	 */
	public Images storeFile(MultipartFile file) throws ValidationException {
		logger.info("Getting Image name..");
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
				throw new ValidationException("Could not store file"+fileName+". Please try again");
			}
			logger.info("Creating image object for "+fileName);
			Images image = new Images(fileName, file.getContentType(), file.getBytes());
			logger.info("Saving the image data into database and returning the image object..");
			return imagesRepository.save(image);
		}catch (Exception exception) {
			throw new ValidationException("Could not store file"+fileName+". Please try again");
		}
	}
	
	public Images getFile(BigInteger fileId) {
        return imagesRepository.findById(fileId).get();
    }
	
	public List<Estate> getListOfEstates(){
		return estateRepository.findAll();
	}

	/*
	 * Description: 	Returns the estate object corresponding to the estate Id inside a list 
	 * Created on: 		November 10, 2019
	 * Input: 			BigInteger estateId
	 * Output: 			List of Estates containing only 1 element
	 */
	@Override
	public List<Estate> getEstate(BigInteger estateId) {
		List<Estate> estateList = new ArrayList<Estate>();
		
		Estate estate = estateRepository.findById(estateId).get();
		estateList.add(estate);
		return estateList;
	}

	/*
	 * Description: 	Returns the user object corresponding to the user Id
	 * Created on: 		November 10, 2019
	 * Input: 			BigInteger userId
	 * Output: 			user object
	 */
	@Override
	public User findUser(BigInteger userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public User updateInterests(BigInteger estateId, BigInteger userId) {
		User user = findUser(userId);
		Optional<Estate> estateList = estateRepository.findById(estateId);
		Estate estate = estateList.get();
		List<Estate> interestedList = user.getInterestedList();
		if(interestedList != null) {
			for (Estate estateIterator : interestedList) {
				if(estateIterator.getEstateId() == estateId) {
					return user;
				}
			}
			interestedList.add(estate);
		}
		return user;
	}

	@Override
	public List<User> getInterestedUsers() {
		List<User> userList = userRepository.findAll();		
		return userList;
	}
	
	@Override
	public boolean changeOfferEstate(BigInteger userId, BigInteger estateId) {
	List<Estate> estateList = getEstate(estateId);
	Estate estate = estateList.get(0);
	User user = findUser(userId);
	user.setOfferEstate(estate);
	return true;
	}


}
