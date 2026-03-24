import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "security.policy");
            if (System.getSecurityManager() == null) System.setSecurityManager(new SecurityManager());
            
            BanqueImpl obj = new BanqueImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Banque", obj);
            System.out.println("Serveur pret...");
        } catch (Exception e) { e.printStackTrace(); }
    }
}