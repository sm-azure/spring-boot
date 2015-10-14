package com.company.service.rest;

import static org.junit.Assert.fail;

import com.company.service.Application;
import com.company.service.customerService.CustomerService;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class RestControllerWebIntegrationTest {

  @Autowired
  private CustomerService customerService;
  private RestTemplate restTemplate;
  
  @Before
  public void setup(){
    customerService.resetCustomers();
    restTemplate = new RestTemplate();
  }
  @Test
  public void restAddCustomerTest(){
    JSONObject request = new JSONObject();
    request.put("username", "user");
    request.put("password", "userpassword");
    System.out.println(request.toString());
    ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/auth", request.toString(), String.class);
    HttpStatus status = response.getStatusCode(); 
    Assert.assertTrue(status.is2xxSuccessful());
  }

}
