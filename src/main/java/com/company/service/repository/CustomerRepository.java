package com.company.service.repository;

import com.company.service.entity.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
  
}
