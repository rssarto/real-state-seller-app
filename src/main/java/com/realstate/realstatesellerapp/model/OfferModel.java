package com.realstate.realstatesellerapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.realstate.realstatesellerapp.enums.OfferStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="OFFER")
@Getter
@Setter
@EqualsAndHashCode
public class OfferModel {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="client_id", unique=false, nullable=false)
	private ClientModel client;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="immobile_id", unique=false, nullable=false)
	private Immobile immobile;
	
	@Enumerated(EnumType.STRING)
	private OfferStatus offerStatus;

}
