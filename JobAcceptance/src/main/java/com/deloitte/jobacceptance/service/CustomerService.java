package com.deloitte.jobacceptance.service;

import com.deloitte.jobacceptance.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
    void delete(Long id);
}
