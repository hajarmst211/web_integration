import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFibonacci extends Remote {
    int calculerFibonacci(int n) throws RemoteException;
}