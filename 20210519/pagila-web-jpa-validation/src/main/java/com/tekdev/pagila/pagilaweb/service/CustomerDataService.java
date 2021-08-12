package com.tekdev.pagila.pagilaweb.service;

import java.util.List;

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

	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer getCustomerById(Long customerId) {
		return customerRepository.getOne(customerId);
	}
	
	@Transactional
	public Customer saveCustomer(Customer customer) {
		validator.validate(customer, null);
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Customer customer) {
		new Exception().printStackTrace();
		if (customer.getCustomerId() != null) {
			Customer existingCustomer = getCustomerById(customer.getCustomerId());
			if (existingCustomer.getCreateDate() != null) {
				customer.setCreateDate(existingCustomer.getCreateDate());
			}
		}
		return saveCustomer(customer);
	}

	public boolean deleteCustomer(Long customerId) {
		new Exception().printStackTrace();
		customerRepository.deleteById(customerId);
		return true;
	}
}
