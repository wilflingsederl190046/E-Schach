package com.example.project_echess;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ChessWorker implements Runnable {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ObjectInputStream inGegner;
    private ObjectOutputStream outGegner;
    private boolean stop;
    private boolean clientBlack;
    private boolean clientWhite;
    private ChessServer chessServer;
    private String user;
    private String message;
    private Map<String, ObjectOutputStream> allWriter = new HashMap<>();
    private Map<String, ObjectInputStream> allReader = new HashMap<>();

    public ChessWorker(Map<String, ObjectOutputStream> allWriter, Map<String, ObjectInputStream> allReader, ChessServer chessServer, String user) {
        this.chessServer = chessServer;
        this.clientBlack = false;
        this.clientWhite = false;
        this.user = user;
        this.allWriter = allWriter;
        this.allReader = allReader;
    }

    @Override
    public void run() {
        stop = false;
        String inputString;
        while (!stop) {
            try {
                if(allWriter.keySet().size()>=2) {
                    for (String s : allWriter.keySet()) {
                        if (s.equals(user)) {
                            in = allReader.get(s);
                            out = allWriter.get(s);
                        } else {
                            inGegner = allReader.get(s);
                            outGegner = allWriter.get(s);
                            stop = true;
                        }
                    }
                }


                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        boolean running = true;
        int count = 0;
        String message1 = "";
        while (true) {

            try {
                if(count == 0){
                    message1 = (String) inGegner.readObject();
                    System.out.println(message1);
                }
                if (running) {
                    message = (String) in.readObject();
                    outGegner.writeObject(message);
                } else {
                    message = (String) inGegner.readObject();
                    out.writeObject(message);
                }
                count++;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            running = !running;
        }

    }

    void stopServerTread() {
        stop = true;
    }
}