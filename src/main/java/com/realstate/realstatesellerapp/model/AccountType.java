package com.realstate.realstatesellerapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "ACCOUNT_TYPE")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class AccountType{
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
//    @OneToOne(mappedBy = "accountType", cascade = CascadeType.ALL)
//    private AccountType_Threshold accountTypeThreshold;	

}
