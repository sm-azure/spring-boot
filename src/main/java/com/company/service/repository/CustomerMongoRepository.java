package com.company.service.repository;

import com.company.service.entity.Customer;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Document(collection="records")
public interface CustomerMongoRepository extends MongoRepository<Customer, String> {

}
