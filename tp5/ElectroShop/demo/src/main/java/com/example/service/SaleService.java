package com.example.service;

import com.example.model.Sale;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SaleService {

    private static SaleService instance;
    private Map<Long, Sale> sales = new ConcurrentHashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    // Singleton
    private SaleService() {
        // Initialiser avec quelques ventes d'exemple
        addSale(new Sale(null, "2026-03-20", "Laptop HP", 2, 5500.0, 0, "Ali Benjelloun", "Rabat"));
        addSale(new Sale(null, "2026-03-21", "iPhone 15", 1, 12000.0, 0, "Sara Laamiri", "Casablanca"));
        addSale(new Sale(null, "2026-03-22", "Samsung Galaxy S24", 3, 9500.0, 0, "Omar Tazi", "Fès"));
        addSale(new Sale(null, "2026-03-23", "Tablette iPad", 2, 4500.0, 0, "Fatima Zahra", "Marrakech"));
    }

    public static synchronized SaleService getInstance() {
        if (instance == null) {
            instance = new SaleService();
        }
        return instance;
    }

    // GET ALL - Récupérer toutes les ventes
    public List<Sale> getAllSales() {
        return new ArrayList<>(sales.values());
    }

    // GET BY ID - Récupérer une vente par ID
    public Sale getSaleById(Long id) {
        return sales.get(id);
    }

    // POST - Ajouter une nouvelle vente
    public Sale addSale(Sale sale) {
        // Générer l'ID si absent
        if (sale.getId() == null) {
            sale.setId(idCounter.getAndIncrement());
        }
        // Calculer le total (quantity * price)
        double total = sale.getQuantity() * sale.getPrice();
        sale.setTotal(total);
        
        sales.put(sale.getId(), sale);
        System.out.println("Vente ajoutée: " + sale);
        return sale;
    }

    // PUT - Mettre à jour une vente
    public Sale updateSale(Long id, Sale sale) {
        if (!sales.containsKey(id)) {
            return null;
        }
        sale.setId(id);
        // Recalculer le total
        double total = sale.getQuantity() * sale.getPrice();
        sale.setTotal(total);
        
        sales.put(id, sale);
        System.out.println("Vente mise à jour: " + sale);
        return sale;
    }

    // DELETE - Supprimer une vente
    public boolean deleteSale(Long id) {
        if (sales.containsKey(id)) {
            sales.remove(id);
            System.out.println("Vente supprimée avec ID: " + id);
            return true;
        }
        return false;
    }

    // COUNT - Compter le nombre de ventes
    public int countSales() {
        return sales.size();
    }
}