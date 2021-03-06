package com.company.service.customerServiceTest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.company.service.customerService.CustomerServiceImpl;
import com.company.service.entity.Customer;
import com.company.service.repository.CustomerRepository;

public class CustomerServiceTest {

  private CustomerServiceImpl customerService;

  @Mock
  private CustomerRepository repository ;

  @Before
  public void setUp() {
    repository = mock(CustomerRepository.class);
    customerService = new CustomerServiceImpl(repository);
  }

  @Test
  public void test() {
    String customerName = "Harry";
    Customer customer = new Customer(customerName);
    Long customerId = new Long(5);
    customer.setId(customerId);
    
    when(repository.save(any(Customer.class))).thenReturn(customer);
    when(repository.findOne(customerId)).thenReturn(customer);
    
    Customer testCustomer = customerService.addCustomer(customerName);
    Long customerSavedId = testCustomer.getId();
    Assert.assertTrue(customerName.equals(customerService.findOne(customerSavedId).getCustomerName()));
  }

}
