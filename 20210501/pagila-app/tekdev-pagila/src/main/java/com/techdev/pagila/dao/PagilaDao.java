package com.techdev.pagila.dao;

import com.techdev.pagila.dto.Actor;
import com.techdev.pagila.dto.Customer;

public interface PagilaDao {

	public Customer getCustomerId(Long customerId);

	public Long saveCustomer(Customer customer);	
	public Long updateCustomer(Customer customer);	
	public Actor getActorid(Long actorid);
	public Long saveActor(Actor actor);
	public Long updateActor(Actor actor);
	public Long deleteActor(Actor actor);
}
