package com.celestialapps.leowallet.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Last {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long serviceId;
    private String name;
    private String account;

    public Last(long serviceId, String name, String account) {
        this.serviceId = serviceId;
        this.name = name;
        this.account = account;
    }
}
