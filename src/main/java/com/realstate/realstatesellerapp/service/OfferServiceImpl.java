package com.realstate.realstatesellerapp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.realstate.realstatesellerapp.enums.OfferStatus;
import com.realstate.realstatesellerapp.exception.OfferBadRequestException;
import com.realstate.realstatesellerapp.exception.OfferNotFoundException;
import com.realstate.realstatesellerapp.model.ClientModel;
import com.realstate.realstatesellerapp.model.Immobile;
import com.realstate.realstatesellerapp.model.OfferModel;
import com.realstate.realstatesellerapp.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);
	
	private OfferRepository offerRepository;
	private ClientService clientService;
	private ImmobileService immobileService;

	public OfferServiceImpl(@Autowired OfferRepository offerRepository, @Autowired ClientService clientService, @Autowired ImmobileService immobileService) {
		this.offerRepository = offerRepository;
		this.clientService = clientService;
		this.immobileService = immobileService;
	}

	@Override
	public OfferModel findById(Long id) {
		Optional<OfferModel> optOfferModel = this.offerRepository.findById(id);
		if( !optOfferModel.isPresent() ) {
			throw new OfferNotFoundException(String.format("Offer not found with id %d", id));
		}
		return optOfferModel.get();
	}

	@Override
	public List<OfferModel> findByEmail(String email) {
		ClientModel clientModel = this.clientService.findByEmail(email);
		List<OfferModel> foundOffer = this.offerRepository.findByClient(clientModel);
		if( Objects.isNull(foundOffer) ) {
			throw new OfferNotFoundException(String.format("Offer not found with client id %d", clientModel.getId()));
		}
		return foundOffer;
	}
	
	@Override
	public List<OfferModel> findByClientId(Long clientId) {
		ClientModel clientModel = this.clientService.findById(clientId);
		List<OfferModel> foundOffer = this.offerRepository.findByClient(clientModel);
		if( Objects.isNull(foundOffer) ) {
			throw new OfferNotFoundException(String.format("Offer not found with client id %d", clientModel.getId()));
		}
		return foundOffer;
	}

	@Override
	public OfferModel create(OfferModel offer) {
		final ClientModel foundClient = this.clientService.findById(offer.getClient().getId());
		final Immobile foundImmobile = this.immobileService.findById(offer.getImmobile().getId());
		List<OfferModel> listOffer = this.offerRepository.findByClient(foundClient);
		if( Objects.nonNull(listOffer) ) {
			List<OfferModel> listOfferWithSameImmobile = listOffer.stream().filter((streamOffer) -> {
				return streamOffer.getImmobile().getId() == foundImmobile.getId();
			}).collect(Collectors.toList());
			
			if( Objects.nonNull(listOfferWithSameImmobile) && !listOfferWithSameImmobile.isEmpty() ) {
				throw new OfferBadRequestException(String.format("Client already has the immobile with id %d", foundImmobile.getId()));
			}
		}
		
		offer.setClient(foundClient);
		offer.setImmobile(foundImmobile);
		offer.setOfferStatus(OfferStatus.SCHEDULE_VISIT);
		return this.offerRepository.save(offer);
	}

	@Override
	public void deleteById(Long id) {
		if( !this.offerRepository.findById(id).isPresent() ) {
			throw new OfferNotFoundException(String.format("Offer not found with id %d", id));
		}
		this.offerRepository.deleteById(id);
	}

	@Override
	public OfferModel update(OfferModel offer) {
		boolean isUpdate = false;
		Optional<OfferModel> optOffer = this.offerRepository.findById(offer.getId());
		if( !optOffer.isPresent() ) {
			throw new OfferNotFoundException(String.format("Offer not found with client id %d", offer.getId()));
		}
		
		OfferModel offerModel = optOffer.get();
		
		if( Objects.nonNull(offer.getClient().getId()) ) {
			ClientModel foundClient = this.clientService.findById(offer.getClient().getId());
			offerModel.setClient(foundClient);
			isUpdate = true;
		}
		
		if( Objects.nonNull(offer.getImmobile().getId()) ) {
			Immobile foundImmobile = this.immobileService.findById(offer.getImmobile().getId());
			offerModel.setImmobile(foundImmobile);
			isUpdate = true;
		}
		
		if( isUpdate ) {
			this.offerRepository.save(offerModel);
		}
		
		return offerModel;
	}
	
	@Scheduled(fixedDelay= ((1000 * 60) * 5))
	private void updateWaitingVisiteToWaitingLoan() {
		LOGGER.info("updateWaitingVisiteToWaitingLoan");
		List<OfferModel> findByOfferStatus = this.offerRepository.findByOfferStatus(OfferStatus.SCHEDULE_VISIT);
		if( Objects.nonNull(findByOfferStatus) ) {
			findByOfferStatus.forEach(offer -> {
				offer.setOfferStatus(OfferStatus.WAITING_MORTGAGE_LOAN_APPROVAL);
				this.offerRepository.save(offer);
			});
		}
	}
	
	@Scheduled(fixedDelay= ((1000 * 60) * 6))
	private void updateWaitingLoanToApproved() {
		LOGGER.info("updateWaitingLoanToApproved");
		List<OfferModel> findByOfferStatus = this.offerRepository.findByOfferStatus(OfferStatus.WAITING_MORTGAGE_LOAN_APPROVAL);
		if( Objects.nonNull(findByOfferStatus) ) {
			findByOfferStatus.forEach(offer -> {
				offer.setOfferStatus(OfferStatus.APPROVED);
				this.offerRepository.save(offer);
			});
		}		
	}

}
