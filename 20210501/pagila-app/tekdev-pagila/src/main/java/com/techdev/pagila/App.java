package com.techdev.pagila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.techdev.pagila.dao.PagilaDao;
import com.techdev.pagila.dao.PagilaDaoImpl;
import com.techdev.pagila.dto.Actor;
import com.techdev.pagila.dto.Customer;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		PagilaDao dao = new PagilaDaoImpl();
		Customer c = dao.getCustomerId(1l);
		logger.debug(c.toString());
		
		c.setFirstName("test");
		dao.updateCustomer(c);

		c = new Customer();
		c.setFirstName("bob");
		c.setLastName("alice");
		c.setStoreId(1l);
		c.setAddressId(5l);
		c.setEmail("bob.alice@sakilacustomer.org");
		//System.out.println("Saved Customer Id: "+ dao.saveCustomer(c));
		logger.info("Saved Customer Id: "+ dao.saveCustomer(c));
		
		Actor a = dao.getActorid(241l);
		logger.debug(a.toString());
		dao.updateActor(a);
		dao.deleteActor(a);
		a = new Actor();
		a.setFirstname("Hakuna");
		a.setLastname("Mataata");
		logger.info("saved actor id : "+ dao.saveActor(a));
	}
}
