package com.company.service.customerServiceTest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.company.service.customerService.CustomerServiceImpl;
import com.company.service.entity.Customer;
import com.company.service.repository.CustomerMongoRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class CustomerServiceTest {

  private CustomerServiceImpl customerService;

  @Mock
  private CustomerMongoRepository repository ;

  @Before
  public void setUp() {
    repository = mock(CustomerMongoRepository.class);
    customerService = new CustomerServiceImpl(repository);
  }

  @Test
  public void test() {
    String customerName = "Harry";
    Customer customer = new Customer(customerName);
    String customerId = "5";
    customer.setId(customerId);
    
    when(repository.save(any(Customer.class))).thenReturn(customer);
    when(repository.findOne(customerId)).thenReturn(customer);
    
    Customer testCustomer = customerService.addCustomer(customerName);
    String customerSavedId = testCustomer.getId();
    Assert.assertTrue(customerName.equals(customerService.findOne(customerSavedId).getCustomerName()));
  }

}
