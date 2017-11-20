package com.pets.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class Client {

	@Autowired
	PetsClient petsClient;

	@RequestMapping("/ping")
	public String ping() {
		return petsClient.ping() + "\n";
	}

	@RequestMapping("/pets")
	public List<Pet> getPets() {
		return petsClient.getPets();
	}

	@RequestMapping(value = "/pets/{id}")
	public Pet petById(@PathVariable String id) {
		return petsClient.petById(id);
	}

	@RequestMapping(value = "/pets/register",
			method = POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	void registerNewPet(@RequestBody Pet pet) {
		petsClient.registerNewPet(pet);
	}

	@RequestMapping(value = "/pets/adopt/{id}",
			method = DELETE)
	void adoptPet(@PathVariable String id) {
		System.out.println("Trying to adopt");
		System.out.println(id);
		petsClient.adoptPet(id);
	}
}
