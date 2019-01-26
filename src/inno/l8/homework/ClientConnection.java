package inno.l8.homework;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ClientConnection implements Runnable {

    private Socket socketConnection;
    private ChattingServer serverLink;
    private PrintWriter output;

    public ClientConnection(ChattingServer serverLink, Socket conn) throws IOException {
        this.socketConnection = conn;
        this.serverLink = serverLink;
        OutputStreamWriter osw = new OutputStreamWriter(socketConnection.getOutputStream());
        this.output = new PrintWriter(osw, true);
    }

    @Override
    public void run() {
        try(InputStreamReader isr = new InputStreamReader(socketConnection.getInputStream());
                Scanner input = new Scanner(isr)) {
            boolean isFirstMessage = true;
            boolean isDone = false;
            String nickname = "";
            while(!isDone) {
                try {
                    String line = input.nextLine();
                    if (isFirstMessage) {
                        nickname = line;
                        output.write("Nickname " + nickname + " added\n");
                        output.flush();
                        System.out.println(nickname);
                        continue;
                    }
                    if (line.equals("quit")) {
                        socketConnection.close();
                        break;
                    }
                    this.serverLink.sendMsgToAll(nickname + ": " + input.nextLine());
                } catch (NoSuchElementException e) {
                    System.out.println("Connection closed on " + socketConnection.getInetAddress() +
                                            socketConnection.getPort());
                    socketConnection.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String value) {
        output.write(value + "\n");
        output.flush();
    }
}
