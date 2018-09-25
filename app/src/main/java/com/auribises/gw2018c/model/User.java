package com.auribises.gw2018c.model;

import java.io.Serializable;

// Model
public class User implements Serializable{

    public int image;
    public String name;
    public String email;
    public String password;
    public String gender;
    public String city;

    public User(){

    }

    public User(int image, String name, String email, String password, String gender, String city) {
        this.image = image;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.city = city;
    }

    public User(int image, String name, String email){
        this.image = image;
        this.name = name;
        this.email = email;
    }

    // toString is executed automatically when you print ref variable of object !!
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
