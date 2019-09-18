package com.realstate.realstatesellerapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.realstate.realstatesellerapp.model.AccountType;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {

}
