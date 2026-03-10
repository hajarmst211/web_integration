import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ChatClient extends JFrame {
    private PrintWriter out;
    private JTextArea area = new JTextArea(20, 40);
    private JTextField input = new JTextField(40);

    public ChatClient() {
        setLayout(new BorderLayout());
        add(new JScrollPane(area), BorderLayout.CENTER);
        add(input, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        String pseudo = JOptionPane.showInputDialog("Entrez votre pseudo :");
        try {
            Socket socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(pseudo);

            // Thread pour écouter le serveur
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;
                    while ((msg = in.readLine()) != null) area.append(msg + "\n");
                } catch (IOException e) { area.append("Déconnecté.\n"); }
            }).start();

            // Envoi de message via Enter
            input.addActionListener(e -> {
                out.println(input.getText());
                input.setText("");
            });
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) { new ChatClient(); }
}