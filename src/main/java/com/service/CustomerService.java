package com.service;

import com.model.Customer;
import com.model.Province;

public interface CustomerService {

    Iterable<Customer> findAll();
    Customer finById(Long id);
    void save(Customer customer);
    void remove(Long id);
    Iterable<Customer> findAllByProvince(Province province);
}
