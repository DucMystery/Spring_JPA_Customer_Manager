package com.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "provinces")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Customer.class)
    private Set<Customer> customers;

    public Province() {
    }

    public Province(String name){
        this.name =name;
    }

    public Long getId() {
        return id;
    }

    public Province setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Province setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Province setCustomers(Set<Customer> customers) {
        this.customers = customers;
        return this;
    }
}
