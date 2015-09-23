package com.company.service.customerService;

import com.company.service.entity.Customer;
import com.company.service.repository.CustomerMongoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class CustomerServiceImpl implements CustomerService {

  
  private CustomerMongoRepository repository;
  
  @Autowired
  public CustomerServiceImpl(CustomerMongoRepository repository) {
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
  public Customer findOne(String customerId) {
    return repository.findOne(customerId);
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
  public void deleteCustomer(String customerId) {
    // TODO Auto-generated method stub

  }

}
