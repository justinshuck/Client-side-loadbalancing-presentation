package com.demo.pet.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PetRepository extends MongoRepository<Pet, String> {
	public List<Pet> findByName(String name);
	public Pet findById(String id);

}
