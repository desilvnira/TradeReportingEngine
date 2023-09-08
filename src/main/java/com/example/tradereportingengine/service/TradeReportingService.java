package com.example.tradereportingengine.service;

import com.example.tradereportingengine.model.Trade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeReportingService {
    final private XMLParserService xmlParserService = new XMLParserService();
    public List<Trade> getXMLTrades(){
        return xmlParserService.parseXMLTrades();
    }

    public List<Trade> getfilteredTrades(List<Trade> trades){
        List<Trade> filteredTrades = new ArrayList<>();
        for(Trade trade: trades){
            if(!trade.getBuyerParty().equals(trade.getSellerParty())) {
                if (trade.getSellerParty().equals("EMU_BANK") && trade.getPremiumCurrency().equals("AUD")) {
                    filteredTrades.add(trade);
                } else if (trade.getSellerParty().equals("BISON_BANK") && trade.getPremiumCurrency().equals("USD")) {
                    filteredTrades.add(trade);
                }
            }
        }
        return filteredTrades;
    }
}
