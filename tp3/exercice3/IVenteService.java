import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVenteService extends Remote {
    void ajouterVente(Vente v) throws RemoteException;
    double calculerCATotal() throws RemoteException;
    double calculerCAParRegion(String region) throws RemoteException;
    String getProduitPlusVendu() throws RemoteException;
    List<Vente> getVentesSuperieuresA(double montant) throws RemoteException;
    List<Vente> getAllVentes() throws RemoteException;
}