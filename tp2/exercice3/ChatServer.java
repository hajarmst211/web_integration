import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(12345);
        System.out.println("Serveur lancé sur le port 12345...");
        while (true) {
            new ClientHandler(listener.accept()).start();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) { this.socket = socket; }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientWriters) { clientWriters.add(out); }

                String pseudo = in.readLine();
                broadcast("Serveur: " + pseudo + " a rejoint le chat.");

                String message;
                while ((message = in.readLine()) != null) {
                    broadcast(pseudo + ": " + message);
                }
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    private static void broadcast(String message) {
        for (PrintWriter writer : clientWriters) writer.println(message);
    }
}