import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FibonacciImpl extends UnicastRemoteObject implements IFibonacci {
    
    protected FibonacciImpl() throws RemoteException {
        super();
    }

    @Override
    public int calculerFibonacci(int n) throws RemoteException {
        // Choose the Iterative approach here for better performance
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}