package com.cg.realestate.controller;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.realestate.dto.Estate;
import com.cg.realestate.dto.Images;
import com.cg.realestate.dto.JwtRequest;
import com.cg.realestate.dto.JwtResponse;
import com.cg.realestate.dto.User;
import com.cg.realestate.exception.ExistingUserException;
import com.cg.realestate.exception.ValidationException;
import com.cg.realestate.filesystem.GeneratePdf;
import com.cg.realestate.jwtconfig.JwtTokenUtil;
import com.cg.realestate.service.EstateService;
import com.cg.realestate.service.JwtUserDetailsService;

/*
 * Author: Jayesh Gaur
 * Description: Controller class
 * Created on: November 6, 2019
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EstateController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private EstateService estateService;

	private static final Logger logger = LoggerFactory.getLogger(EstateController.class);

	/*
	 * Author: Jayesh Gaur Description: Takes care of user's role based
	 * authentication Created on: November 6, 2019 Input: Login credentials Output:
	 * JWT Token
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		logger.info("Authenticating request...");
		authenticate(authenticationRequest.getUserEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUserEmail());

		User user = estateService.findUser(authenticationRequest.getUserEmail());
		System.out.println(user);
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	/*
	 * Description: Registration function. Will validate existing user email and
	 * phone number Created on: November 6, 2019 Input: User details Output: Newly
	 * created user Object with user Id
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		// return ResponseEntity.ok(jwtUserDetailsService.save(user));
		try {
			User newUser = jwtUserDetailsService.save(user);
			logger.info("New User Created. AUDIT TRAIL=> User ID: " + newUser.getUserId() + " Created by: "
					+ newUser.getCreatedBy() + " Created on: " + newUser.getCreationDate());
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		} catch (ExistingUserException exception) {
			logger.error(exception.getMessage());
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	


	/*
	 * Description: Calls the authenticate function from AuthenticationManager class
	 * to validate user credentials Created on: November 6, 2019 Input: user
	 * credentials Output: void
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	/*
	 * Author: 			Jayesh Gaur
	 * Description: 	Returns user Id of the user associated with the useremail in parameter
	 * Created on: 		October 23, 2019
	 * Input: 			User Email
	 * Output: 			User id
	 */
	@GetMapping("/finduser")
	public ResponseEntity<?> findUser(@RequestParam("userEmail") String userEmail){
		try {
			logger.info("Fetching user object linked with user Email..");
			User user = estateService.findUser(userEmail);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		} catch (ValidationException exception) {
			logger.info("ValidationException caught in find user controller..");
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/*
	 * Author: 			Jayesh Gaur
	 * Description: 	Returns role of the user associated with the useremail in parameter
	 * Created on: 		October 23, 2019
	 * Input: 			User Email
	 * Output: 			User id
	 */
	@GetMapping("/finduserrole")
	public ResponseEntity<?> findUserRole(@RequestParam("userEmail") String userEmail){
		try {
			logger.info("Fetching user object linked with user Email..");
			User user = estateService.findUser(userEmail);
			return new ResponseEntity<String>(user.getUserRole(),HttpStatus.OK);
		} catch (ValidationException exception) {
			logger.info("ValidationException caught in find user controller..");
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * Description: Submit the new estate. Saves the property details into database
	 * which is now available for users to view in estate listings Created on:
	 * November 6, 2019 Input: Estate object details and its image files in the form
	 * of MultipartFile Output: Estate Object
	 */
	@PostMapping(value = "/addEstate")
	public ResponseEntity<?> addEstate(@ModelAttribute Estate estate, @RequestParam("files") MultipartFile[] files) {

		logger.info("Adding images one by one into database..");
		// Adding the images into database and adding them to the list of images of the estate
		estate.setImageList(Arrays.asList(files).stream().map(file -> {
			try {
				return uploadFile(file);
			} catch (ValidationException exception) {

				exception.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList()));
		logger.info("All images added into estate and the Database.CACHE..and setting foreign keys..");
		return new ResponseEntity<Estate>(estateService.addEstate(estate), HttpStatus.OK);
	}

	@GetMapping(value = "/userpage")
	public ResponseEntity<?> userPage() {
		return new ResponseEntity<String>("User page...", HttpStatus.OK);
	}

	@GetMapping(value = "/adminpage")
	public ResponseEntity<?> adminPage() {
		return new ResponseEntity<String>("Admin page...", HttpStatus.OK);
	}

	public Images uploadFile(@RequestParam("file") MultipartFile file) throws ValidationException {
		// Creating new image object for new image file and calling storeFile
		Images image = estateService.storeFile(file);
		logger.info("Saved the image successfully... getting download URL");
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(image.getImageId().toString()).toUriString();
		logger.info("Generated download URL...Saving URL into the image object:  " + fileDownloadUri);
		image.setUrl(fileDownloadUri);
		return image;
//	        return new UploadFileResponse(image.getImageName(), fileDownloadUri,
//	                file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles")
	public List<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> {
			try {
				return uploadFile(file);
			} catch (ValidationException exception) {

				exception.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<?> downloadFile(@PathVariable BigInteger fileId) {
		// Load file from database
		Images image = estateService.getFile(fileId);

		return new ResponseEntity<>(image.getData(), HttpStatus.OK);
//	        return ResponseEntity.ok()
//	                .contentType(MediaType.parseMediaType(image.getImageType()))
//	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getImageName() + "\"")
//	                .body(new ByteArrayResource(image.getData()));
	}
	
	
	/*
	 * Description: Returns a list of all properties in the system
	 * Created on:  November 10, 2019
	 * Input: 		Non
	 * Output: 		List of all properties in the system
	 */
	@GetMapping(value="/getEstates")
	public ResponseEntity<?> getEstates(){
		List<Estate> estateList = estateService.getListOfEstates();
		if(estateList != null) {
			return new ResponseEntity<List<Estate>>(estateList, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("No properties in the system", HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * Description:  Generates a pdf and sends it back as response to download
	 * Created on: 	 November 10, 2019
	 * Input: 		 Estate ID
	 * Output: 		 Brochure of the estate in the form of .PDF
	 */
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport(@RequestParam("estateId") BigInteger estateId ) {

        List<Estate> estateList = estateService.getEstate(estateId);
        
        for (Estate estate : estateList) {
        	if(estate.getEstateId() != estateId) {
        		estateList.remove(estate);
        	}
		}
        
        System.out.println(estateList.size());
        
        ByteArrayInputStream bis = GeneratePdf.citiesReport(estateList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
}
