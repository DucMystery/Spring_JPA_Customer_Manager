package com.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    public Customer() {
    }

    public Customer(String firstName,String lastName){
        this.firstName =firstName;
        this.lastName =lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer [id=%d ,firstName = '%s' , lastName = '%s']",id,firstName,lastName);
    }

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

}
