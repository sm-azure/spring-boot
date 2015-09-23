package com.company.service.rest;

import com.company.service.customerService.CustomerService;
import com.company.service.entity.Customer;
import com.company.service.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ServiceController {

	
	private CustomerService service;
	
	public ServiceController(){
		
	}
	
	@Autowired
	public void setRepository(CustomerService service){
		this.service = service;
	}
	
	@RequestMapping(value="/{customerId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getStringList(@PathVariable ("customerId") String customerId){
		return new ResponseEntity<Customer>(service.findOne(customerId), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{value}", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setNewString(@PathVariable ("value") String value){
		Customer customer = service.addCustomer(value);
		HttpHeaders headers = new HttpHeaders();
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{customerId}").build()
				.expand(customer.getId()).toUri();
		headers.setLocation(location);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
}
