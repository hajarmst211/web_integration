import java.io.*;
import java.net.*;
import java.util.*;

public class server{
    public static void main(String[] args) throws IOException{
        ServerSocket server_Socket = new ServerSocket(8000);
        System.out.printf("server started...\n");
        while(true){
            Socket socket = server_Socket.accept();
            ClientHandler handler = new ClientHandler(socket);
            Thread client_Thread = new Thread(handler);
            client_Thread.start();
        }
    }
}

class ClientHandler implements Runnable{
    private Socket socket;

    public ClientHandler(Socket socket){this.socket = socket;}

    public void run(){
        try{
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String corps = "<HTML><TITLE>Mon serveur</TITLE>Cette page a été envoyée par<B>Serveur</B></HTML>";

            out.println("HTTP/1.0 200 OK\n\n" + corps);

            socket.close();
        }catch (IOException e){
            System.out.print(e);
        }
    }

}