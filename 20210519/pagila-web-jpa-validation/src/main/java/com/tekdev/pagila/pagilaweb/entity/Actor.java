package com.tekdev.pagila.pagilaweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = { "hibernateLazyInitializer", "handler", "created" })
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_id_generator")
	@SequenceGenerator(name = "actor_id_generator", sequenceName = "pagila.actor_actor_id_seq", allocationSize = 1)
	Long actorId;

	@NotBlank(message = "First name is mandatory")
	String firstName; // first_name
	@NotBlank(message = "Last name is mandatory")
	String lastName; // last_name

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update", insertable = false)
	Date lastUpdate; // last_update
}
