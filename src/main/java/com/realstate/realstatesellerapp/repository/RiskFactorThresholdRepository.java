package com.realstate.realstatesellerapp.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.realstate.realstatesellerapp.model.AccountType;
import com.realstate.realstatesellerapp.model.RiskFactorThreshold;

@Repository
public interface RiskFactorThresholdRepository extends CrudRepository<RiskFactorThreshold, RiskFactorThreshold.RiskFactorThresholdId> {
    
    @Query("SELECT DISTINCT (t.accountType) FROM RiskFactorThreshold t")
    public Set<AccountType> distinctAccountTypeThreshold();
    
    public List<RiskFactorThreshold> findByAccountType(AccountType accountType);

}
