package com.example.project_echess;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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

    ChessClient(String userName, String color, String hostName, int portNumber)  {
        this.userName = userName;
        this.color = color;
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    private void runClient(){
        try {
            stop = false;
            Socket socket = new Socket(hostName, portNumber);
            DataInputStream in = new DataInputStream( socket.getInputStream() );
            DataOutputStream out = new DataOutputStream(socket.getOutputStream() );
            out.writeUTF(getClientMessage());


            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("client" + color.toUpperCase() + "IsHere");

            while (!stop) {
                TimeUnit.SECONDS.sleep(3);
                out.writeUTF(getClientMessage());
            }

        } catch (UnknownHostException | InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
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
