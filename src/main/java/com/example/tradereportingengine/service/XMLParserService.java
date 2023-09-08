package com.example.tradereportingengine.service;

import com.example.tradereportingengine.model.Trade;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParserService {
    public List<Trade> parseXMLTrades(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Trade> tradeList = new ArrayList<>();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            File dir = new File("src/main/resources/static");
            File[] directoryListing = dir.listFiles();
            for(File child : directoryListing) {
                String bpRefValue = "";
                String spRefValue = "";
                String currencyValue = "";
                String amountValue = "";
                Document document = builder.parse(child);
                document.getDocumentElement().normalize();
                NodeList bpRef = document.getElementsByTagName("buyerPartyReference");
                NodeList spRef = document.getElementsByTagName("sellerPartyReference");
                NodeList currency = document.getElementsByTagName("currency");
                NodeList amount = document.getElementsByTagName("amount");

                if (bpRef.item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Element buyerPartyReference = (Element) bpRef.item(0);
                    bpRefValue = buyerPartyReference.getAttribute("href");

                }
                if (spRef.item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Element sellerPartyReference = (Element) spRef.item(0);
                    spRefValue = sellerPartyReference.getAttribute("href");

                }
                if (currency.item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Element curr = (Element) currency.item(0);
                    currencyValue = curr.getFirstChild().getTextContent();

                }
                if (amount.item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Element am = (Element) amount.item(0);
                    amountValue = am.getFirstChild().getTextContent();

                }
                tradeList.add(new Trade(bpRefValue, spRefValue, currencyValue, amountValue));
            }


        }catch(ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
        return tradeList;

    }
}
