package net.htlgrieskirchen.pos.zweib.cscomm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kimme
 */
public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        int selection = -1;
        do {
            selection = client.menu();
            switch (selection) {
                case 1:
                    client.requestCurrentDate();
                    break;
                case 2:
                    client.requestPrimes();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Ungültige Auswahl!");
            }
        } while (selection != 3);
    }

    private int menu() {
        Scanner s = new Scanner(System.in);
        System.out.println("1 ... Datum abfragen");
        System.out.println("2 ... Primzahlen abfragen");
        System.out.println("3 ... Ende");
        return s.nextInt();
    }

    private void requestCurrentDate() {
        Scanner s = new Scanner(System.in);
        try (Socket clientSocket = new Socket("localhost", 4711);
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));) {
            System.out.print("Pattern: ");
            String pattern = s.nextLine();
            bw.write("GET date " + pattern);
            bw.newLine();
            bw.flush();
            String date = br.readLine();
            System.out.println("Aktuelles Datum: " + date);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void requestPrimes() {
        Scanner s = new Scanner(System.in);
        try (Socket clientSocket = new Socket("localhost", 4711);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));) {
            System.out.print("Obergrenze: ");
            String upperLimit = s.nextLine();
            bw.write("GET primes " + upperLimit);
            bw.newLine();
            bw.flush();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            Object o = ois.readObject();
            if (o != null && o instanceof ArrayList) {
                List<Integer> primes = (ArrayList<Integer>) o;
                System.out.println(o);
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
