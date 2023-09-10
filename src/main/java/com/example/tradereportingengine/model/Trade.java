package com.example.tradereportingengine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Trade {
    @Id
    @GeneratedValue
    private int id;
    private String BUYERPARTY;
    private String SELLERPARTY;
    private String PREMIUMAMOUNT;
    private String PREMIUMCURRENCY;


    public Trade(String bpRefValue, String spRefValue, String currencyValue, String amountValue) {
        this.BUYERPARTY = bpRefValue;
        this.SELLERPARTY = spRefValue;
        this.PREMIUMCURRENCY = currencyValue;
        this.PREMIUMAMOUNT = amountValue;
    }
}
