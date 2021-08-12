package com.techdev.pagila.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.techdev.pagila.dto.Actor;
import com.techdev.pagila.dto.Customer;

public class PagilaDaoImpl implements PagilaDao {

	private static final String GET_CUSTOMER_BY_ID = "select * from pagila.customer where customer_id  = %s and active = 1";

	//INSERT INTO table_name(column1, column2, …)
	//VALUES (value1, value2, …);
	private static final String SAVE_CUSTOMER = "INSERT INTO pagila.customer(store_id, first_name, last_name, email, address_id, active)"
	+ " VALUES (%s, '%s', '%s', '%s', %s, %s)";
	
	private static final String GET_ACTOR_BY_ID = "select * from pagila.actor where actor_id = %s";
	private static final String SAVE_ACTOR = "INSERT INTO pagila.actor(first_name, last_name)" + "VALUES('%s', '%s')";
	
	private String formatQuery(String queryTemplate, Object... params) {
		return  String.format(queryTemplate, params);
	}
	
	// inner functional interface to create row mapper
	private interface RowMapper<T> {
	    public T mapRow(ResultSet rs);
	}

	// passing lambda as a parameter to the function
	private<T> T executeQuery(String query, RowMapper<T> mapper) {
		try (Connection conn = DbConfig.createConnection()){
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
		try (Connection conn = DbConfig.createConnection()){
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				return rs.getLong(1);
			}
			

		} catch (Exception e) {
			System.out.println(e);
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
	private Actor mapActor(ResultSet rs) {
	try {
		Actor a = new Actor();
		a.setActorid(rs.getLong("actor_id"));
		a.setFirstname(rs.getString("first_name"));
		a.setLastname(rs.getString("last_name"));
		return a;
	}	catch(SQLException e) {
		e.getMessage();
		}
	return null;
	}
	
	
	@Override
	public Customer getCustomerId(Long customerId) {
		String query = formatQuery(GET_CUSTOMER_BY_ID, customerId);
		return executeQuery(query, (rs) -> mapCustomer(rs));
	}	

	@Override
	public Long saveCustomer(Customer customer) {
		if (customer.getCustomerId() != null) {
			throw new RuntimeException ("Update is not yer implemented.");
		}
		String query = formatQuery(SAVE_CUSTOMER, customer.getStoreId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddressId(), "1");
		return executeUpdate(query);
	}


	
	@Override
	public Actor getActorid(Long actorid) {
		String query = formatQuery(GET_ACTOR_BY_ID, actorid);
		return executeQuery(query, (rs) -> mapActor(rs));
	}	
	@Override
	public Long saveActor(Actor actor) {
		if(actor.getActorid() != null) {
			throw new RuntimeException ("Update is not yet implemented.");	
		}
		String query = formatQuery(SAVE_ACTOR, actor.getFirstname(), actor.getLastname());
		return executeUpdate(query);
	}

	
}
