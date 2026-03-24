import java.rmi.*;
import java.util.*;

public interface IBanque extends Remote {
    double consulterSolde(int id) throws RemoteException;
    void retrait(int id, double montant) throws RemoteException;
    void transfert(int idSource, int idDest, double montant) throws RemoteException;
    String getNom(int id) throws RemoteException;
    Compte getCompte(int id) throws RemoteException;
}