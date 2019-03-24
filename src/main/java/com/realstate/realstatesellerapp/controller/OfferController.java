package com.realstate.realstatesellerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realstate.realstatesellerapp.model.OfferModel;
import com.realstate.realstatesellerapp.service.OfferService;

@RestController
@RequestMapping(value="/api/v1")
public class OfferController {
	
	private OfferService offerService;
	
	public OfferController(@Autowired OfferService offerService) {
		this.offerService = offerService;
	}
	
	@PostMapping("/offer")
	public ResponseEntity<OfferModel> createOffer(@RequestBody OfferModel offerModel){
		OfferModel createdOffer = this.offerService.create(offerModel);
		return new ResponseEntity<OfferModel>(createdOffer, HttpStatus.CREATED);
	}
	
	@GetMapping("/offer/{id}")
	public ResponseEntity<OfferModel> getById(@PathVariable Long id){
		return new ResponseEntity<OfferModel>(offerService.findById(id), HttpStatus.OK);
	}

}
