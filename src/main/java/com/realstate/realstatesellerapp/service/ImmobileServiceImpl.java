package com.realstate.realstatesellerapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realstate.realstatesellerapp.exception.ImmobileNotFoundException;
import com.realstate.realstatesellerapp.model.Immobile;
import com.realstate.realstatesellerapp.repository.ImmobileRepository;

@Service
public class ImmobileServiceImpl implements ImmobileService {
	
	private ImmobileRepository immobileRepository;
	
	public ImmobileServiceImpl(@Autowired ImmobileRepository immobileRepository) {
		this.immobileRepository = immobileRepository;
	}

	@Override
	public Immobile findById(Long id) {
		Optional<Immobile> optImmobile = this.immobileRepository.findById(id);
		if( !optImmobile.isPresent() ) {
			throw new ImmobileNotFoundException(String.format("Immobile not found with id %d", id));
		}
		return optImmobile.get();
	}

	@Override
	public List<Immobile> getAll() {
		final List<Immobile> listImmobile = new ArrayList<>();
		Iterable<Immobile> iteratorImmobile = this.immobileRepository.findAll();
		iteratorImmobile.forEach(immobile -> listImmobile.add(immobile));
		return listImmobile;
	}
}
