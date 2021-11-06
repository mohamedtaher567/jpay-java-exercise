package com.java.exercise.jpay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.java.exercise.jpay.model.Customer;
import com.java.exercise.jpay.repository.CustomerRepository;

@SpringBootTest
public class CustomerPhoneNumbersServiceTests {
  private final static int NO_CUSTOMERS = 10;
  @Autowired
  private CustomerRepository customerRepository;

  void initialize() {
    List<Customer> customers = new ArrayList<>();
    for (int i = 0; i < NO_CUSTOMERS; i++) {
      Customer customer = new Customer();
      customer.setName(String.format("customer_%d_name", i));
      customer.setPhone("2345");
      customers.add(customer);
    }
    customerRepository.saveAll(customers);
  }

  @Test
  void testSave() {
    assertEquals(10, customerRepository.count());
  }

}
