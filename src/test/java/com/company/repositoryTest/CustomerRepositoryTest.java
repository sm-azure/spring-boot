package com.company.repositoryTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.company.service.repository.CustomerRepository;
import com.company.service.repository.CustomerRepositoryImpl;

public class CustomerRepositoryTest {

	
	
	@Test
	public void test() {
		CustomerRepository repository = new CustomerRepositoryImpl();
		String customerName = "Harry";
		int customerId = repository.storeCustomer(customerName);
		Assert.assertTrue(customerName.equals(repository.getCustomers(customerId).getCustomerName()));
	}

}
