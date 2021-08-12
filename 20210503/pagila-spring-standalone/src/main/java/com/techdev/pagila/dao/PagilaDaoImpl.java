package com.techdev.pagila.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.techdev.pagila.dto.Customer;

@Component("dao")
public class PagilaDaoImpl implements PagilaDao {
	private static final Logger logger = LoggerFactory.getLogger(PagilaDaoImpl.class);

	private static final String GET_CUSTOMER_BY_ID = "select * from pagila.customer where customer_id  = %s and active = 1";

	//INSERT INTO table_name(column1, column2, …)
	//VALUES (value1, value2, …);
	private static final String SAVE_CUSTOMER = "INSERT INTO pagila.customer(store_id, first_name, last_name, email, address_id, active)"
	+ " VALUES (%s, '%s', '%s', '%s', %s, %s)";
	
	private static final String UPATE_CUSTOMER = "UPDATE pagila.customer SET "
			+ "store_id = %s, first_name = '%s', last_name= '%s', "
			+ "email = '%s', address_id = %s  WHERE customer_id = %s";

	@Autowired
	private DbConfig dbConfig;
	
	public PagilaDaoImpl(DbConfig dbConfig) {
		super();
		this.dbConfig = dbConfig;
	}
	

	private String formatQuery(String queryTemplate, Object... params) {
		return  String.format(queryTemplate, params);
	}
	
	// inner functional interface to create row mapper
	@FunctionalInterface
	private interface RowMapper<T> {
	    public T mapRow(ResultSet rs);
	}

	// passing lambda as a parameter to the function
	private<T> T executeQuery(String query, RowMapper<T> mapper) {
		try (Connection conn = dbConfig.createConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs != null && rs.next()) {
				return mapper.mapRow(rs);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private Long executeUpdate(String query) {
		try (Connection conn = dbConfig.createConnection()){
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				return rs.getLong(1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	private Customer mapCustomer(ResultSet rs) {
		try {
			Customer c = new Customer();
			c.setFirstName(rs.getString("first_name"));
			c.setCustomerId(rs.getLong("customer_id"));
			c.setLastName(rs.getString("last_name"));
			c.setStoreId(rs.getLong("store_id"));
			c.setEmail(rs.getString("email"));
			c.setAddressId(rs.getLong("address_id"));
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Customer getCustomerId(Long customerId) {
		String query = formatQuery(GET_CUSTOMER_BY_ID, customerId);
		return executeQuery(query, (rs) -> mapCustomer(rs));
	}	

	@Override
	public Long saveCustomer (Customer customer) {
		if (customer.getCustomerId() != null) {
			throw new RuntimeException("Please use update operation");	
		}
		String query = formatQuery(SAVE_CUSTOMER, customer.getStoreId(), customer.getFirstName(),
				customer.getLastName(), customer.getEmail(), customer.getAddressId(), "1");
		return executeUpdate(query);
	}	

	@Override
	public Long updateCustomer(Customer customer) {
		if (customer.getCustomerId() == null) {
			throw new RuntimeException("Please use save operation");
		}
		String query = formatQuery(UPATE_CUSTOMER, customer.getStoreId(), customer.getFirstName(),
					customer.getLastName(), customer.getEmail(),
					customer.getAddressId(), customer.getCustomerId());
		return executeUpdate(query);
	}
}
