package com.example.project_echess;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChessClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 4711);

            OutputStream out = socket.getOutputStream();
            PrintStream ps = new PrintStream(out, true);

            InputStream in = socket.getInputStream();
            BufferedReader buff = new BufferedReader(new InputStreamReader(in));

            while (buff.ready()) {
                String line = buff.readLine();
                if(line.equals("menue_launch")) {
                    Main.launch();
                } else if(line.startsWith("menue_data;")) {

                }
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
