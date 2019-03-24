package com.realstate.realstatesellerapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.realstate.realstatesellerapp.enums.ImmobileType;
import com.realstate.realstatesellerapp.enums.OfferStatus;
import com.realstate.realstatesellerapp.exception.ClientNotFoundException;
import com.realstate.realstatesellerapp.exception.ImmobileNotFoundException;
import com.realstate.realstatesellerapp.exception.OfferNotFoundException;
import com.realstate.realstatesellerapp.model.ClientModel;
import com.realstate.realstatesellerapp.model.Immobile;
import com.realstate.realstatesellerapp.model.OfferModel;
import com.realstate.realstatesellerapp.repository.OfferRepository;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {
	
	@Autowired
	private OfferService offerService;
	
	@Mock
	private OfferRepository offerRepository;
	
	@Mock
	private ClientService clientService;
	
	@Mock
	private ImmobileService immobileService;
	
	@Before
	public void before() {
		this.offerService = new OfferServiceImpl(this.offerRepository, this.clientService, this.immobileService);
	}
	
	@Test
	public void shouldFindOfferByClientId() {
		final Long clientId = 10L;
		final ClientModel clientModel = new ClientModel();
		clientModel.setBornDate(LocalDate.of(1982, 9, 15));
		clientModel.setEmail("teste@teste.com.br");
		clientModel.setName("Ricardo");
		clientModel.setWage(11000L);
		clientModel.setId(clientId);
		
		when(this.clientService.findById(clientId)).then(answer -> {
			return clientModel;
		});
		
		when(offerRepository.findByClient(clientModel)).then(ansert -> {
			Immobile immobile = new Immobile();
			immobile.setAddress("22th avenue, 1800");
			immobile.setPicture("picture.png");
			immobile.setType(ImmobileType.APARTMENT);
			immobile.setValue(1500000L);
			
			ClientModel newClient = new ClientModel();
			newClient.setBornDate(LocalDate.of(1982, 9, 15));
			newClient.setEmail("teste@teste.com.br");
			newClient.setName("Ricardo");
			newClient.setWage(11000L);
			
			OfferModel offerModel = new OfferModel();
			offerModel.setClient(newClient);
			offerModel.setImmobile(immobile);
			offerModel.setOfferStatus(OfferStatus.SCHEDULE_VISIT);
			return offerModel;
		});
		List<OfferModel> offerModel = this.offerService.findByClientId(clientId);
		assertThat(offerModel).isNotNull();
	}
	
	@Test(expected=ClientNotFoundException.class)
	public void shouldThrowClientNotFoundException() {
		final Long clientId = 10L;
		final ClientModel clientModel = new ClientModel();
		clientModel.setBornDate(LocalDate.of(1982, 9, 15));
		clientModel.setEmail("teste@teste.com.br");
		clientModel.setName("Ricardo");
		clientModel.setWage(11000L);
		clientModel.setId(clientId);
		
		when(this.clientService.findById(clientId)).then(answer -> {
			throw new ClientNotFoundException(String.format("Client not found with id %d", clientId));
		});	

		this.offerService.findByClientId(clientId);		
	}
	
	@Test
	public void shouldFindOfferByClientEmail() {
		final String email = "test@teste.com.br";
		final ClientModel clientModel = new ClientModel();
		clientModel.setBornDate(LocalDate.of(1982, 9, 15));
		clientModel.setEmail(email);
		clientModel.setName("Ricardo");
		clientModel.setWage(11000L);
		clientModel.setId(10L);
		
		when(this.clientService.findByEmail(email)).then(answer -> {
			return clientModel;
		});
		
		when(offerRepository.findByClient(clientModel)).then(ansert -> {
			Immobile immobile = new Immobile();
			immobile.setAddress("22th avenue, 1800");
			immobile.setPicture("picture.png");
			immobile.setType(ImmobileType.APARTMENT);
			immobile.setValue(1500000L);
			
			ClientModel newClient = new ClientModel();
			newClient.setBornDate(LocalDate.of(1982, 9, 15));
			newClient.setEmail("teste@teste.com.br");
			newClient.setName("Ricardo");
			newClient.setWage(11000L);
			
			OfferModel offerModel = new OfferModel();
			offerModel.setClient(newClient);
			offerModel.setImmobile(immobile);
			offerModel.setOfferStatus(OfferStatus.SCHEDULE_VISIT);
			return offerModel;
		});
		List<OfferModel> offerModel = this.offerService.findByEmail(email);
		assertThat(offerModel).isNotNull();		
	}
	
	@Test(expected=ClientNotFoundException.class)
	public void shouldThrowClientNotFoundException_whenEmailNotExist() {
		final String email = "test@teste.com.br";
		final ClientModel clientModel = new ClientModel();
		clientModel.setBornDate(LocalDate.of(1982, 9, 15));
		clientModel.setEmail(email);
		clientModel.setName("Ricardo");
		clientModel.setWage(11000L);
		clientModel.setId(10L);
		
		when(this.clientService.findByEmail(email)).then(answer -> {
			throw new ClientNotFoundException(String.format("Client not found with email %s", email));
		});
		
		this.offerService.findByEmail(email);
	}
	
	@Test
	public void shouldFindOfferById() {
		final Long offerId = 10L;
		when(this.offerRepository.findById(offerId)).then(answer -> {
			Immobile immobile = new Immobile();
			immobile.setAddress("22th avenue, 1800");
			immobile.setPicture("picture.png");
			immobile.setType(ImmobileType.APARTMENT);
			immobile.setValue(1500000L);
			
			ClientModel newClient = new ClientModel();
			newClient.setBornDate(LocalDate.of(1982, 9, 15));
			newClient.setEmail("teste@teste.com.br");
			newClient.setName("Ricardo");
			newClient.setWage(11000L);
			
			OfferModel offerModel = new OfferModel();
			offerModel.setClient(newClient);
			offerModel.setImmobile(immobile);
			offerModel.setOfferStatus(OfferStatus.SCHEDULE_VISIT);
			return Optional.of(offerModel);			
		});
		OfferModel foundOffer = this.offerService.findById(offerId);
		assertThat(foundOffer).isNotNull();
	}
	
	@Test(expected=OfferNotFoundException.class)
	public void shouldThrowOfferNotFoundException_whenIdNotExist() {
		final Long offerId = 10L;
		when(this.offerRepository.findById(offerId)).then(answer -> {
			OfferModel offerModel = null;
			return Optional.ofNullable(offerModel);			
		});
		this.offerService.findById(offerId);
	}
	
	@Test
	public void shouldCreateNewOffer() {
		final Long clientId = 10l;
		final Long immobileId = 11l;
		
		final ClientModel clientModel = new ClientModel();
		clientModel.setBornDate(LocalDate.of(1982, 9, 15));
		clientModel.setEmail("teste@teste.com.br");
		clientModel.setName("Ricardo");
		clientModel.setWage(11000L);
		clientModel.setId(clientId);
		
		final Immobile foundImmobile = new Immobile();
		foundImmobile.setAddress("22th avenue, 1800");
		foundImmobile.setPicture("picture.png");
		foundImmobile.setType(ImmobileType.APARTMENT);
		foundImmobile.setValue(1500000L);
		foundImmobile.setId(immobileId);
		
		final Immobile immobile = new Immobile();
		immobile.setId(immobileId);
		
		final ClientModel newClient = new ClientModel();
		newClient.setId(clientId);
		
		final OfferModel offerModel = new OfferModel();
		offerModel.setClient(newClient);
		offerModel.setImmobile(immobile);
		
		when(this.clientService.findById(clientId)).then(answer -> {
			return clientModel;
		});
		
		when(this.immobileService.findById(immobileId)).then(answer -> {
			return foundImmobile;
		});
		
		when(this.offerRepository.save(offerModel)).then(answer -> {
			OfferModel newOffer = new OfferModel();
			newOffer.setId(10L);
			newOffer.setClient(clientModel);
			newOffer.setImmobile(foundImmobile);
			newOffer.setOfferStatus(OfferStatus.SCHEDULE_VISIT);			
			return newOffer;
		});
		
		OfferModel createdOffer = this.offerService.create(offerModel);
		assertThat(createdOffer).isNotNull();
	}
	
	@Test(expected=ClientNotFoundException.class)
	public void shouldThrowClientNotFoundException_whenClientIdNotExist() {
		final Long clientId = 10l;
		final Long immobileId = 11l;
		
		final ClientModel clientModel = new ClientModel();
		clientModel.setBornDate(LocalDate.of(1982, 9, 15));
		clientModel.setEmail("teste@teste.com.br");
		clientModel.setName("Ricardo");
		clientModel.setWage(11000L);
		clientModel.setId(clientId);
		
		final Immobile foundImmobile = new Immobile();
		foundImmobile.setAddress("22th avenue, 1800");
		foundImmobile.setPicture("picture.png");
		foundImmobile.setType(ImmobileType.APARTMENT);
		foundImmobile.setValue(1500000L);
		foundImmobile.setId(immobileId);
		
		final Immobile immobile = new Immobile();
		immobile.setId(immobileId);
		
		final ClientModel newClient = new ClientModel();
		newClient.setId(clientId);
		
		final OfferModel offerModel = new OfferModel();
		offerModel.setClient(newClient);
		offerModel.setImmobile(immobile);
		
		when(this.clientService.findById(clientId)).then(answer -> {
			throw new ClientNotFoundException(String.format("Client not found with id %d", clientId));
		});
		
		this.offerService.create(offerModel);
	}
	
	@Test(expected=ImmobileNotFoundException.class)
	public void shouldThrowImmobileNotFoundException_whenImmobileIdNotExist() {
		final Long clientId = 10l;
		final Long immobileId = 11l;
		
		final ClientModel clientModel = new ClientModel();
		clientModel.setBornDate(LocalDate.of(1982, 9, 15));
		clientModel.setEmail("teste@teste.com.br");
		clientModel.setName("Ricardo");
		clientModel.setWage(11000L);
		clientModel.setId(clientId);
		
		final Immobile foundImmobile = new Immobile();
		foundImmobile.setAddress("22th avenue, 1800");
		foundImmobile.setPicture("picture.png");
		foundImmobile.setType(ImmobileType.APARTMENT);
		foundImmobile.setValue(1500000L);
		foundImmobile.setId(immobileId);
		
		final Immobile immobile = new Immobile();
		immobile.setId(immobileId);
		
		final ClientModel newClient = new ClientModel();
		newClient.setId(clientId);
		
		final OfferModel offerModel = new OfferModel();
		offerModel.setClient(newClient);
		offerModel.setImmobile(immobile);
		
		when(this.clientService.findById(clientId)).then(answer -> {
			return clientModel;
		});
		
		when(this.immobileService.findById(immobileId)).then(answer -> {
			throw new ImmobileNotFoundException(String.format("Immobilier not found with id %d", immobileId));
		});
		
		this.offerService.create(offerModel);
	}

}
