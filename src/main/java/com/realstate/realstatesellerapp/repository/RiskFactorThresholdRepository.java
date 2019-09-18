package com.realstate.realstatesellerapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.realstate.realstatesellerapp.model.RiskFactorThreshold;

@Repository
public interface RiskFactorThresholdRepository extends CrudRepository<RiskFactorThreshold, RiskFactorThreshold.RiskFactorThresholdId> {

}
