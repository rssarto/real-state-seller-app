package com.realstate.realstatesellerapp.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.realstate.realstatesellerapp.enums.ImmobileType;
import com.realstate.realstatesellerapp.model.ClientModel;
import com.realstate.realstatesellerapp.model.Immobile;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ImmobileRepository immobileRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void checkInstances() {
		assertThat(clientRepository).isNotNull();
		assertThat(testEntityManager).isNotNull();
		assertThat(immobileRepository).isNotNull();
	}
	
	@Test
	public void shouldfindClientById() {
		ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail("teste@teste.com.br");
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		ClientModel persistedClient = testEntityManager.persistAndFlush(newClient);
		Optional<ClientModel> optFoundClient = this.clientRepository.findById(persistedClient.getId());
		assertThat(optFoundClient.isPresent()).isTrue();
	}
	
	@Test
	public void shouldUpdateClient() {
		final String newEmail = "ricardo@teste.com.br";
		final Long newWage = 14000L;
		
		Immobile immobile = new Immobile();
		immobile.setAddress("22th avenue, 1800");
		immobile.setPicture("picture.png");
		immobile.setType(ImmobileType.APARTMENT);
		immobile.setValue(1500000L);
		Immobile savedImmobile = testEntityManager.persistAndFlush(immobile);
		assertThat(savedImmobile).isNotNull();
		assertThat(savedImmobile.getId()).isNotNull();
		assertThat(savedImmobile.getId() > 0L).isTrue();		
		
		ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail("teste@teste.com.br");
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		ClientModel persistedClient = testEntityManager.persistAndFlush(newClient);

		Optional<ClientModel> optFoundClient = this.clientRepository.findById(persistedClient.getId());
		assertThat(optFoundClient.isPresent()).isTrue();
		ClientModel foundClient = optFoundClient.get();
		foundClient.setEmail(newEmail);
		foundClient.setWage(newWage);
		
		List<Immobile> immobileList = new ArrayList<>();
		immobileList.add(savedImmobile);
		foundClient.setFavorites(immobileList);
		
		this.clientRepository.save(foundClient);
		
		Optional<ClientModel> optUpdatedClient = this.clientRepository.findById(foundClient.getId());
		assertThat(optUpdatedClient.isPresent()).isTrue();
		ClientModel updatedClient = optUpdatedClient.get();
		assertThat(updatedClient.getEmail().equals(newEmail)).isTrue();
		assertThat(updatedClient.getWage().longValue() == newWage.longValue()).isTrue();
	}
	
	@Test
	public void shouldDeleteClientById() {
		ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail("teste@teste.com.br");
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		ClientModel persistedClient = testEntityManager.persistAndFlush(newClient);
		Optional<ClientModel> optFoundClient = this.clientRepository.findById(persistedClient.getId());
		assertThat(optFoundClient.isPresent()).isTrue();
		
		ClientModel savedClient = optFoundClient.get();
		final Long clientId = savedClient.getId();
		this.clientRepository.deleteById(clientId);
		
		Optional<ClientModel> optDeletedClient = this.clientRepository.findById(clientId);
		assertThat(optDeletedClient.isPresent()).isFalse();
		
	}
	
	@Test
	public void shouldFindClientByEmail() {
		ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail("teste@teste.com.br");
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		testEntityManager.persistAndFlush(newClient);
		
		ClientModel clientFound = this.clientRepository.findByEmail(newClient.getEmail());
		assertThat(clientFound).isNotNull();
		assertThat(clientFound.getEmail().equals(newClient.getEmail())).isTrue();
	}

}
