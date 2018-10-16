package com.auribises.gw2018c.model;

public class Customer {

    public Integer id;
    public String name;
    public String phone;
    public String email;

    public Customer(){

    }

    public Customer(Integer id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                "\nNAME: " + name +
                "\nPHONE" + phone +
                "\nEMAIL: " + email;
    }
}

