package com.example.project_echess;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChessWorker implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public ChessWorker(Socket socket) {
        try {
            this.socket = socket;
            this.in = new Scanner(socket.getInputStream());
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException("ERROR: unable to instantiate reader and writer", e);
        }
    }

    @Override
    public void run() {
        String line = in.nextLine();
        while (true) {
            if(line.equals("start")) {
                out.println("menue_launch");
            } else if(line.startsWith("menue;")) {
                String[] parts = line.split(";");
                out.println("menue_data;" + parts[1] + ";" + parts[2]);
            } else {
                break;
            }
        }

        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("ERROR: unable to close reader, writer and socket", e);
        }
    }
}
