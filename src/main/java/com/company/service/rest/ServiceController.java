package com.company.service.rest;

import java.net.URI;

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

import com.company.service.entity.Customer;
import com.company.service.repository.CustomerRepository;

@RestController
public class ServiceController {

	
	private CustomerRepository repository;
	
	public ServiceController(){
		
	}
	
	@Autowired
	public void setRepository(CustomerRepository repository){
		this.repository = repository;
	}
	
	@RequestMapping(value="/{customerId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getStringList(@PathVariable ("customerId") int customerId){
		return new ResponseEntity<Customer>(repository.getCustomers(customerId), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{value}", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setNewString(@PathVariable ("value") String value){
		int customerId = repository.storeCustomer(value);
		HttpHeaders headers = new HttpHeaders();
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{customerId}").build()
				.expand(customerId).toUri();
		headers.setLocation(location);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
}
