package com.tekdev.pagila.pagilaweb.dao;

import com.tekdev.pagila.pagilaweb.dto.Customer;
import com.tekdev.pagila.pagilaweb.dto.Actor;

public interface PagilaDao {
	Customer getCustomerById(Long customerId);
	Long saveCustomer(Customer customer);	
	Long updateCustomer(Customer customer);
	boolean deleteCustomer(Long customerId);	
	
	Actor getActorById(Long actorid);
	Long saveActor(Actor actor);	
	Long updateActor(Actor actor);
	boolean deleteActor(Long actorid);	
}
