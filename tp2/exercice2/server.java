import java.io.*;
import java.net.*;

public class server{
    public static void main(String[] args) throws IOException{
        ServerSocket server_Socket = new ServerSocket(8000);
        System.out.printf("server started...\n");

        while(true){
            Socket client = server_Socket.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            String corps = "<HTML><TITLE>Mon serveur</TITLE>Cette page a été envoyée par<B>Serveur</B></HTML>";

            out.println("HTTP/1.0 200 OK\n\n" + corps);

            client.close();
        }
    }
}