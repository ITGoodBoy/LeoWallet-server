package com.celestialapps.leowallet.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Favorite {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long serviceId;
    private String name;
    private String account;

    public Favorite(long serviceId, String name, String account) {
        this.serviceId = serviceId;
        this.name = name;
        this.account = account;
    }
}
