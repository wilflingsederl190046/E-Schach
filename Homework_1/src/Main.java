import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            System.out.println("EXAMPLE 1:\n" +
                "Please enter the limit until prime numbers should be counted!");
        Scanner s = new Scanner(System.in);
        int userInput = s.nextInt();
        EratosthenesPrimeSieve eps = new EratosthenesPrimeSieve(userInput);
        eps.printPrimes();
        System.out.println("\nEXAMPLE 2:\n" +
                "Please enter the limit for the sum of natural numbers with two prime numbers");
        int userInputTwo = s.nextInt();
        eps.sumOfPrimeNumbers(userInputTwo);
    }
}
