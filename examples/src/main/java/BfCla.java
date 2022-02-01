import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BfCla {
    public static void main(String[] args) {
        BiPredicate<Integer, String> condition
                = (i, s) -> i > 20 && s.startsWith("R");

        System.out.printf("ConditionTest: " + condition.test(1, "La"));
    }
}
