import java.awt.*;
import java.util.ArrayList;

public class EratosthenesPrimeSieve implements PrimeSieve {

    private int upperLimit;

    public EratosthenesPrimeSieve(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean isPrime(int p) {

        ArrayList<Integer> allNumbers = new ArrayList();

        for(int i = 2; i < p; i++) {
            allNumbers.add(i);
        }

        if(p < 2) {
            return false;
        } else {
            int multipleOfTwo = 2;
            int multipleOfThree = 3;
            int multipleOfFive = 5;
            int multipleOfSeven = 7;

            while(multipleOfTwo != p && multipleOfTwo < p) {
                multipleOfTwo += 2;
                allNumbers.remove((multipleOfTwo));
            }
            while (multipleOfThree != p && multipleOfThree < p) {
                multipleOfThree += 3;
                allNumbers.remove(multipleOfThree);
            }
            while (multipleOfFive != p && multipleOfFive < p) {
                multipleOfFive += 5;
                allNumbers.remove(multipleOfFive);
            }
            while (multipleOfSeven != p && multipleOfSeven < p) {
                multipleOfSeven += 7;
                allNumbers.remove(multipleOfSeven);
            }

            for (int i = 0; i < allNumbers.size(); i++) {
                if(allNumbers.get(i).equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void printPrimes() {
        ArrayList<Integer> allNumbers = new ArrayList();

        for(int i = 2; i < upperLimit; i++) {
            allNumbers.add(i);
        }

        for(int i = 2; i < upperLimit; i++) {
            if(isPrime(allNumbers.get(i)) == true) {
                System.out.println(i);
            }
        }
    }
}
