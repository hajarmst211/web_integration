package com.banque.publisher;

import com.banque.service.BanqueService;

import javax.xml.ws.Endpoint;

public class BanquePublisher {

    public static void main(String[] args) {
        String url = "http://localhost:9001/banque";
        Endpoint.publish(url, new BanqueService());
        System.out.println("Banque Web Service is running at: " + url + "?wsdl");
    }
}