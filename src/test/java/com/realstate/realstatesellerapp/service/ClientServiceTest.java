package com.realstate.realstatesellerapp.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.realstate.realstatesellerapp.exception.ClientNotFoundException;
import com.realstate.realstatesellerapp.model.ClientModel;
import com.realstate.realstatesellerapp.repository.ClientRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
	
	@Mock
	private ClientRepository clientRepository;
	
	private ClientService clientService;
	
	@Before
	public void before() {
		this.clientService = new ClientServiceImpl(this.clientRepository);
	}
	
	@Test
	public void shouldFindClientById() {
		final Long clientId = 10L;
		when(this.clientRepository.findById(clientId)).then((answer) -> {
			final ClientModel newClient = new ClientModel();
			newClient.setBornDate(LocalDate.of(1982, 9, 15));
			newClient.setEmail("teste@teste.com.br");
			newClient.setName("Ricardo");
			newClient.setWage(11000L);
			newClient.setId(10L);
			Optional<ClientModel> optClientModel = Optional.of(newClient); 
			return optClientModel;
		});
		ClientModel foundClient = this.clientService.findById(clientId);
		assertThat(foundClient).isNotNull();
	}
	
	@Test(expected=ClientNotFoundException.class)
	public void shouldThrowClientNotFoundException() {
		final Long clientId = 10L;
		when(this.clientRepository.findById(clientId)).then((answer) -> {
			throw new ClientNotFoundException(String.format("Client not found with id %d", clientId));
		});
		this.clientService.findById(clientId);		
	}
	
	@Test(expected=ClientNotFoundException.class)
	public void shouldDeleteClientById() {
		final Long clientId = 10L;
		when(this.clientRepository.findById(clientId)).then((answer) -> {
			final ClientModel newClient = new ClientModel();
			newClient.setBornDate(LocalDate.of(1982, 9, 15));
			newClient.setEmail("teste@teste.com.br");
			newClient.setName("Ricardo");
			newClient.setWage(11000L);
			newClient.setId(10L);
			Optional<ClientModel> optClientModel = Optional.of(newClient); 
			return optClientModel;
		});
		this.clientService.deleteById(clientId);
		
		when(this.clientRepository.findById(clientId)).then((answer) -> {
			final ClientModel newClient = null;
			return Optional.ofNullable(newClient);
		});
		this.clientService.findById(clientId);
	}
	
	@Test
	public void shouldUpdateClient() {
		final String newEmail = "ricardo@teste.com.br";
		final Long clientId = 10L;
		final ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail("teste@teste.com.br");
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		newClient.setId(clientId);
		
		when(this.clientRepository.findById(clientId)).then((answer) -> {
			Optional<ClientModel> optClientModel = Optional.of(newClient); 
			return optClientModel;
		});
		
		newClient.setEmail(newEmail);
		ClientModel updatedClient = this.clientService.update(newClient);
		assertThat(updatedClient).isNotNull();
		assertThat(updatedClient.getEmail().equals(newEmail)).isTrue();
	}
	
	@Test
	public void shouldAddNewClient() {
		final String email = "teste@teste.com.br";
		final ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail(email);
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		
		when(this.clientRepository.findByEmail(email)).then(answer -> {
			return null;
		});
		
		when(this.clientRepository.save(newClient)).then(answer -> {
			newClient.setId(10L);
			return newClient;
		});
		
		ClientModel createdClient = this.clientService.create(newClient);
		assertThat(createdClient).isNotNull();
	}

}
