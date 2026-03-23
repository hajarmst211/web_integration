package com.banque.service;

import com.banque.model.Compte;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebService(serviceName = "BanqueWS")
public class BanqueService {

    private static Map<Integer, Compte> comptes = new HashMap<>();

    static {
        comptes.put(1, new Compte(1, 5000, new Date()));
        comptes.put(2, new Compte(2, 2500, new Date()));
        comptes.put(3, new Compte(3, 10000, new Date()));
    }

    @WebMethod
    public double consulterSolde(@WebParam(name = "id") int id) {
        Compte compte = comptes.get(id);
        if (compte != null) {
            return compte.getSolde();
        }
        throw new RuntimeException("Compte non trouve");
    }

    @WebMethod
    public double retrait(@WebParam(name = "id") int id,
                          @WebParam(name = "montant") double montant) {
        Compte compte = comptes.get(id);
        if (compte != null) {
            if (compte.getSolde() >= montant) {
                compte.setSolde(compte.getSolde() - montant);
                return compte.getSolde();
            }
            throw new RuntimeException("Solde insuffisant");
        }
        throw new RuntimeException("Compte non trouve");
    }

    @WebMethod
    public double depot(@WebParam(name = "id") int id,
                        @WebParam(name = "montant") double montant) {
        Compte compte = comptes.get(id);
        if (compte != null) {
            compte.setSolde(compte.getSolde() + montant);
            return compte.getSolde();
        }
        throw new RuntimeException("Compte non trouve");
    }

    @WebMethod
    public Compte getCompte(@WebParam(name = "id") int id) {
        return comptes.get(id);
    }
}