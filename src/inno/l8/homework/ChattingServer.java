package inno.l8.homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Класс чат-сервера.
 *
 * @author Tsapin Anton
 */
public class ChattingServer {

    private static final int PORT = 8189;
    private LinkedList<ClientConnection> connections = new LinkedList<>();

    /**
     * Открывает клиентские соединения и добавляет их в список
     * @throws IOException
     */
    private ChattingServer() throws IOException {
        try (ServerSocket ss = new ServerSocket(PORT)) {
            while (true) {
                Socket conn = ss.accept();
                System.out.println("Client connected: " + conn.getInetAddress() + " " + conn.getPort());
                ClientConnection clientConnection = new ClientConnection(ChattingServer.this, conn);
                getConnections().add(clientConnection);
                Thread clientTask = new Thread(clientConnection);
                clientTask.start();
            }
        }
    }

    /**
     * Метод отправляет полученное сервером сообщение
     * всем текущим клиентам.
     *
     * @param value сообщение
     */
    public synchronized void sendMsgToAll(String value) {
        final int connsCount = this.getConnections().size();
        System.out.println(value);
        for (int i = 0; i<connsCount;i++)
            getConnections().get(i).sendMessage(value);
    }

    public static void main(String[] args) {
        try {
            new ChattingServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<ClientConnection> getConnections() {
        return connections;
    }
}
