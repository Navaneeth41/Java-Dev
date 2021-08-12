package com.tekdev.pagila.pagilaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tekdev.pagila.pagilaweb.dto.Actor;

import com.tekdev.pagila.pagilaweb.service.ActorDataService;

@RestController
@CrossOrigin
@RequestMapping("/actor_data")
public class ActorDataController {
	@Autowired
	ActorDataService actorDataService;
	
	@GetMapping("/actor/{actorid}")
	public ResponseEntity<?> getActor(@PathVariable Long actorid) {
		try {
			return getResponseEntity(actorDataService.getActorById(actorid), HttpStatus.OK);
		} catch (Exception ex) {
	         throw new ResponseStatusException(
	                 HttpStatus.BAD_REQUEST, "Bad request. Actor Id is not available.");
		}
	
}
	@PostMapping(path = "/actor", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addActor(@RequestBody Actor actor) {
		try {
		return getResponseEntity(actorDataService.saveActor(actor), HttpStatus.OK);
		} catch (Exception ex) {
	         throw new ResponseStatusException(
	                 HttpStatus.BAD_REQUEST, "Bad request. Some of Actor details are not provided.", ex);
		}
	}
	@PutMapping(path = "/actor", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateActor(@RequestBody Actor actor) {
		return getResponseEntity(actorDataService.updateActor(actor), HttpStatus.OK);
	}

	@DeleteMapping("/actor/{actorid}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long actorid) {
		return getResponseEntity(actorDataService.deleteActor(actorid), HttpStatus.OK);
	}
	private <T> ResponseEntity<T> getResponseEntity(T data, HttpStatus status) {
		if (data == null) {
			throw new RuntimeException("Invalid Request.");
		}
		return new ResponseEntity<T>(data, status);
	}
}
