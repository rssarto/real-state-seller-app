package com.realstate.realstatesellerapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.realstate.realstatesellerapp.model.ClientModel;

@Repository
public interface ClientRepository extends CrudRepository<ClientModel, Long> {
	
	ClientModel findByEmail(String email);

}
