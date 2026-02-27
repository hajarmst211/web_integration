import java.util.LinkedList;


class File{
    final int capacity = 10;
    LinkedList<Integer> product_list = new LinkedList<Integer>();


    public synchronized void ajouter(int product_to_add)throws InterruptedException{
        while(product_list.size() >= capacity){
            wait();
        }

        product_list.add(product_to_add);
        notifyAll();
        System.out.print("Le produit a ete ajoute\n");
    } 


    public synchronized void retirer() throws InterruptedException{
        while(product_list.size() == 0){
            wait();
        }
        product_list.removeFirst();
        System.out.print("Le produit a ete supprime\n");
        notifyAll();
    }
    
}


class Consomatteur implements Runnable{
    File product_file;

    public Consomatteur(File product_file){this.product_file = product_file;}

    public void run(){
        for(int i = 0 ; i<10 ; i++){
            try{
                product_file.retirer();
                Thread.sleep(20);
                System.out.print("LE consomatteur a retirer un produit\n");
            }
            catch(InterruptedException e){
                System.out.println("Error du consommateur");
            }
        }
    }
}


class Producteur implements Runnable{
    File product_file;

    public Producteur(File product_file){this.product_file = product_file;}

    public void run(){
        for(int i = 0 ; i<10 ; i++){
            try{
                product_file.ajouter(i);
                Thread.sleep(20);
                System.out.print("Le producteur a ajouter un produit\n");
            }
            catch(InterruptedException e){
                System.out.println("Error du consommateur");
            }
        }
    }

}

public class Exercice3{
    public static void main(String args[])throws InterruptedException {
        File file_produits = new File();

        Consomatteur client = new Consomatteur(file_produits);
        Producteur producteur = new Producteur(file_produits);

        Thread thread_consomattuer = new Thread(client);
        Thread thread_producteur = new Thread(producteur);

        thread_consomattuer.join();
        thread_producteur.join();
    }
}