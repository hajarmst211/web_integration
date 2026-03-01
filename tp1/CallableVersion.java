import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Task implements Callable<List<String>>{
    private final int start;
    private final int end;
    
    public Task(int start, int end){
        this.start= start;
        this.end= end;
    }


    public List<String> call(){
        List<String> results = new ArrayList<String>();
        for(int i = start; i<end; i++){
            results.add("sqrt of:"+ i+" is :"+ Math.sqrt(i));
        }
        return results;
    }
}

public class CallableVersion {

    public static void main(){
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<List<String>>> futures_list = new ArrayList<>();
        int total =5000;
        int step = 100;

        for(int i =0 ; i<total ; i++){
            int start = i;
            int end = Math.min(i+step, total+1);
            Task new_task = new Task(start,end);
            Future<List<String>> future = executor.submit(new_task);
            futures_list.add(future);
        }

        for (Future<List<String>>  future :futures_list){
            try {
                List<String> results = future.get();
                for (String result : results)
                    System.out.println(result);
                }
            catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        long endTime = System.currentTimeMillis();
        System.out.println("Temps d'exécution Runnable : " + (endTime - startTime) + " ms");

    } 
}
