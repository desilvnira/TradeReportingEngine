package com.example.tradereportingengine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Trade {
    @Id
    @GeneratedValue
    private int id;
    private String buyerParty;
    private String sellerParty;
    private String premiumAmount;
    private String premiumCurrency;


    public Trade(String bpRefValue, String spRefValue, String currencyValue, String amountValue) {
        buyerParty = bpRefValue;
        sellerParty = spRefValue;
        premiumCurrency = currencyValue;
        premiumAmount = amountValue;
    }
}
