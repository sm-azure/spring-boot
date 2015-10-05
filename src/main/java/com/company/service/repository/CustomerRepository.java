package com.company.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.service.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
  
}
