package inno.l8.homework;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Клиент для серверного чата.
 *
 * @author Tsapin Anton
 */
public class ChattingClient {

    private static final String IPADDR = "localhost";
    private static final int PORT = 8189;

    private ChattingClient() {

        try (Socket conn = new Socket(IPADDR, PORT);
             Scanner clInput = new Scanner(System.in);
             OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
             PrintWriter outputToServer = new PrintWriter(osw, true);
             PrintWriter clOutput = new PrintWriter(System.out, true);
             InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
             BufferedReader inputFromServer = new BufferedReader(isr)) {

            new Thread() {
                @Override
                public void run() {
                    while (!conn.isClosed()) {
                        try {
                            clOutput.write(inputFromServer.readLine() + "\n");
                            clOutput.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            boolean isDone = false;
            System.out.println("Ready to send");
            while (!isDone) {
                String line = clInput.nextLine();
                if (line.trim().equals("quit")) {
                    isDone = true;
                }
                outputToServer.write(line + "\n");
                outputToServer.flush();
            }

        } catch (UnsupportedEncodingException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChattingClient();
    }

}
