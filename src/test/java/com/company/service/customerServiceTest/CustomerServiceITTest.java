package com.company.service.customerServiceTest;

import com.company.service.Application;
import com.company.service.customerService.CustomerService;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= Application.class)
public class CustomerServiceITTest {
  
  @Autowired
  private CustomerService customerService;
  
  @Before
  public void setup(){
    customerService.resetCustomers();
  }
  
  @Test
  public void addCustomerTest() {
      String customerName = "Harry";
      Long customerId = customerService.addCustomer(customerName).getId();
      Assert.assertTrue(customerName.equals(customerService.findOne(customerId).getCustomerName()));
  }
  
  
}
