package com.realstate.realstatesellerapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.realstate.realstatesellerapp.model.Immobile;

@Repository
public interface ImmobileRepository extends CrudRepository<Immobile, Long> {

}
