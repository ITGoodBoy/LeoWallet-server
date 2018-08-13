package com.celestialapps.leowallet.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date date;
    private long count;
    private String senderAccount;
    private String recipientAccount;

    public Transaction(long count, String senderAccount, String recipientAccount) {
        this.date = new Date(System.currentTimeMillis());
        this.count = count;
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
    }
}
