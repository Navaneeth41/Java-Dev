package com.tekdev.pagila.pagilaweb;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tekdev.pagila.pagilaweb.entity.Actor;
import com.tekdev.pagila.pagilaweb.service.ActorDataService;

@RestController
@CrossOrigin
@RequestMapping("/actor_data")
public class ActorDataController {
	@Autowired
	ActorDataService actorDataService;

	@GetMapping("/actor")
	public ResponseEntity<?> getActors() {
		return getResponseEntity(actorDataService.getActors(), HttpStatus.OK);
	}

	@GetMapping("/actor/{actorId}")
	public ResponseEntity<?> getActor(@PathVariable @Valid Long actorId) {
		try {
			return getResponseEntity(actorDataService.getActorById(actorId), HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request. Actor Id is not available.");
		}

	}

	@PostMapping(path = "/actor", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addActor(@RequestBody @Valid Actor actor) {
		try {
			return getResponseEntity(actorDataService.saveActor(actor), HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Bad request. Some of Actor details are not provided.", ex);
		}
	}

	@PutMapping(path = "/actor", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateActor(@RequestBody @Valid Actor actor) {
		return getResponseEntity(actorDataService.updateActor(actor), HttpStatus.OK);
	}

	@DeleteMapping("/actor/{actorId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable @Valid Long actorId) {
		return getResponseEntity(actorDataService.deleteActor(actorId), HttpStatus.OK);
	}

	private <T> ResponseEntity<T> getResponseEntity(T data, HttpStatus status) {
		if (data == null) {
			throw new RuntimeException("Invalid Request.");
		}
		return new ResponseEntity<T>(data, status);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
