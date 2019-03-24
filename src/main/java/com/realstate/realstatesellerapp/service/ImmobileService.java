package com.realstate.realstatesellerapp.service;

import java.util.List;

import com.realstate.realstatesellerapp.model.Immobile;

public interface ImmobileService {
	
	Immobile findById(Long id);
	List<Immobile> getAll();

}
