package com.example.project_echess;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChessClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 3141);

            OutputStream out = socket.getOutputStream();
            PrintStream ps = new PrintStream(out, true);
            ps.println("Hallo Welt!");

            InputStream rein = socket.getInputStream();
            BufferedReader buff = new BufferedReader(new InputStreamReader(rein));

            while (buff.ready()) {
                System.out.println(buff.readLine());
            }

        } catch (UnknownHostException e) {
            System.out.println("Unknown Host...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOProblems...");
            e.printStackTrace();
        } finally {
            if (socket != null)
                try {
                    socket.close();
                    System.out.println("Socket closed...");
                } catch (IOException e) {
                    System.out.println("Socket cannot get closed...");
                    e.printStackTrace();
                }
        }
    }
}
