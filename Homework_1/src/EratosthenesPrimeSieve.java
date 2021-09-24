import java.awt.*;
import java.util.ArrayList;

public class EratosthenesPrimeSieve implements PrimeSieve {

    private int[] allPrimes;

    public EratosthenesPrimeSieve(int upperLimit) {
        boolean[] isPrim = new boolean[upperLimit];

        for (int i = 0; i < upperLimit; i++) {
            isPrim[i] = i % 2 == 1;
        }

        for (int i = 3; i < Math.sqrt(upperLimit)+2; i += 2) {
            /*allPrimes[i - 2] = i;
            for(int y = i; y < upperLimit; y++) {
                if (y % i != 0 && allPrimes[y - 1] != 0) {
                    allPrimes[y - i] = y-1;
                } else if(y % i != 0 && i == 2) {
                    allPrimes[y - i] = y;
                } else {
                    allPrimes[y - i] = -1;
                }
            }*/
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
        for(int i = 0; i < allPrimes.length; i++) {
            System.out.print(allPrimes[i] + ", ");
        }
    }
}
