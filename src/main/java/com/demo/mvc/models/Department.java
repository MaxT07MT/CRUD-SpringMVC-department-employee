package com.demo.mvc.models;


import net.sf.oval.constraints.Length;
import net.sf.oval.constraints.NotEmpty;
import net.sf.oval.constraints.RegEx;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Please Enter Name")
    private String name;

    @Column(name = "phone")
    @NotEmpty(message = "Please Enter your phone")
    @RegEx(pattern = "^((\\+?380)[0-9]{9})$", message = "this format phone has not to be rely")
    private String phone;

    @Column(name = "email", unique = true)
    @Email(message = "{user.email.invalid}")
    @NotEmpty(message = "Please Enter your email")
    private String email;


    @Column(name = "address")
    @Length(min = 5, max = 50, message = "Input text between 5 and 50 symbols")
    @NotEmpty(message = "Please Enter your address")
    private String address;


    @Column(name = "description")
    @Length(min = 5, max = 150, message = "Input text between 5 and 150 symbols")
    private String description;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}