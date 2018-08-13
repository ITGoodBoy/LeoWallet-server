package com.celestialapps.leowallet.controller;

import com.celestialapps.leowallet.constants.HeaderConstants;
import com.celestialapps.leowallet.model.Transaction;
import com.celestialapps.leowallet.model.user.User;
import com.celestialapps.leowallet.request.PromotionalCodeRequest;
import com.celestialapps.leowallet.request.ReceiveMoneyRequest;
import com.celestialapps.leowallet.request.SendMoneyRequest;
import com.celestialapps.leowallet.request.UpBalanceRequest;
import com.celestialapps.leowallet.response.Balance;
import com.celestialapps.leowallet.response.Wallet;
import com.celestialapps.leowallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/wallet")
public class WalletController {

    private UserService userService;

    @Autowired
    public WalletController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/my-wallet", headers = HeaderConstants.HEADER_TOKEN)
    public ResponseEntity<Wallet> getMyWallet() {
        User user = userService.getLoggedUser();

        if (user != null) {
            user = userService.findById(user.getId());
        }

        Wallet wallet = new Wallet(user);

        return ResponseEntity.ok(wallet);
    }

    @PutMapping(value = "/up-balance",headers = HeaderConstants.HEADER_TOKEN)
    public ResponseEntity<Balance> upBalance(@RequestBody UpBalanceRequest upBalanceRequest) {
        User user = userService.getLoggedUser();
        Balance balance = null;

        if (user != null) {
            balance = userService.upBalance(user, upBalanceRequest);
        }

        return ResponseEntity.ok(balance);
    }

    @PutMapping(value = "/send-money", headers = HeaderConstants.HEADER_TOKEN)
    public ResponseEntity<Balance> sendMoney(@RequestBody SendMoneyRequest sendMoneyRequest) {
        User user = userService.getLoggedUser();
        Balance balance = null;

        if (user != null) {
            balance = userService.sendMoney(user, sendMoneyRequest);
        }

        return ResponseEntity.ok(balance);
    }

    @PutMapping(value = "/receive-money", headers = HeaderConstants.HEADER_TOKEN)
    public ResponseEntity<Balance> receiveMoney(@RequestBody ReceiveMoneyRequest receiveMoneyRequest) {
        User user = userService.getLoggedUser();
        Balance balance = null;

        if (user != null) {
            balance = userService.receiveMoney(user, receiveMoneyRequest);
        }

        return ResponseEntity.ok(balance);
    }

    @PutMapping(value = "/enter-promotional-code", headers = HeaderConstants.HEADER_TOKEN)
    public ResponseEntity<Balance> enterPromotionCode(@RequestBody PromotionalCodeRequest promotionalCodeRequest) {
        User user = userService.getLoggedUser();
        Balance balance = null;

        if (user != null) {
            balance = userService.enterPromotionalCode(user, promotionalCodeRequest);
        }

        return ResponseEntity.ok(balance);
    }

    @GetMapping(value = "/get-transactions", headers = HeaderConstants.HEADER_TOKEN)
    public ResponseEntity<List<Transaction>> getTransactions() {
        User user = userService.getLoggedUser();
        List<Transaction> transactions = null;

        if (user != null) {
            transactions = userService.getMyTransactions(user);
        }

        return ResponseEntity.ok(transactions);
    }



}
