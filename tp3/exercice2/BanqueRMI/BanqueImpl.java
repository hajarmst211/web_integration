import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {
    private Map<Integer, Compte> comptes = new HashMap<>();

    protected BanqueImpl() throws RemoteException {
        super();
        // Adding initial data
        comptes.put(1, new Compte(1, "Alice", 1000.0));
        comptes.put(2, new Compte(2, "Bob", 500.0));
    }

    public double consulterSolde(int id) { return comptes.get(id).solde; }
    
    public void retrait(int id, double montant) {
        Compte c = comptes.get(id);
        c.solde -= montant;
    }

    public void transfert(int idSource, int idDest, double montant) {
        retrait(idSource, montant);
        comptes.get(idDest).solde += montant;
    }

    public String getNom(int id) { return comptes.get(id).nom; }
    
    public Compte getCompte(int id) { return comptes.get(id); }
}