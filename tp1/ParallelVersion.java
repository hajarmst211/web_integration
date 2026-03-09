import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


class  PowerOfTwo implements Runnable{
    private final int number;

    public PowerOfTwo(int number){
        this.number = number;
    }

    public void run(){
        String thread_name = Thread.currentThread().getName();
        
        System.out.printf("%s: The power of %d is:%d \n", thread_name, number, Math.sqrt(number));
    }
}


class ParallelVersion{
    public static void main (String args[]){
        ExecutorService pool = Executors.newFixedThreadPool(5);
        long startTime = System.currentTimeMillis();
        
        for (int i =0; i<5000; i++){
            pool.execute(new PowerOfTwo(i));
        }

        pool.shutdown();        
        long endTime = System.currentTimeMillis();
        System.out.println("All tasks are done");
        System.out.println("Temps d'exécution Runnable : " + (endTime - startTime) + " ms");
    }
}