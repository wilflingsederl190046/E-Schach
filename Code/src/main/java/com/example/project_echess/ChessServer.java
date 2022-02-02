package com.example.project_echess;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChessServer {

    private final ExecutorService pool;
    private final List<ChessWorker> clients;
    private final int portNumber;
    private boolean stop;

    public ChessServer(int portNumber) {
        this.portNumber = portNumber;
        pool = Executors.newFixedThreadPool(2);
        clients = new ArrayList<>();
    }

    private void runServer(){

        System.out.println("SERVER: Waiting for client");
        try{
            ServerSocket serverSocket = new ServerSocket(portNumber);
            stop = false;

            while(!stop){
                Socket clientSocket = serverSocket.accept();
                System.out.println("SERVER: client connected");
                //startHandle();
                ChessWorker worker = new ChessWorker(clientSocket);
                pool.execute(worker);
                clients.add(worker);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void startHandle() {
        Thread thread = new Thread(){
            @Override
            public void run(){

            }
        };
        thread.start();
    }*/

    public void stop(){
        for(ChessWorker st : clients) {
            st.stopServerTread();
        }
        stop = true;
        pool.shutdown();
    }

    public void activate() {
        new Thread(this::runServer).start();
    }

    public static void main(String[] args) {
        new ChessServer(4445).activate();
    }

    /*public static void main(String[] args) {
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
    }*/
}

