package com.example.tradereportingengine.dao;

import com.example.tradereportingengine.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
