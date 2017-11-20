package com.pets.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@FeignClient("pet-service")
public interface PetsClient {
	@RequestMapping(value = "/", method = GET)
	String ping();

	@RequestMapping(value = "/pets", method = GET)
	List<Pet> getPets();

	@RequestMapping(value = "/pets/{id}", method = GET)
	Pet petById(@PathVariable(name = "id") String id);

	@RequestMapping(value = "/pets/register", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	void registerNewPet(@RequestBody Pet pet);

	@RequestMapping(value = "/pets/adopt/{id}", method = DELETE)
	void adoptPet(@PathVariable(name = "id") String id);

}
