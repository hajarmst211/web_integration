import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IBanque stub = (IBanque) registry.lookup("Banque");
            
            System.out.println("Solde Alice: " + stub.consulterSolde(1));
            stub.retrait(1, 100);
            System.out.println("Nouveau solde Alice: " + stub.consulterSolde(1));
        } catch (Exception e) { e.printStackTrace(); }
    }
}