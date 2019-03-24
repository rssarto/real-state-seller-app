package com.realstate.realstatesellerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realstate.realstatesellerapp.model.Immobile;
import com.realstate.realstatesellerapp.service.ImmobileService;

@RestController
@RequestMapping(value="/api/v1")
public class ImmobileController {
	
	private ImmobileService immobileService;
	
	public ImmobileController(@Autowired ImmobileService immobileService) {
		this.immobileService = immobileService;
	}
	
	@GetMapping("immobilie")
	public ResponseEntity<List<Immobile>> getAll(){
		List<Immobile> immobilieList = this.immobileService.getAll();
		return new ResponseEntity<List<Immobile>>(immobilieList, HttpStatus.OK);
	}

}
