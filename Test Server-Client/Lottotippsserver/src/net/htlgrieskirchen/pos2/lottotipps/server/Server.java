/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos2.lottotipps.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Torsten Welsch
 */
public class Server {
    private static final int PORT = 4711;

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(PORT)) {
            System.out.println("server started");
            while (true) {
                try {
                    new Thread(new Worker(s.accept())).start();
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
