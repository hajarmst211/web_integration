package com.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import java.net.URI;

public class Application {

    // URL de base du serveur REST
    public static final String BASE_URI = "http://localhost:9090/api/";

    /**
     * Démarre le serveur HTTP Grizzly
     */
    public static HttpServer startServer() {
        // Configuration de Jersey
        final ResourceConfig config = new ResourceConfig()
                .packages("com.example.resource")
                .property(ServerProperties.WADL_FEATURE_DISABLE, false);
        
        // Créer et démarrer le serveur
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) {
        try {
            final HttpServer server = startServer();

            System.out.println("=".repeat(60));
            System.out.println(" Serveur REST démarré avec succès!");
            System.out.println(" URL de base: " + BASE_URI);
            System.out.println("=".repeat(60));
            System.out.println("\n Endpoints disponibles pour ElectroShop:");
            System.out.println("  GET    " + BASE_URI + "sales          - Liste toutes les ventes");
            System.out.println("  GET    " + BASE_URI + "sales/{id}     - Détails d'une vente");
            System.out.println("  POST   " + BASE_URI + "sales          - Créer une vente");
            System.out.println("  PUT    " + BASE_URI + "sales/{id}     - Modifier une vente");
            System.out.println("  DELETE " + BASE_URI + "sales/{id}     - Supprimer une vente");
            System.out.println("  GET    " + BASE_URI + "sales/count    - Compter les ventes");
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Appuyez sur Entrée pour arrêter le serveur...");
            System.out.println("=".repeat(60));

            System.in.read();

            server.shutdownNow();
            System.out.println("\n Serveur arrêté.");

        } catch (Exception e) {
            System.err.println(" Erreur lors du démarrage du serveur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}