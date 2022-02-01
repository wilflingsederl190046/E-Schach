package net.htlgrieskirchen.pos.zweib.cscomm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kimme
 */
public class Server {

    public static void main(String[] args) {
        System.out.println("Server gestartet.");
        while(true) {
            try(ServerSocket serverSocket = new ServerSocket(4711);) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Neuer Client hat sich verbunden ...");
                Thread t = new Thread(new Worker(clientSocket));
                t.start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
