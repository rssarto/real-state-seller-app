package com.realstate.realstatesellerapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realstate.realstatesellerapp.model.AccountType_Threshold;
import com.realstate.realstatesellerapp.model.ClientModel;
import com.realstate.realstatesellerapp.repository.AccountTypeThresholdRepository;

@RestController
@RequestMapping(value="/api/v1")
public class AccountTypeController {
	
//	@Autowired
//	private AccountTypeThresholdRepository accountTypeThresholdRepository;
	
//	@GetMapping("/thresholds")
//	public ResponseEntity<List<AccountType_Threshold>> listAll(){
//		Iterable<AccountType_Threshold> findAll = this.accountTypeThresholdRepository.findAll();
//		final List<AccountType_Threshold> results = new ArrayList<>();
//		findAll.forEach(action -> results.add(action));
//		return new ResponseEntity<List<AccountType_Threshold>>(results, HttpStatus.OK);
//	}

}
