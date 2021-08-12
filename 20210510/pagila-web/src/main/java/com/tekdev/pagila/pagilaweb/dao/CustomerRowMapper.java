package com.tekdev.pagila.pagilaweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.tekdev.pagila.pagilaweb.dto.Customer;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId(rs.getLong("customer_id"));
		customer.setAddressId(rs.getLong("address_id"));
		customer.setCreateDate(rs.getDate("create_date"));
		customer.setEmail(rs.getString("email"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.setLastUpdate(rs.getTimestamp("last_update"));
		customer.setStoreId(rs.getLong("store_id"));
		return customer;
	}
}
