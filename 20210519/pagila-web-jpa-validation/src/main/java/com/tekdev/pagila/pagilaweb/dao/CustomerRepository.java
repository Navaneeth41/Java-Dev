package com.tekdev.pagila.pagilaweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekdev.pagila.pagilaweb.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
