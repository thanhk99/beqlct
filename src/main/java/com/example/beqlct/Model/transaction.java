package com.example.beqlct.Model;

import jakarta.persistence.*;

@Entity
@Table(name="transaction")
public class transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private float amount;
    private String time;
    private String spent;
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_wallet")
    private int idWallet;
    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private userModel user;

    @ManyToOne
    @JoinColumn(name = "id_wallet" , insertable =  false, updatable = false)
    private wallet wallet;

    // Getters and Setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }
    public int getIdWallet() {
        return idWallet;
    }
    public void setSpent( String spent) {
        this.spent = spent;
    }
    public String getSpent() {
        return spent;
    }
}