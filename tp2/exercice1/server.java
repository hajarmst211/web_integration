import java.io.*;
import java.net.*;

public class server {
    public static void main(String args[]){
        int PORT = 6666;
        try{
            System.out.printf("Server starting....\n");
            ServerSocket server_socket = new ServerSocket(PORT);
            System.out.printf("Server sttart listening on port 6000\n");

            Socket s = server_socket.accept();

            DataOutputStream data_out = new DataOutputStream(s.getOutputStream());
            
            File file = new File("proverbs.txt");
            BufferedReader buffer = new BufferedReader(new FileReader(file));

            String line;
            while((line = buffer.readLine()) != null){
                data_out.writeUTF(line);
            }

            buffer.close();
            server_socket.close();

        }catch (Exception e){
            System.out.printf("System error: %v\n" , e);
        }
    } 
}
