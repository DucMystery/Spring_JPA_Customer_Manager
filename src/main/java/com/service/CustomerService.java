package com.service;

import com.model.Customer;
import com.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Iterable<Customer> findAll();
    Customer finById(Long id);
    void save(Customer customer);
    void remove(Long id);
    Iterable<Customer> findAllByProvince(Province province);

    Page<Customer> findAllByFirstNameContaining (String firstName,Pageable pageable);
}
