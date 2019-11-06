package com.cg.realestate.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.realestate.dto.User;
import com.cg.realestate.dto.UserDetailsImpl;
import com.cg.realestate.exception.ErrorMessages;
import com.cg.realestate.exception.ExistingUserException;
import com.cg.realestate.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder brcyptEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserEmail(userEmail);
		if(user == null) {
			throw new UsernameNotFoundException("User not found with email: " + userEmail);
		}
		return user.map(UserDetailsImpl::new).get();
	}
	
	public User save(User user) throws ExistingUserException {
//		User newUser = new User(brcyptEncoder.encode(user.getUserPassword()), user.getUserName(), user.getContactNo(), user.getUserEmail(), user.getAge(), user.getGender());
//		return userRepository.save(newUser);
		
		logger.info("Checking if the email is already registered..");
		// Validating unique database columns
		Optional<User> checkUserCredentials = userRepository.findByUserEmail(user.getUserEmail());
		if (checkUserCredentials.isPresent()) {
			logger.error("An existing account with this email found... throwing ExistingCredentialException");
			throw new ExistingUserException(ErrorMessages.userErrorDuplicateEmail);
		} else {
			checkUserCredentials = null;
			logger.info("Email is unique. Checking if the phone number is already registered..");
			checkUserCredentials = userRepository.findByUserContact(user.getUserContact());
			if (checkUserCredentials.isPresent()) {
				logger.error("An existing account with this contact found... throwing ExistingCredentialException");
				throw new ExistingUserException(ErrorMessages.userErrorDuplicatePhoneNumber);
			}
		}
		// save if email and phone numbers are unique
		logger.info("Phone number is also unique. Registering the user..");
		user.setUserPassword(brcyptEncoder.encode(user.getUserPassword()));
		user.setUserRole("ROLE_Customer");
		return userRepository.save(user);
		
	}
	
}