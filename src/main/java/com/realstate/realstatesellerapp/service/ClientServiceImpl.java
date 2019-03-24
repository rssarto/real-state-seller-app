package com.realstate.realstatesellerapp.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realstate.realstatesellerapp.exception.ClientBadRequestException;
import com.realstate.realstatesellerapp.exception.ClientNotFoundException;
import com.realstate.realstatesellerapp.model.ClientModel;
import com.realstate.realstatesellerapp.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	private ClientRepository clientRepository;
	
	public ClientServiceImpl(@Autowired ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public ClientModel findById(Long clientId) {
		Optional<ClientModel> optClient = this.clientRepository.findById(clientId);
		if( !optClient.isPresent() ) {
			throw new ClientNotFoundException(String.format("Client not found with id %d", clientId));
		}
		return optClient.get();
	}

	@Override
	public void deleteById(Long clientId) {
		ClientModel foundClient = this.findById(clientId);
		this.clientRepository.deleteById(foundClient.getId());
	}

	@Override
	public ClientModel update(ClientModel client) {
		boolean isUpdate = false;
		ClientModel foundClient = this.findById(client.getId());
		if( Objects.nonNull(client.getBornDate()) ){
			foundClient.setBornDate(client.getBornDate());
			isUpdate = true;
		}
		
		if( Objects.nonNull(client.getEmail()) ) {
			foundClient.setEmail(client.getEmail());
			isUpdate = true;
		}
		
		if( Objects.nonNull(client.getFavorites()) && !client.getFavorites().isEmpty() ) {
			foundClient.setFavorites(client.getFavorites());
			isUpdate = true;
		}
		
		if( Objects.nonNull(client.getName()) && !client.getName().trim().equals("")) {
			foundClient.setName(client.getName());
			isUpdate = true;
		}
		
		if( Objects.nonNull(client.getWage()) && client.getWage() > 0L ) {
			foundClient.setWage(client.getWage());
			isUpdate = true;
		}
		
		if( isUpdate ) {
			this.clientRepository.save(foundClient);
		}
		return foundClient;
	}

	@Override
	public ClientModel create(ClientModel newClient) {
		if( Objects.nonNull(this.clientRepository.findByEmail(newClient.getEmail()))  ) {
			throw new ClientBadRequestException(String.format("E-mail %s already exist", newClient.getEmail()));
		}
		
		return this.clientRepository.save(newClient);
	}

	@Override
	public ClientModel findByEmail(String email) {
		ClientModel foundClient = this.clientRepository.findByEmail(email);
		if( Objects.isNull(foundClient) ) {
			throw new ClientNotFoundException(String.format("Client not found with e-mail %s", email));
		}
		return foundClient;
	}
	
	
	
	
}
