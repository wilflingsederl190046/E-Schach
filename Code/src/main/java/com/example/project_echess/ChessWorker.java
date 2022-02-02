package com.example.project_echess;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ChessWorker implements Runnable {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private boolean stop;
    private boolean clientBlack;
    private boolean clientWhite;

    public ChessWorker(Socket socket) {
        try {
            this.socket = socket;
            this.in = new Scanner(socket.getInputStream());
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.clientBlack = false;
            this.clientWhite = false;
        } catch (IOException e) {
            throw new RuntimeException("ERROR: unable to instantiate reader and writer", e);
        }
    }

    @Override
    public void run() {
        stop = false;
        String inputString;
        while (!stop) {
            if ((inputString = in.nextLine()) != null) {
                /*if(inputString.equals("clientBLACKIsHere") && !clientBlack) {
                    clientBlack = true;
                } else if(inputString.equals("clientWHITEIsHere") && !clientWhite) {
                    clientWhite = true;
                } else {
                    out.write("denied");
                    System.out.println("DENIED");
                }*/


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

    void stopServerTread() {
        stop = true;
    }
}
