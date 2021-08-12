package com.tekdev.pagila.pagilaweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = { "hibernateLazyInitializer", "handler", "created" })
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_generator")
	@SequenceGenerator(name = "customer_id_generator", sequenceName = "pagila.customer_customer_id_seq", allocationSize = 1)
	Long customerId; // customer_id

	@NotNull(message = "Store Id is mandatory")
	Long storeId; // store_id
	@NotBlank(message = "First name is mandatory")	
	String firstName; // first_name
	String lastName; // last_name
	@Pattern(regexp=".*", message = "Invalid email.")
	String email; // email
	Long addressId; // address_id

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", insertable = false)
	Date createDate; // create_date

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update", insertable = false)
	Date lastUpdate; // last_update
	Integer active;

	@PrePersist
	protected void onCreate() {
		if (active == null) {
			active = 1;
		}
	}
}
