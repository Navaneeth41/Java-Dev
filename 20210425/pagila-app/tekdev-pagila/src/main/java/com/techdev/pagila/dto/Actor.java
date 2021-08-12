package com.techdev.pagila.dto;

public class Actor {
	Long actorid;
	String firstname;
	String lastname;
	public Long getActorid() {
		return actorid;
	}
	public void setActorid(Long actorid) {
		this.actorid = actorid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public String toString() {
		return "Actor [actorid=" + actorid + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

}