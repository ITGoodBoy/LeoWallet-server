package com.celestialapps.leowallet.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PromotionalCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String code;
    private long moneyCount;

    public PromotionalCode(String code, long moneyCount) {
        this.code = code;
        this.moneyCount = moneyCount;
    }
}
