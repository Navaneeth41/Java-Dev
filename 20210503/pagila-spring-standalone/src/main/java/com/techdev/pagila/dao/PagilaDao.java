package com.techdev.pagila.dao;

import com.techdev.pagila.dto.Customer;

public interface PagilaDao {

	public Customer getCustomerId(Long customerId);

	public Long saveCustomer(Customer customer);	
	public Long updateCustomer(Customer customer);	
}
