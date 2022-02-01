import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> myList = Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1");

        Stream<String> myStream = myList.stream();

        List<String> myList2 = myStream.collect(Collectors.toList());

        myList.stream().filter(s -> s.startsWith("c"))
              .forEach(System.out::println);

        myList.stream().filter(s -> s.startsWith("c"))
              .map(String::toUpperCase)
              .forEach(System.out::println);

        myList.stream().filter(s -> s.startsWith("c"))
              .map(String::toUpperCase)
              .sorted()
              .forEach(System.out::println);

        Arrays.asList("a1", "a2", "a3").stream()
              .findFirst()
              .ifPresent(System.out::println);

        IntStream.range(1, 4).forEach(i -> System.out.println(i));

        double avg = Arrays.stream(new int[]{1, 2, 3})
                           .map(n -> 2 * n + 1)
                           .average()
                           .getAsDouble();
        System.out.println(avg);

        Stream.of("a1", "a2", "a3")
              .map(s -> s.substring(1))
              .mapToInt(Integer::parseInt)
              .max()
              .ifPresent(System.out::println);

        String[] myArray = {"this", "is", "a", "sentence"};

        String result = Arrays.stream(myArray)
                .reduce("", (a1, a2) -> a1 + a2 + " ");

        System.out.println(result);
    }
}
