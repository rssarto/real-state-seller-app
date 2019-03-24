package com.realstate.realstatesellerapp.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CLIENT_MODEL")
@Getter
@Setter
@EqualsAndHashCode
public class ClientModel {
	
	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String name;
	private LocalDate bornDate;
	private Long wage;
	@JsonIgnore
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="clientModel", cascade= {CascadeType.ALL})
	@OneToMany(fetch=FetchType.LAZY, cascade= {CascadeType.ALL})
	@JoinTable
	(
      name="FAVORITES",
      joinColumns={ @JoinColumn(name="client_id", referencedColumnName="id") },
      inverseJoinColumns={ @JoinColumn(name="immobile_id", referencedColumnName="id", unique=true) }
	)	
	private List<Immobile> favorites;
}
