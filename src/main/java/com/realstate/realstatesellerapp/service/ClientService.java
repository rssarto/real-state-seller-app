package com.realstate.realstatesellerapp.service;

import com.realstate.realstatesellerapp.model.ClientModel;

public interface ClientService {

	ClientModel findById(Long clientId);
	
	void deleteById(Long clientId);
	
	ClientModel update(ClientModel client);

	ClientModel create(ClientModel newClient);

	ClientModel findByEmail(String email);
}
