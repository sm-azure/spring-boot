package com.company.service.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.company.service.entity.Customer;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private Map<Integer, Customer> customers;
	private Random rand;

	public CustomerRepositoryImpl() {
		customers = new HashMap<Integer, Customer>();
		rand = new Random();
	}

	@Override
	public int storeCustomer(String customerName) {
		int customerId = rand.nextInt();
		customers.put(new Integer(customerId), new Customer(customerName,
				customerId));
		return customerId;
	}

	@Override
	public Customer getCustomers(int customerId) {
		Customer customer = customers.get(new Integer(customerId));
		return customer;
	}

}
