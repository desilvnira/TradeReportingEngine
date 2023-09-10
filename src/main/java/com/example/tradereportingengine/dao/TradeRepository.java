package com.example.tradereportingengine.dao;

import com.example.tradereportingengine.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
    @Query(nativeQuery = true, value  = "SELECT * FROM Trade t WHERE t.BUYERPARTY != t.SELLERPARTY AND " +
            "((t.SELLERPARTY = 'EMU_BANK' AND t.PREMIUMCURRENCY = 'AUD') OR (t.SELLERPARTY = 'BISON_BANK' AND t.PREMIUMCURRENCY = 'USD'))")
    List<Trade> findFilteredTrades();
}
