package com.realstate.realstatesellerapp.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.realstate.realstatesellerapp.enums.ImmobileType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="IMMOBILE")
@Getter
@Setter
@EqualsAndHashCode
public class Immobile {
	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private ImmobileType type;
	private Long value;
	private String address;
	private String picture;
}
