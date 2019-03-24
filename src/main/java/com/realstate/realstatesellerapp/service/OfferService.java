package com.realstate.realstatesellerapp.service;

import java.util.List;

import com.realstate.realstatesellerapp.model.OfferModel;

public interface OfferService {
	
	OfferModel findById(Long id);
	List<OfferModel> findByEmail(String email);
	OfferModel create(OfferModel offer);
	void deleteById(Long id);
	OfferModel update(OfferModel offer);
	List<OfferModel> findByClientId(Long clientId);

}
