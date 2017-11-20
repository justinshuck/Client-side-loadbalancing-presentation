package com.demo.pet.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableDiscoveryClient
//@RibbonClient(name = "pet-service", configuration = PetServiceConfiguration.class)
public class PetService {

	private static Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	DiscoveryClient client;

	@Autowired
	private PetRepository repository;

	@RequestMapping("/")
	public String ping() throws InterruptedException {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		log.info("Called [/]' on port {}", localInstance.getPort());
//		Thread.sleep(2000);
		return String.format("Using '%s' running on port %s \\n",localInstance.getServiceId(), Integer.toString(localInstance.getPort()));
	}

	@RequestMapping("/pets")
	public List<Pet> pets() {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		log.info("Called [/pets]' on port {}", localInstance.getPort());
		return repository.findAll();
	}

	@RequestMapping("/pets/{id}")
	public Pet petsById(@PathVariable String id) {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		log.info("Called [/pets/{}]' on port {}", id, localInstance.getPort());
		return repository.findById(id);
	}

	@RequestMapping(value = "/pets/register",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void registerNewPet(@RequestBody Pet pet) {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		log.info("Called [/pets/register]' on port {}", localInstance.getPort());
		repository.save(pet);
	}
	@RequestMapping(value = "/pets/adopt/{id}",
			method = RequestMethod.DELETE)
	public void adoptPet(@PathVariable String id) {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		log.info("Called [/pets/adopt/{}]' on port {}", id, localInstance.getPort());
		repository.delete(id);
	}

}
