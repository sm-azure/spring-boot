package com.company.service.repository;

import java.util.List;

import com.company.service.entity.Customer;

public interface CustomerRepository {

	public int storeCustomer(String customerName);
	
	public Customer getCustomers(int customerId);
}
