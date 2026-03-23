package com.example;

import com.example.client.ICalculatrice;
import com.example.client.CalculatriceImpService;
public class Main {
 public static void main(String[] args) {
 System.out.println("Hello world!");
 CalculatriceImpService service = new CalculatriceImpService() ;
 ICalculatrice port = service.getCalculatriceImpServicePort();
 double sum = port.add(50,40);
  System.out.println("la somme est :"+sum);
 }
}