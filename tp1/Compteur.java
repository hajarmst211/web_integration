

class increment_int implements Runnable{
    public void run(){
        for(int compteur_int = 0; compteur_int < 10; compteur_int++){
            System.out.println("Comptuer int is at:"+ compteur_int);
        }
    }
}

class increment_char implements Runnable{
    public void run(){
        for (char character = 'a'; character <= 'z'; character ++)
            System.out.println("the charcter now is:"+ character);
        }
}

public class Compteur {
    public static void main(String[] args) {
        
        for (int i=0; i<7 ; i++){System.out.print("_");}
        System.out.print("Exercice 1:");
        for (int i=0; i<7 ; i++){System.out.print("_");}

        increment_int task1 = new increment_int();
        increment_char task2 = new increment_char();

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }

}
