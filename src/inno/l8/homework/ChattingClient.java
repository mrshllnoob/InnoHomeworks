package inno.l8.homework;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChattingClient {

    private static final String IPADDR = "localhost";
    private static final int PORT = 8189;

    private ChattingClient() {
        try (Socket conn = new Socket(IPADDR,PORT);
                Scanner clInput = new Scanner(System.in);
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
                PrintWriter output = new PrintWriter(osw,true)) {
            boolean isDone = false;
            while (!isDone) {
                String line = clInput.nextLine();
                if (line.trim().equals("quit")) {
                    isDone = true;
                }
                output.write(line + "\n");
                output.flush();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChattingClient();
        new ChattingClient();
    }

}
