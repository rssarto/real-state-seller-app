package com.realstate.realstatesellerapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.realstate.realstatesellerapp.enums.OfferStatus;
import com.realstate.realstatesellerapp.model.ClientModel;
import com.realstate.realstatesellerapp.model.Immobile;
import com.realstate.realstatesellerapp.model.OfferModel;

public interface OfferRepository extends CrudRepository<OfferModel, Long> {

	List<OfferModel> findByOfferStatus(OfferStatus scheduleVisit);

	List<OfferModel> findByClient(ClientModel newClient);

	List<OfferModel> findByImmobile(Immobile immobile);

}
