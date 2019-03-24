package com.realstate.realstatesellerapp.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.realstate.realstatesellerapp.enums.ImmobileType;
import com.realstate.realstatesellerapp.model.Immobile;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ImmobileRepositoryTest {
	
	@Autowired
	private ImmobileRepository immobileRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void checkRepositoryInstance() {
		assertThat(immobileRepository).isNotNull();
	}
	
	@Test
	public void shouldInsertAnApartment() {
		Immobile immobile = new Immobile();
		immobile.setAddress("22th avenue, 1800");
		immobile.setPicture("picture.png");
		immobile.setType(ImmobileType.APARTMENT);
		immobile.setValue(1500000L);
		Immobile savedImmobile = testEntityManager.persistAndFlush(immobile);
		assertThat(savedImmobile).isNotNull();
		assertThat(savedImmobile.getId()).isNotNull();
		assertThat(savedImmobile.getId() > 0L).isTrue();
	}
	
	@Test
	public void shouldFindImmobileById() {
		Immobile immobile = new Immobile();
		immobile.setAddress("22th avenue, 1800");
		immobile.setPicture("picture.png");
		immobile.setType(ImmobileType.APARTMENT);
		immobile.setValue(1500000L);
		Immobile savedImmobile = testEntityManager.persistAndFlush(immobile);
		Optional<Immobile> optImmobile = this.immobileRepository.findById(savedImmobile.getId());
		assertThat(optImmobile.isPresent()).isTrue();
	}
	
	@Test
	public void shouldUpdateImmobile() {
		final String newAddress = "13th Avenue, 400";
		final String pictureName = "pic.png";
		final ImmobileType immobileType = ImmobileType.HOUSE;
		final Long cost = 400000L;
		
		Immobile immobile = new Immobile();
		immobile.setAddress("22th avenue, 1800");
		immobile.setPicture("picture.png");
		immobile.setType(ImmobileType.APARTMENT);
		immobile.setValue(1500000L);
		Immobile savedImmobile = testEntityManager.persistAndFlush(immobile);
		
		Optional<Immobile> optImmobile = this.immobileRepository.findById(savedImmobile.getId());
		assertThat(optImmobile.isPresent()).isTrue();
		Immobile immobileFound = optImmobile.get();
		immobileFound.setAddress(newAddress);
		immobileFound.setPicture(pictureName);
		immobileFound.setType(immobileType);
		immobileFound.setValue(cost);
		testEntityManager.persist(immobileFound);
		
		
		Optional<Immobile> optUpdatedImmobile = this.immobileRepository.findById(savedImmobile.getId());
		assertThat(optUpdatedImmobile.isPresent()).isTrue();
		Immobile updateImmobile = optUpdatedImmobile.get();
		assertThat(updateImmobile.getAddress().equals(newAddress)).isTrue();
		assertThat(updateImmobile.getPicture().equals(pictureName)).isTrue();
		assertThat(updateImmobile.getType().equals(immobileType)).isTrue();
		assertThat(updateImmobile.getValue().longValue() == cost.longValue()).isTrue();
	}
	
	@Test
	public void shouldDeleteImmobile() {
		Immobile immobile = new Immobile();
		immobile.setAddress("22th avenue, 1800");
		immobile.setPicture("picture.png");
		immobile.setType(ImmobileType.APARTMENT);
		immobile.setValue(1500000L);
		Immobile savedImmobile = testEntityManager.persistAndFlush(immobile);
		
		Optional<Immobile> optImmobile = this.immobileRepository.findById(savedImmobile.getId());
		assertThat(optImmobile.isPresent()).isTrue();
		Immobile immobileFound = optImmobile.get();
		final Long immobileId = immobileFound.getId();
		
		this.immobileRepository.deleteById(immobileId);
		Optional<Immobile> optDeletedImmobile = this.immobileRepository.findById(immobileId);
		assertThat(optDeletedImmobile.isPresent()).isFalse();
	}

}
