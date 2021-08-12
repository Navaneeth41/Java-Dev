package com.teckdev.pagila.dao;

import com.techdev.pagila.dto.Customer;

public interface PagilaDao {
	Customer getCustomerById(Long customerId);
	Long saveCustomer(Customer customer);	
	Long updateCustomer(Customer customer);
	boolean deleteCustomer(Long customerId);	
}
