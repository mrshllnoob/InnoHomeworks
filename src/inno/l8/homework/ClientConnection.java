package inno.l8.homework;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;

/**
 * Класс клиетского подключения к серверу-чату.
 *
 * @author Tsapin Anton
 */
public class ClientConnection implements Runnable {

    private Socket socketConnection;
    private ChattingServer serverLink;
    private PrintWriter output;
    private String nickname;

    /**
     * Контруктор ClientConnection
     * @param serverLink ссылка на экземпляр объекта класса ChattingServer
     * @param conn сокетное соединение между клиентом и сервером
     * @throws IOException для OutputStreamWriter
     */
    public ClientConnection(ChattingServer serverLink, Socket conn) throws IOException {
        this.socketConnection = conn;
        this.serverLink = serverLink;
        OutputStreamWriter osw = new OutputStreamWriter(socketConnection.getOutputStream());
        this.output = new PrintWriter(osw, true);
    }

    /**
     * Открывает потоки ввода и вывода. Принимает сообщения и
     * сразу же отправляет полученное сообщение всем клиентам.
     * При закрытии соединения со стороны клиента или обрыве соединения
     * закрывает сокет и удаляет клиентское подключение из листа, соде-
     * ржащего сокеты клиентов
     */
    @Override
    public void run() {
        try(InputStreamReader isr = new InputStreamReader(socketConnection.getInputStream());
                BufferedReader input = new BufferedReader(isr)) {
            boolean isFirstMessage = true;
            boolean isDone = false;
            while(!isDone) {
                try {
                    String line = input.readLine();
                    if (isFirstMessage) {
                        nickname = line;
                        output.write("Nickname " + nickname + " added\n");
                        output.flush();
                        isFirstMessage = false;
                        continue;
                    }
                    if (line.equals("quit")) {
                        System.out.println("Connection closed on " + socketConnection.getInetAddress() + " " +
                                socketConnection.getPort());
                        isDone = true;
                        socketConnection.close();
                        break;
                    }
                    this.serverLink.sendMsgToAll(nickname + ": " + line);
                } catch (NoSuchElementException e) {
                    socketConnection.close();
                    System.out.println("Connection closed on " + socketConnection.getInetAddress() + " " +
                                            socketConnection.getPort());
                    socketConnection.close();
                    serverLink.getConnections().remove(this);
                }
            }
            if (!socketConnection.isClosed()) {
                socketConnection.close();
                serverLink.getConnections().remove(this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправка сообщения от сервера к клиенту
     * @param value сообщение
     */
    public void sendMessage(String value) {
        output.write(value + "\n");
        output.flush();
    }
}
