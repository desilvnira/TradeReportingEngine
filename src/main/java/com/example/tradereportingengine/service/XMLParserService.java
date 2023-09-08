package com.example.tradereportingengine.service;

import com.example.tradereportingengine.model.Trade;
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
import java.util.*;

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
                List<String> tags = Arrays.asList("buyerPartyReference", "sellerPartyReference", "currency", "amount");
                Map<String, NodeList> tagsMap = getElementsbyTag(tags, document);

                if (tagsMap.get("buyerPartyReference").item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Element buyerPartyReference = (Element) tagsMap.get("buyerPartyReference").item(0);
                    bpRefValue = buyerPartyReference.getAttribute("href");

                }
                if (tagsMap.get("sellerPartyReference").item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Element sellerPartyReference = (Element) tagsMap.get("sellerPartyReference").item(0);
                    spRefValue = sellerPartyReference.getAttribute("href");

                }
                if (tagsMap.get("currency").item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Element curr = (Element) tagsMap.get("currency").item(0);
                    currencyValue = curr.getFirstChild().getTextContent();

                }
                if (tagsMap.get("amount").item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Element am = (Element) tagsMap.get("amount").item(0);
                    amountValue = am.getFirstChild().getTextContent();

                }
                tradeList.add(new Trade(bpRefValue, spRefValue, currencyValue, amountValue));
            }


        }catch(ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
        return tradeList;

    }

    public Map<String, NodeList> getElementsbyTag(List<String> tags, Document doc){
        Map<String, NodeList> elementMap = new HashMap<>();
        for(String tag: tags){
            elementMap.put(tag, doc.getElementsByTagName(tag));
        }
        return elementMap;
    }
}
