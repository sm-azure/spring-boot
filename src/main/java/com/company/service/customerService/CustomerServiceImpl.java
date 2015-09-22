package com.company.service.customerService;

import com.company.service.entity.Customer;
import com.company.service.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class CustomerServiceImpl implements CustomerService {

  
  private CustomerRepository repository;
  
  @Autowired
  public CustomerServiceImpl(CustomerRepository repository) {
    this.repository = repository;
  }
  
  @Override
  public void resetCustomers(){
    repository.deleteAll();
  }
  
  
  @Override
  public Collection<Customer> findAll() {
    return (Collection<Customer>)repository.findAll();
  }

  @Override
  public Customer findOne(Long customerId) {
    return repository.findOne(new Long(customerId));
  }

  @Override
  public Customer addCustomer(String customerName) {
    Customer customer = new Customer(customerName);
    return repository.save(customer);
  }

  @Override
  public Customer updateCustomer(Customer customer) {  
    return null;
  }

  @Override
  public void deleteCustomer(Long customerId) {
    // TODO Auto-generated method stub

  }

}
