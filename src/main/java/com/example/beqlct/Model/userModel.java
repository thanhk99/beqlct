package com.example.beqlct.Model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class userModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;


    private String account;
    private String password;
    private String username;
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<transaction> transactions;
    @OneToMany(mappedBy = "user")
    private List<wallet> wallet;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccount() {
        return account;
    }
    public void setPassword(String password) { 
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
}
