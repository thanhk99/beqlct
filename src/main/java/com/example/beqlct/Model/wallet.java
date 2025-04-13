package com.example.beqlct.Model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="id_user")
    private int idUser;
    @ManyToOne
    @JoinColumn(name = "id_user",insertable=false, updatable=false)
    private userModel user;

    private String nameWallet;
    private String currency;
    private float balance;

    @OneToMany(mappedBy = "wallet")
    private List<transaction> transactions;
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setIdUser(int idUser){
        this.idUser = idUser;
    }
    public int getIdUser(){
        return this.idUser;
    }
    public void setNameWallet(String nameWallet){
        this.nameWallet = nameWallet;
    }
    public String getNameWallet(){
        return this.nameWallet;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getCurrency(){
        return this.currency;
    }
    public void setBalance(float balance){
        this.balance = balance;
    }
    public float getBalance(){
        return this.balance;
    }



}
