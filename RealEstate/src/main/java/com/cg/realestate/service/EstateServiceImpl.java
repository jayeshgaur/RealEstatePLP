package com.cg.realestate.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.realestate.dto.Estate;
import com.cg.realestate.repository.EstateRepository;

@Service
@Transactional
public class EstateServiceImpl implements EstateService {

	@Autowired
	EstateRepository estateRepository;
	
	@Override
	public Estate addEstate(Estate estate) {
		estateRepository.save(estate);
		return estate;
	}

}
