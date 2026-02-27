class increment_int implements Runnable{
    public int final_int;

    public void run(){
        for(int compteur_int = 0; compteur_int < 10; compteur_int++){
            System.out.println("Comptuer int is at:"+ compteur_int);
            final_int ++;
        }
    }

    public int getResult(){
        return final_int;
    }
}

class increment_char implements Runnable{
    public char final_char;

    public void run(){
        for (char character = 'a'; character <= 'z'; character ++){
            System.out.println("the charcter now is:"+ character);
            final_char = character;
        }
    }

    public char getResult(){
        return final_char;
    }
}

public class Exercice1 {
    public static void main(String[] args) {
        
        increment_int task1 = new increment_int();
        increment_char task2 = new increment_char();

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("The result of thread 1 is: "+ thread1.getResult());
        System.out.println("The result of thread 2 is: "+ thread2.getResult());
    }

}
