import java.util.concurrent.locks.ReentrantLock;

class Compte{
    private int solde = 1002;
    private final ReentrantLock lock = new ReentrantLock();

    //public synchronized void retirer(int montant){
    public void retirer(int montant){
        lock.lock();
        solde -= montant;
        lock.unlock();
    }
    public synchronized int getSolde(){
        return solde;
    }
}

class JobAhmedEtFatima implements Runnable{
    private Compte compte_ahmed_fatima = new Compte();
    
    public void doRetrait(int montant){
        int current_solde = compte_ahmed_fatima.getSolde();
        String name = Thread.currentThread().getName();

        if (current_solde >= montant){
            System.out.println(name +" va retirer");
            compte_ahmed_fatima.retirer(montant);
            System.out.println("Le nouveau solde est "+compte_ahmed_fatima.getSolde());
        }
        else
            System.out.println("On ne peut pas retirer le solde est inferieur au montant");
    }

    public void run(){
        for(int i = 2; i<10 ; i++)
            doRetrait(12);
    }
}


public class Exercice2 {
        public static void main(String[] args) throws InterruptedException {
            JobAhmedEtFatima job = new JobAhmedEtFatima();

            Thread thread_ahmed = new Thread(job);
            Thread thread_fatima = new Thread(job);

            thread_ahmed.setName("Ahmed");
            thread_fatima.setName("Fatima");

            thread_ahmed.start();
            thread_fatima.start();
    }
}
