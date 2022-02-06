package com.example.project_echess;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class ChessClient {
    private final String userName;
    private final String color;
    private final String hostName;
    private final  int portNumber;
    private  boolean stop;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;

    ChessClient(String userName, String color, String hostName, int portNumber)  {
        this.userName = userName;
        this.color = color;
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    private void runClient(){
        try {
            stop = false;
            socket = new Socket(hostName, portNumber);
            in = new ObjectInputStream(socket.getInputStream() );
            out = new ObjectOutputStream(socket.getOutputStream() );

            while (!stop) {
                TimeUnit.SECONDS.sleep(3);
            }

        } catch (UnknownHostException | InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void activate(){
        new Thread(this::runClient).start();
    }

    public void stop(){
        stop = true;
    }

    private String getClientMessage(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "from client '"+ userName + "' with color '" + color + "' at "+ time.format(formatter);
    }
}
