package com.example.tradereportingengine.controller;

import com.example.tradereportingengine.model.Trade;
import com.example.tradereportingengine.service.TradeReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    private TradeReportingService tradeReportingService;

    @PostMapping("/setTrades")
    public String saveTrades(){
        tradeReportingService.setXMLTrades();
        return "saved trades";
    }

    @GetMapping("/getTrades")
    public List<Trade> getTrades(){
        return tradeReportingService.getfilteredTrades();
    }
}
