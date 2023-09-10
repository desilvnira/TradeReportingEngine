package com.example.tradereportingengine.service;

import com.example.tradereportingengine.dao.TradeRepository;
import com.example.tradereportingengine.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeReportingService {
    @Autowired
    private TradeRepository tradeRepository;
    final private XMLParserService xmlParserService = new XMLParserService();
    public HttpStatus setXMLTrades(){
        long initSize = tradeRepository.count();
        List<Trade> trades = tradeRepository.saveAll(xmlParserService.parseXMLTrades());
        if(initSize != trades.size()){
            return HttpStatus.CREATED;
        }
        else{
            return HttpStatus.OK;
        }
    }

    public List<Trade> getfilteredTrades(){
        return tradeRepository.findFilteredTrades();
    }
}
