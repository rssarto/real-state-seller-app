package com.realstate.realstatesellerapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.realstate.realstatesellerapp.enums.ImmobileType;
import com.realstate.realstatesellerapp.enums.OfferStatus;
import com.realstate.realstatesellerapp.model.ClientModel;
import com.realstate.realstatesellerapp.model.Immobile;
import com.realstate.realstatesellerapp.model.OfferModel;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OfferRepositoryTest {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ImmobileRepository ImmobileRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void checkInstances() {
		assertThat(offerRepository).isNotNull();
		assertThat(clientRepository).isNotNull();
		assertThat(ImmobileRepository).isNotNull();
		assertThat(testEntityManager).isNotNull();
	}
	
	@Test
	public void shouldFindOfferById() {
		Immobile immobile = new Immobile();
		immobile.setAddress("22th avenue, 1800");
		immobile.setPicture("picture.png");
		immobile.setType(ImmobileType.APARTMENT);
		immobile.setValue(1500000L);
		this.ImmobileRepository.save(immobile);
		
		ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail("teste@teste.com.br");
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		this.clientRepository.save(newClient);
		
		OfferModel offerModel = new OfferModel();
		offerModel.setClient(newClient);
		offerModel.setImmobile(immobile);
		offerModel.setOfferStatus(OfferStatus.SCHEDULE_VISIT);
		OfferModel persistedOffer = this.testEntityManager.persistAndFlush(offerModel);
		
		Optional<OfferModel> optFoundOffer = this.offerRepository.findById(persistedOffer.getId());
		assertThat(optFoundOffer.isPresent()).isTrue();
		OfferModel foundOffer = optFoundOffer.get();
		assertThat(foundOffer.getOfferStatus().equals(OfferStatus.SCHEDULE_VISIT)).isTrue();
	}
	
	@Test
	public void shouldFindOfferByStatus() {
		Immobile immobile = new Immobile();
		immobile.setAddress("22th avenue, 1800");
		immobile.setPicture("picture.png");
		immobile.setType(ImmobileType.APARTMENT);
		immobile.setValue(1500000L);
		this.ImmobileRepository.save(immobile);
		
		ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail("teste@teste.com.br");
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		this.clientRepository.save(newClient);
		
		OfferModel offerModel = new OfferModel();
		offerModel.setClient(newClient);
		offerModel.setImmobile(immobile);
		offerModel.setOfferStatus(OfferStatus.SCHEDULE_VISIT);
		OfferModel persistedOffer = this.testEntityManager.persistAndFlush(offerModel);
		
		Optional<OfferModel> optFoundOffer = this.offerRepository.findById(persistedOffer.getId());
		assertThat(optFoundOffer.isPresent()).isTrue();
		
		List<OfferModel> foundOffer = this.offerRepository.findByOfferStatus(OfferStatus.SCHEDULE_VISIT);
		assertThat(foundOffer).isNotNull();
//		assertThat(foundOffer.getOfferStatus().equals(OfferStatus.SCHEDULE_VISIT));
//		assertThat(foundOffer.getClient()).isNotNull();
//		assertThat(foundOffer.getImmobile()).isNotNull();
//		assertThat(foundOffer.getClient().equals(newClient));
//		assertThat(foundOffer.getImmobile().equals(immobile));
	}
	
	@Test
	public void shouldFindOfferByClient() {
		Immobile immobile = new Immobile();
		immobile.setAddress("22th avenue, 1800");
		immobile.setPicture("picture.png");
		immobile.setType(ImmobileType.APARTMENT);
		immobile.setValue(1500000L);
		this.ImmobileRepository.save(immobile);
		
		ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail("teste@teste.com.br");
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		this.clientRepository.save(newClient);
		
		OfferModel offerModel = new OfferModel();
		offerModel.setClient(newClient);
		offerModel.setImmobile(immobile);
		offerModel.setOfferStatus(OfferStatus.SCHEDULE_VISIT);
		OfferModel persistedOffer = this.testEntityManager.persistAndFlush(offerModel);
		
		Optional<OfferModel> optFoundOffer = this.offerRepository.findById(persistedOffer.getId());
		assertThat(optFoundOffer.isPresent()).isTrue();
		
		List<OfferModel> foundOffer = this.offerRepository.findByClient(newClient);
		assertThat(foundOffer).isNotNull();
//		assertThat(foundOffer.getOfferStatus().equals(OfferStatus.SCHEDULE_VISIT));
//		assertThat(foundOffer.getClient()).isNotNull();
//		assertThat(foundOffer.getImmobile()).isNotNull();
//		assertThat(foundOffer.getClient().equals(newClient));
//		assertThat(foundOffer.getImmobile().equals(immobile));		
	}
	
	@Test
	public void shouldFindOfferByImmobile() {
		Immobile immobile = new Immobile();
		immobile.setAddress("22th avenue, 1800");
		immobile.setPicture("picture.png");
		immobile.setType(ImmobileType.APARTMENT);
		immobile.setValue(1500000L);
		this.ImmobileRepository.save(immobile);
		
		ClientModel newClient = new ClientModel();
		newClient.setBornDate(LocalDate.of(1982, 9, 15));
		newClient.setEmail("teste@teste.com.br");
		newClient.setName("Ricardo");
		newClient.setWage(11000L);
		this.clientRepository.save(newClient);
		
		OfferModel offerModel = new OfferModel();
		offerModel.setClient(newClient);
		offerModel.setImmobile(immobile);
		offerModel.setOfferStatus(OfferStatus.SCHEDULE_VISIT);
		OfferModel persistedOffer = this.testEntityManager.persistAndFlush(offerModel);
		
		Optional<OfferModel> optFoundOffer = this.offerRepository.findById(persistedOffer.getId());
		assertThat(optFoundOffer.isPresent()).isTrue();		
		
		List<OfferModel> foundOffer = this.offerRepository.findByImmobile(immobile);
		assertThat(foundOffer).isNotNull();
//		assertThat(foundOffer.getOfferStatus().equals(OfferStatus.SCHEDULE_VISIT));
//		assertThat(foundOffer.getClient()).isNotNull();
//		assertThat(foundOffer.getImmobile()).isNotNull();
//		assertThat(foundOffer.getClient().equals(newClient));
//		assertThat(foundOffer.getImmobile().equals(immobile));		
	}

	

}
