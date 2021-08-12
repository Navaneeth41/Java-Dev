package com.tekdev.pagila.pagilaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekdev.pagila.pagilaweb.dao.PagilaDao;
import com.tekdev.pagila.pagilaweb.dto.Customer;

@Service
public class CustomerDataService {

	@Autowired
	PagilaDao dao;
	
	public Object getCustomerById(Long customerId) {
		return dao.getCustomerById(customerId);
	}
	
	@Transactional
	public Long saveCustomer(Customer customer) {
		return dao.saveCustomer(customer);
	}

	public Long updateCustomer(Customer customer) {
		return dao.updateCustomer(customer);		
	}

	public boolean deleteCustomer(Long customerId) {
		return dao.deleteCustomer(customerId);
	}
}
