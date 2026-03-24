import java.io.Serializable;
public class Compte implements Serializable {
    public int id;
    public String nom;
    public double solde;
    public Compte(int id, String nom, double solde) {
        this.id = id; this.nom = nom; this.solde = solde;
    }
}
