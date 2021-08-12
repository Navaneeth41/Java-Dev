package com.tekdev.pagila.pagilaweb;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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

import com.tekdev.pagila.pagilaweb.entity.Customer;
import com.tekdev.pagila.pagilaweb.service.CustomerDataService;

@RestController
@CrossOrigin
@RequestMapping("/customer_data")
@Validated
public class CustomerDataController {

	@Autowired
	CustomerDataService customerDataService;
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable @Min(1) Long customerId) {
		try {
			return getResponseEntity(customerDataService.getCustomerById(customerId), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
	         throw new ResponseStatusException(
	                 HttpStatus.BAD_REQUEST, "Bad request. Customer Id is not available.");
		}
	}

	@PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
		//try {
		return getResponseEntity(customerDataService.saveCustomer(customer), HttpStatus.OK);
		/*} catch (Exception ex) {
	         throw new ResponseStatusException(
	                 HttpStatus.BAD_REQUEST, "Bad request. Some of customer details are not provided.", ex);
		}*/
	}

	@PutMapping(path = "/customer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		return getResponseEntity(customerDataService.updateCustomer(customer), HttpStatus.OK);
	}

	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
		return getResponseEntity(customerDataService.deleteCustomer(customerId), HttpStatus.OK);
	}
	
	private <T> ResponseEntity<T> getResponseEntity(T data, HttpStatus status) {
		if (data == null) {
			throw new RuntimeException("Invalid Request.");
		}
		return new ResponseEntity<T>(data, status);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
