package com.teckdev.pagila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techdev.pagila.dto.Customer;
import com.teckdev.pagila.dao.PagilaDao;

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
