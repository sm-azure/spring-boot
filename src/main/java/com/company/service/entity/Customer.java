package com.company.service.entity;

public class Customer {
	private String customerName;
	private Integer id;
	
	
	public Customer (){};
	
	public Customer(String customerName, Integer id) {
		super();
		this.customerName = customerName;
		this.id = id;
	}
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
