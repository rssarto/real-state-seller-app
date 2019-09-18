package com.realstate.realstatesellerapp.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "ACCOUNTTYPE_THRESHOLD")
@AllArgsConstructor
@NoArgsConstructor
public class AccountType_Threshold {
	
//	@Id
//	private Long id;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@PrimaryKeyJoinColumn(referencedColumnName = "id")
//	@MapsId
	private AccountType accountType;
    
//    @OneToMany(mappedBy="accountType")
	private List<RiskFactorThreshold> thresholds;
	
    
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<RiskFactorThreshold> getThresholds() {
        return thresholds;
    }

    public void setThresholds(List<RiskFactorThreshold> thresholds) {
        this.thresholds = thresholds;
    }



    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class AccountType_ThresholdId implements Serializable {
    	private AccountType accountType;
    }
}
