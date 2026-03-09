import java.io.*;
import java.net.*;

public class MyClient {
    public static void main(String[] args) {
        try{
        int PORT = 6666;
        Socket client_socket = new Socket("localhost", PORT);
        DataInputStream client_data = new DataInputStream(client_socket.getInputStream());

        String message;
        while(true){
            message = client_data.readUTF();
            if(message.equals("EOF")) break;
            System.out.println("From server: " + message);
        }
        client_socket.close(); 

        }catch (Exception e){
            System.out.print(e);
        }
    }
}