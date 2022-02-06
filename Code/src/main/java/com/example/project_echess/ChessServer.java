package com.example.project_echess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChessServer {

    private final ExecutorService pool;
    private final List<ChessWorker> clients;
    private final int portNumber;
    private boolean stop;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Map<String, ObjectOutputStream> allWriter = new HashMap<>();
    private Map<String, ObjectInputStream> allReader = new HashMap<>();

    public ChessServer(int portNumber) {
        this.portNumber = portNumber;
        pool = Executors.newFixedThreadPool(2);
        clients = new ArrayList<>();
    }

    public Map<String, ObjectOutputStream> getAllWriter() {
        return allWriter;
    }

    public void setAllWriter(Map<String, ObjectOutputStream> allWriter) {
        this.allWriter = allWriter;
    }

    public Map<String, ObjectInputStream> getAllReader() {
        return allReader;
    }

    public void setAllReader(Map<String, ObjectInputStream> allReader) {
        this.allReader = allReader;
    }

    private void runServer(){

        System.out.println("SERVER: Waiting for client");
        try{
            ServerSocket serverSocket = new ServerSocket(portNumber);
            stop = false;
            int counter = 0;

            while(!stop){
                Socket clientSocket = serverSocket.accept();
                counter++;
                System.out.println("SERVER: client connected");
                startHandle(clientSocket, counter + "");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startHandle(Socket clientSocket, String user) throws IOException {
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        allReader.put(user, in);
        allWriter.put(user, out);
        ChessWorker worker = new ChessWorker(allWriter, allReader, this, user);
        pool.execute(worker);
        clients.add(worker);
    }

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
}

