package com.realstate.realstatesellerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realstate.realstatesellerapp.model.ClientModel;
import com.realstate.realstatesellerapp.service.ClientService;

@RestController
@RequestMapping(value="/api/v1")
public class ClientController {
	
	private ClientService clientService;
	
	public ClientController(@Autowired ClientService clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<ClientModel> getByid(@PathVariable Long id){
		ClientModel findById = this.clientService.findById(id);
		return new ResponseEntity<ClientModel>(findById, HttpStatus.OK);
	}
	
}
