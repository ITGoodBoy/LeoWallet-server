package com.celestialapps.leowallet.response;

import com.celestialapps.leowallet.model.Favorite;
import com.celestialapps.leowallet.model.Invoice;
import com.celestialapps.leowallet.model.Last;
import com.celestialapps.leowallet.model.user.User;
import lombok.Data;

import java.util.List;

@Data
public class Wallet {

    private long id;
    private String account;
    private long balance;
    private List<Invoice> invoices;
    private List<Favorite> favorites;
    private List<Last> lasts;

    public Wallet(User user) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.balance = user.getBalance();
        this.invoices = user.getInvoices();
        this.favorites = user.getFavorites();
        this.lasts = user.getLasts();
    }
}
