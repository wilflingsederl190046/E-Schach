public class Lession0 {
    interface NumericTest {
        boolean computeTest(int n);
    }

    class IsEven implements NumericTest {
        public boolean computeTest(int n) {
            return (n % 2) == 0;
        }
    }

    class IsNegative implements NumericTest {
        public boolean computeTest(int n) {
            return n < 0;
        }
    }

    public void lession0Test() {
        NumericTest isEven = new IsEven();
        NumericTest isNegative = new IsNegative();

        // Output: false
        System.out.println(isEven.computeTest(5));

        // Output: true
        System.out.println(isNegative.computeTest(-5));
    }
}

