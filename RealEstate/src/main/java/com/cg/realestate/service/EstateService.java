package com.cg.realestate.service;

import com.cg.realestate.dto.Estate;
import com.cg.realestate.dto.User;
import com.cg.realestate.exception.ValidationException;

public interface EstateService {
	Estate addEstate(Estate estate);
	User findUser(String userEmail) throws ValidationException;

}
