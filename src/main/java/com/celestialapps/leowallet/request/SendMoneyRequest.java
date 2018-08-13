package com.celestialapps.leowallet.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMoneyRequest {

    private String accountTo;
    private long moneyCount;
}
