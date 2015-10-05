package com.company.service.customerService;

import java.util.Collection;

import com.company.service.entity.Customer;

public interface CustomerService {

  public Collection<Customer> findAll();
  
  public Customer findOne(Long customerId);
  
  public Customer addCustomer (String customerName);
  
  public Customer updateCustomer(Customer customer);
  
  public void deleteCustomer(Long customerId);
 
  public void resetCustomers();
  
}
