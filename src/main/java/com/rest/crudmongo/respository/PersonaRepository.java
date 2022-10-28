package com.rest.crudmongo.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rest.crudmongo.model.Persona;

public interface PersonaRepository extends MongoRepository<Persona,String>{
    
}
