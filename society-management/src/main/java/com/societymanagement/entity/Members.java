package com.societymanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "members")
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Mem_type")
    private String mem_type;

    public Members() {
    }

    public Members(String name, String phone, String email, String pass, String mem_type) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = pass;
        this.mem_type = mem_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public String getMem_type() {
        return mem_type;
    }

    public void setMem_type(String mem_type) {
        this.mem_type = mem_type;
    }
}
