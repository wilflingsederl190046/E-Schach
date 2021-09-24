import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please enter the limit until prime numbers should be counted!");
        Scanner s = new Scanner(System.in);
        int userInput = s.nextInt();
        EratosthenesPrimeSieve eps = new EratosthenesPrimeSieve(userInput);
        eps.printPrimes();
    }
}
