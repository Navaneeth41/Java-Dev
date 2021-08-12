package com.techdev.pagila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.techdev.pagila.dto.Customer;

public class AppByAnnotation {
	private static final Logger logger = LoggerFactory.getLogger(AppByAnnotation.class);

	ContextConfigByAnnotation config = ContextConfigByAnnotation.getInstance();

	private void accessDb() {
		Customer c = config.getDao().getCustomerId(1l);
		logger.debug(c.toString());

		c.setFirstName("test");
		config.getDao().updateCustomer(c);

		c = new Customer();
		c.setFirstName("bob");
		c.setLastName("alice");
		c.setStoreId(1l);
		c.setAddressId(5l);
		c.setEmail("bob.alice@sakilacustomer.org");
		// System.out.println("Saved Customer Id: "+ dao.saveCustomer(c));
		logger.info("Saved Customer Id: " + config.getDao().saveCustomer(c));
	}

	public static void main(String[] args) {
		AppByAnnotation app = new AppByAnnotation();
		app.accessDb();
	}
}
