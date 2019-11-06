package com.cg.realestate.controller;

/*  Author: 		Jayesh Gaur
 *  Description:  	Service class of the program
 *  Created on: 	November 6, 2019
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.cg.realestate.dto.Estate;
import com.cg.realestate.service.EstateService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EstateController {

	@Autowired
	private EstateService estateService;

	@PostMapping(value = "/add")
	public ResponseEntity<?> addEstate(@RequestBody Estate estate) {
			return new ResponseEntity<Estate>(estateService.addEstate(estate),HttpStatus.OK);
	}
}
