package com.example.project_echess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChessServer {
    private static final int PORT = 4711;

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(PORT)) {
            System.out.println("server started");
            while (true) {
                try {
                    new Thread(new ChessWorker(s.accept())).start();
                    System.out.println("client connected");
                } catch (IOException e) {
                    throw new RuntimeException("ERROR: unable to instantiate reader and writer", e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("ERROR: unable to instantiate the server socket", e);
        }
    }
}
