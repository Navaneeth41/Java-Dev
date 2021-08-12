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

import com.tekdev.pagila.pagilaweb.dto.Customer;
import com.tekdev.pagila.pagilaweb.service.CustomerDataService;

@RestController
@CrossOrigin
@RequestMapping("/customer_data")
public class CustomerDataController {

	@Autowired
	CustomerDataService customerDataService;
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable Long customerId) {
		try {
			return getResponseEntity(customerDataService.getCustomerById(customerId), HttpStatus.OK);
		} catch (Exception ex) {
	         throw new ResponseStatusException(
	                 HttpStatus.BAD_REQUEST, "Bad request. Customer Id is not available.");
		}
	}

	@PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {
		return getResponseEntity(customerDataService.saveCustomer(customer), HttpStatus.OK);
		} catch (Exception ex) {
	         throw new ResponseStatusException(
	                 HttpStatus.BAD_REQUEST, "Bad request. Some of customer details are not provided.", ex);
		}
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
}
