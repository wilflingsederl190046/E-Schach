package net.htlgrieskirchen.pos.zweib.cscomm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kimme
 */
public class Worker implements Runnable {

    private final Socket clientSocket;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));) {
            String request = br.readLine();
            if (request != null && request.startsWith("GET")) {
                String[] parts = request.trim().split(" ");
                switch (parts[1]) {
                    case "date":
                        String pattern = "dd.MM.yyyy HH:mm";
                        if (parts.length >= 3) {
                            pattern = parts[2];
                        }
                        try {
                            System.out.println("Schicke aktuelles Datum im Format " + pattern);
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            String result = sdf.format(new Date());
                            bw.write(result);
                            bw.newLine();
                        } catch (IllegalArgumentException ex) {
                            bw.write("Error. Invalid date format.");
                            bw.newLine();
                        }
                        break;
                    case "primes":
                        List<Integer> primes = new ArrayList<>();
                        if (parts.length >= 3) {
                            int upperLimit = Integer.parseInt(parts[2]);
                            primes = generatePrimes(upperLimit);
                        }
                        try ( ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
                            oos.writeObject(primes);
                        }
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private List<Integer> generatePrimes(int n) {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
