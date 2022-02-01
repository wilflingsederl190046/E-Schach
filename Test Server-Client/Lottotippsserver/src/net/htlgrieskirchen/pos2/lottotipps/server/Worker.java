/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos2.lottotipps.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author Torsten Welsch
 */
public class Worker implements Runnable {
    private static final int NUMBER_OF_TIPS = 6;
    private static final int MAX_NUMBER = 45;
    private static final String MESSAGE_SEPARATOR = ":";
    private static final String NUMBER_SEPARATOR = ",";
    
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    
    public Worker(Socket socket) {
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
        System.out.println("reading " + line);
        while (line != null) {
            try {
                int numberOfTips = Integer.parseInt(line.split(MESSAGE_SEPARATOR)[1].trim());
                System.out.print("generating " + numberOfTips + " tipps ... ");
                out.println("<tips>");
                for (int i = 0; i < numberOfTips; ++i) {
                    out.println(generateTip());
                }
                out.println("</tips>");
                System.out.println("done");
            } catch (NumberFormatException e) {
                throw new RuntimeException("ERROR: unable parse line " + line + " to int", e);
            }
            line = in.nextLine();
        }
        
        System.out.println("client disconnected");
        
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("ERROR: unable to close reader, writer and socket", e);
        }
    }
    
    private static String generateTip() {  
        Set<Integer> numbers = new TreeSet<>();
        Random r = new Random();
        while (numbers.size() != NUMBER_OF_TIPS) {
            numbers.add(r.nextInt(MAX_NUMBER) + 1);
        }
        
        return numbers.stream().map(Object::toString).collect(Collectors.joining(NUMBER_SEPARATOR));
    }

}
