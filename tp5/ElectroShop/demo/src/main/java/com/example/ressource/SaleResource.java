package com.example.ressource;

import com.example.model.Sale;
import com.example.service.SaleService;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/sales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SaleResource {
    
    private SaleService saleService = SaleService.getInstance();

    // GET /sales - Lister toutes les ventes
    @GET
    public Response getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return Response.ok(sales).build();
    }

    // GET /sales/{id} - Récupérer une vente par ID
    @GET
    @Path("/{id}")
    public Response getSaleById(@PathParam("id") Long id) {
        Sale sale = saleService.getSaleById(id);
        if (sale != null) {
            return Response.ok(sale).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Vente avec l'ID " + id + " non trouvée\"}")
                    .build();
        }
    }

    // POST /sales - Ajouter une vente
    @POST
    public Response createSale(Sale sale) {
        // Validation des champs obligatoires
        if (sale.getProduct() == null || sale.getProduct().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Le nom du produit est obligatoire\"}")
                    .build();
        }
        if (sale.getCustomer() == null || sale.getCustomer().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Le nom du client est obligatoire\"}")
                    .build();
        }
        if (sale.getQuantity() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"La quantité doit être supérieure à 0\"}")
                    .build();
        }
        
        Sale created = saleService.addSale(sale);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    // PUT /sales/{id} - Mettre à jour une vente
    @PUT
    @Path("/{id}")
    public Response updateSale(@PathParam("id") Long id, Sale sale) {
        Sale updated = saleService.updateSale(id, sale);
        
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Vente non trouvée\"}")
                    .build();
        }
        
        return Response.ok(updated).build();
    }

    // DELETE /sales/{id} - Supprimer une vente
    @DELETE
    @Path("/{id}")
    public Response deleteSale(@PathParam("id") Long id) {
        boolean deleted = saleService.deleteSale(id);
        
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Vente non trouvée\"}")
                    .build();
        }
        
        return Response.ok("{\"message\": \"Vente supprimée avec succès\"}")
                .build();
    }

    // GET /sales/count - Retourner le nombre total de ventes
    @GET
    @Path("/count")
    public Response countSales() {
        int count = saleService.countSales();
        return Response.ok("{\"count\": " + count + "}").build();
    }
}