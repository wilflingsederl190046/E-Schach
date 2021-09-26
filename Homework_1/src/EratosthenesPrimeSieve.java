import java.awt.*;
import java.util.ArrayList;

public class EratosthenesPrimeSieve implements PrimeSieve {

    private int[] allPrimes;
    private int upperLimit;

    public EratosthenesPrimeSieve(int upperLimit) {
        this.upperLimit = upperLimit;
        boolean[] isPrim = new boolean[upperLimit];

        for (int i = 0; i < upperLimit; i++) {
            if(i % 2 != 0) {
                isPrim[i] = true;
            }
        }

        for (int i = 3; i < Math.sqrt(upperLimit)+2; i += 2) {
            if(isPrim[i] == true) {
                for(int y = i; y <= upperLimit / i; y++) {
                    final int number = i * y;
                    if(number < upperLimit) {
                        isPrim[number] = false;
                    }
                }
            }
        }

        int counter = 0;
        for(boolean primNumber : isPrim) {
            if(primNumber == true) {
                counter++;
            }
        }

        allPrimes = new int[counter];
        int indexForPrim = 0;
        for(int i = 0; i < isPrim.length; i++) {
            if(isPrim[i] == true) {
                allPrimes[indexForPrim++] = i;
            }
        }
        allPrimes[0] = 2;
    }

    @Override
    public boolean isPrime(int p) {
        for(int i = 0; i < allPrimes.length; i++) {
            if(allPrimes[i] == p) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void printPrimes() {
        System.out.println("All Prime Numbers until " + upperLimit + ":");
        for(int i = 0; i < allPrimes.length; i++) {
            System.out.print(allPrimes[i]);
            if(allPrimes.length - 1 != i) {
                System.out.print(", ");
            }
        }
        System.out.print("\nAmount of the Prime Numbers: " + allPrimes.length);
    }

    public void sumOfPrimeNumbers(int limit) {
        if(limit > 3) {
            for(int i = 2; i <= limit; i += 2) {
                int firstNumber;
                for(int y = 0; y < allPrimes.length; y++) {
                    firstNumber = allPrimes[y];
                    int secondNumber;
                    for(int x = 0; x < allPrimes.length; x++) {
                        secondNumber = allPrimes[x];
                        if((firstNumber + secondNumber) == i) {
                            System.out.println(i + " summe: " + i + " = " + firstNumber + " + " + secondNumber);
                            break;
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Das Limit muss über 3 groß sein!");
        }
    }
}
