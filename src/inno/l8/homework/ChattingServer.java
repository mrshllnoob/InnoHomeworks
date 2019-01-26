package inno.l8.homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ChattingServer {

    private static final int PORT = 8189;
    private LinkedList<ClientConnection> connections = new LinkedList<>();


    private ChattingServer() throws IOException {
        try (ServerSocket ss = new ServerSocket(PORT)) {
            while (true) {
                Socket conn = ss.accept();
                System.out.println("Client connected: " + conn.getInetAddress() + " " + conn.getPort());
                ClientConnection clientConnection = new ClientConnection(ChattingServer.this, conn);
                connections.add(clientConnection);
                Thread clientTask = new Thread(clientConnection);
                clientTask.start();
            }
        }
    }

    public synchronized void sendMsgToAll(String value) {
        final int connsCount = this.connections.size();
        System.out.println("kek");
        for (int i = 0; i<connsCount;i++)
            connections.get(i).sendMessage(value);
    }

    public static void main(String[] args) {
        try {
            new ChattingServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
