package com.example.project_echess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChessServer {
    private final ServerSocket server;

    public ChessServer(int port) throws IOException {
        server = new ServerSocket(port);
    }

    private void connect() {

        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
                OutIn(socket);
            }

            catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    private void OutIn(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());
        String s;

        while(in.ready()) {
            s = in.readLine();
            out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        ChessServer server = new ChessServer(3141);
        server.connect();
    }
}
