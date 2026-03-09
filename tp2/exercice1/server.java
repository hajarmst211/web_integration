import java.io.*;
import java.net.*;
import java.util.*;

public class server {
    public static void main(String args[])throws IOException {
        int PORT = 6666;
        System.out.printf("Server starting....\n");
        ServerSocket server_socket = new ServerSocket(PORT);
        System.out.printf("Server sttart listening on port 6000\n");

        while(true){
           Socket socket =  server_socket.accept();
            new ClientHandler(socket).start();;
        }
    } 
}


class ClientHandler extends Thread{
    private Socket socket;

    public ClientHandler(Socket socket){this.socket = socket;}

    public void run(){
        try{

            DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
            List<String> proverbsList = new ArrayList<>();
            File file = new File("proverbs.txt");
            BufferedReader buffer = new BufferedReader(new FileReader(file));

            String line;
            while((line = buffer.readLine()) != null){
                proverbsList.add(line);
            }

            String randomProverb = proverbsList.get(new Random().nextInt(proverbsList.size()));
            data_out.writeUTF(randomProverb);

            buffer.close();
            socket.close();
        }catch (Exception e){
            System.out.printf("System error: %v\n" , e);
        }

    }



}