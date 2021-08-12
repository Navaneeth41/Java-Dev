package com.tekdev.pagila.pagilaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekdev.pagila.pagilaweb.dao.CustomerRepository;
import com.tekdev.pagila.pagilaweb.entity.Customer;

@Service
public class CustomerDataService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerValidator validator;
	
	public Customer getCustomerById(Long customerId) {
		return customerRepository.getOne(customerId);
	}
	
	@Transactional
	public Customer saveCustomer(Customer customer) {
		validator.validate(customer, null);
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Customer customer) {
		if (customer.getCustomerId() != null) {
			Customer existingCustomer = getCustomerById(customer.getCustomerId());
			if (existingCustomer.getCreateDate() != null) {
				customer.setCreateDate(existingCustomer.getCreateDate());
			}
		}
		return saveCustomer(customer);
	}

	public boolean deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
		return true;
	}
}
