package com.techdev.pagila.dao;

import com.techdev.pagila.dto.Customer;
import com.techdev.pagila.dto.Actor;

public interface PagilaDao {

	public Customer getCustomerId(Long customerId);

	public Long saveCustomer(Customer customer);	
	
	public Actor getActorid(Long actorid);
	public Long saveActor(Actor actor);
}
