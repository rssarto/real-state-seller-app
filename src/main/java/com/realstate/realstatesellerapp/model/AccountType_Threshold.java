package com.realstate.realstatesellerapp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "ACCOUNTTYPE_THRESHOLD")
@AllArgsConstructor
@NoArgsConstructor
public class AccountType_Threshold implements Serializable {
	
	@Id
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(referencedColumnName = "id")
	@MapsId
	private AccountType accountType;
    
    @OneToMany(mappedBy="accountType")
	private List<RiskFactorThreshold> threshold;
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class AccountType_ThresholdId implements Serializable {
    	private AccountType accountType;
    }
}
