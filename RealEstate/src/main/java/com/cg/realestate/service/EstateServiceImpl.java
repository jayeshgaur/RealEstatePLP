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
	
	
	/*
	 * Description: Service method to add a single estate without any owner
	 * Created on: 	November 9, 2019
	 * Input: 		Estate object to be added
	 * Output: 		Persisted estate object
	 */
	@Override
	public Estate addEstate(Estate estate) {
		logger.info("Persisting the estate object received..");
		estateRepository.save(estate);
		return estate;
	}
	
	/*
	 * Description: Service method to add a single estate with the owner
	 * Created on: 	November 9, 2019
	 * Input: 		Estate object to be added, user ID corresponding to the owner
	 * Output: 		Persisted estate object
	 */
	@Override
	public Estate addEstate(Estate estate, BigInteger userId) {
		logger.info("Getting the user object who is adding the estate");
		User user = userRepository.findById(userId).get();
		logger.info("Adding the user as owner of the new estate");
		estate.setEstateOwner(user);
		logger.info("Persisting the estate object received..");
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
		logger.info("Finding user by email Id");
		Optional<User> user =  userRepository.findByUserEmail(userEmail);
		if(user.isPresent()) {
			logger.info("User found.. returning user object");
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
		logger.info("Getting user role of the user corresponding to the email id");
		Optional<User> user = userRepository.findByUserEmail(userEmail);
		if(user.isPresent()) {
			logger.info("User object found.. returning user role");
			return user.get().getUserRole();
		}
		else {
			return "ROLE_Customer";
		}
	}
	
	/*
	 * Description:  Persists the images into the database and associates it with database.
	 * Created on: November 7, 2019
	 * Input: 		Multipart image file
	 * Output: 		Image object corresponding to the file
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
	
	/*
	 * Description: 	Get image object corresponding to the file ID specified
	 * Created on: 		November 9, 2019
	 * Input: 			image Id
	 * Output: 			image object
	 */
	public Images getFile(BigInteger fileId) {
		logger.info("returning image file");
        return imagesRepository.findById(fileId).get();
    }
	
	/*
	 * Description: 	Returns a list of all estates in the system
	 * Created on: 		November 9, 2019
	 * Input: 			Void
	 * Output:			List of estates
	 */
	public List<Estate> getListOfEstates(){
		logger.info("Returning all estates");
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
		logger.info("finding estate object corresponding to the estate id");
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
		logger.info("finding user object corresponding to the user id");
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			logger.info("user found... returning user object");
			return user.get();
		}
		logger.error("invalid user id");
		return null;
	}
	
	
	/*
	 * Description: 	Add the estate who's brochure user has downloaded into his interest list
	 * Created on: 		November 10, 2019
	 * Input: 			Estate Id, User Id
	 * Output: 			User object
	 */
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
			logger.info("Adding the estate into user's interested list");
			interestedList.add(estate);
		}
		return user;
	}

	/*
	 * Description: 	Returns a list of users in the system
	 * Created on: 		November 9, 2019
	 * Input: 			void
	 * Output: 			List of all users in the system
	 */
	@Override
	public List<User> getInterestedUsers() {
		logger.info("Returning all users");
		List<User> userList = userRepository.findAll();		
		return userList;
	}
	
	/*
	 * Description: 	Updates the estate which is on offer for a particular user
	 * Created on: 		November 10, 2019
	 * Input: 			User id, estate id
	 * Output: 			status of the operation
	 */
	@Override
	public boolean changeOfferEstate(BigInteger userId, BigInteger estateId) {
	List<Estate> estateList = getEstate(estateId);
	Estate estate = estateList.get(0);
	User user = findUser(userId);
	logger.info("Updating the offer estate for the user.");
	user.setOfferEstate(estate);
	return true;
	}


}
