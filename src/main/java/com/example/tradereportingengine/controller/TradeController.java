package com.example.tradereportingengine.controller;

import com.example.tradereportingengine.dao.TradeRepository;
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
    private TradeRepository tradeRepository;

    @Autowired
    private TradeReportingService tradeReportingService;

    @PostMapping("/setTrades")
    public String saveTrades(){
        List<Trade> reports = tradeReportingService.getXMLTrades();
        tradeRepository.saveAll(reports);
        return "saved...";
    }

    @GetMapping("/getTrades")
    public List<Trade> getTrades(){
        List<Trade> trades = tradeRepository.findAll();
        return tradeReportingService.getfilteredTrades(trades);
    }
}
