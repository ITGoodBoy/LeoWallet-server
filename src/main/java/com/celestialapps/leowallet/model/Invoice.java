package com.celestialapps.leowallet.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String account;

    public Invoice(String name, String account) {
        this.name = name;
        this.account = account;
    }
}


