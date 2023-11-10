package com.petrinet.Repository;

import com.petrinet.Entity.Transition;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called transitionRepository
// CRUD refers Create, Read, Update, Delete

public interface TransitionRepository extends CrudRepository<Transition, Integer> {

}
