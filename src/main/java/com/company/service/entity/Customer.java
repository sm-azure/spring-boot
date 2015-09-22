package com.company.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

  private String customerName;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  public Customer() {};

  public Customer(String customerName) {
    super();
    this.customerName = customerName;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
