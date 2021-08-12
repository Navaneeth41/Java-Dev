package com.teckdev.pagila.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techdev.pagila.dto.Customer;

@Component
public class PagilaDaoImpl implements PagilaDao {
	private static final Logger logger = LoggerFactory.getLogger(PagilaDaoImpl.class);
	
	@Autowired
	NamedParameterJdbcTemplate	jdbcTemplate;
	
	@Autowired
	CustomerRowMapper customerRowMapper;

	private static final String GET_CUSTOMER_BY_ID = "select * from pagila.customer where customer_id  = :customer_id and active = 1";

	private static final String GET_NEXT_CUSTOMER_ID = "select NEXTVAL('pagila.customer_customer_id_seq')";

	//INSERT INTO table_name(column1, column2, …)
	//VALUES (value1, value2, …);
	private static final String SAVE_CUSTOMER = "INSERT INTO pagila.customer(customer_id, store_id, first_name, last_name, email, address_id, active)"
	+ " VALUES (:customer_id ,:store_id, :first_name, :last_name, :email, :address_id, :active)";

	private static final String UPATE_CUSTOMER = "UPDATE pagila.customer SET "
			+ "store_id = :store_id, first_name = :first_name, last_name= :last_name, "
			+ "email = :email, address_id = :address_id  WHERE customer_id =:customer_id";
	
	private static final String DELETE_CUSTOMER = "DELETE from pagila.customer where customer_id =:customer_id";
	
	private <T> T executeQuery(String query, SqlParameterSource paramSource,
			RowMapper<T> mapper) {
		return jdbcTemplate.queryForObject(query, paramSource, mapper);
	}
	
	private <T> T executeQuery(String query, MapSqlParameterSource paramSource,
			Class<T> klass) {
		return jdbcTemplate.queryForObject(query, paramSource, klass);
	}

	private int executeUpdate(String updateQuery, SqlParameterSource paramSource) {
		return jdbcTemplate.update(updateQuery, paramSource);
	}
	
	@Override
	public Customer getCustomerById(Long customerId) {
	    SqlParameterSource paramSource = new MapSqlParameterSource().addValue("customer_id", customerId);
		return executeQuery(GET_CUSTOMER_BY_ID, paramSource, customerRowMapper);
	}	

	private static SqlParameterSource getCustomerParamSource(Customer customer) {
	    SqlParameterSource paramSource = new MapSqlParameterSource()
	    .addValue("customer_id", customer.getCustomerId())
	    .addValue("store_id", customer.getStoreId())
	    .addValue("first_name", customer.getFirstName())
	    .addValue("last_name", customer.getLastName())
	    .addValue("email", customer.getEmail())
	    .addValue("address_id", customer.getAddressId())
	    .addValue("active", 1);
	    return paramSource;
	}
	
	@Override
	public Long saveCustomer (Customer customer) {
		if (customer.getCustomerId() != null) {
			throw new RuntimeException("Please use update operation");	
		}
		customer.setCustomerId(executeQuery(GET_NEXT_CUSTOMER_ID, new MapSqlParameterSource(), Long.class));
	    executeUpdate(SAVE_CUSTOMER, getCustomerParamSource(customer));
	    return customer.getCustomerId();
	}	

	@Override
	public Long updateCustomer(Customer customer) {
		if (customer.getCustomerId() == null) {
			throw new RuntimeException("Please use save operation");
		}
	    executeUpdate(UPATE_CUSTOMER, getCustomerParamSource(customer));
	    return customer.getCustomerId();
	}

	@Override
	public boolean deleteCustomer(Long customerId) {
		if (customerId == null) {
			throw new RuntimeException("Custome id is required for customer remove.");
		}
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
	    int effectedRows = executeUpdate(DELETE_CUSTOMER, getCustomerParamSource(customer));
	    return (effectedRows == 0) ? false: true;
	}
}
