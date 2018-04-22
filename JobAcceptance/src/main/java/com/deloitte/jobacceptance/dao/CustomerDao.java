package com.deloitte.jobacceptance.dao;

import com.deloitte.jobacceptance.model.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
    void delete(Long id);
}
