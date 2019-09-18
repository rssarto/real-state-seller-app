package com.realstate.realstatesellerapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realstate.realstatesellerapp.model.AccountType;
import com.realstate.realstatesellerapp.model.AccountType_Threshold;
import com.realstate.realstatesellerapp.model.RiskFactorThreshold;
import com.realstate.realstatesellerapp.repository.RiskFactorThresholdRepository;

@RestController
@RequestMapping(value="/api/v1")
public class RiskFactorThresholdController {
    
    @Autowired
    private RiskFactorThresholdRepository factorThresholdRepository;
    
    @GetMapping("/thresholds")
    public ResponseEntity<List<AccountType_Threshold>> listThreshold(){
        final List<AccountType_Threshold> thresholds = new ArrayList<>();
        final Set<AccountType> distinctAccountTypeThreshold = factorThresholdRepository.distinctAccountTypeThreshold();
        distinctAccountTypeThreshold.forEach(accountType -> {
            final List<RiskFactorThreshold> thresholdList = this.factorThresholdRepository.findByAccountType(accountType);
            final AccountType_Threshold accountType_Threshold = new AccountType_Threshold();
            accountType_Threshold.setThresholds(thresholdList);
            accountType_Threshold.setAccountType(accountType);
            thresholds.add(accountType_Threshold);
        });
        return new ResponseEntity<>(thresholds, HttpStatus.OK);
    }
    
}
