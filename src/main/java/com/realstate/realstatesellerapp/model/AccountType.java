package com.realstate.realstatesellerapp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name= "ACCOUNT_TYPE")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccountType implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
    @OneToOne(mappedBy = "accountType", cascade = CascadeType.ALL)
    private AccountType_Threshold accountTypeThreshold;	

}
