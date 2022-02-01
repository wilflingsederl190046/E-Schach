package demo.rsickinger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        List<Weapon> list = new LinkedList<>();
        list.add(new Weapon("Varscona", CombatType.MELEE, DamageType.SLASHING, 11, 3, 5, 4250));
        list.add(new Weapon("Tuigan Bow", CombatType.RANGED, DamageType.MISSILE, 1, 5, 6, 3500));

        // anonymous class for interface Comparator
        list.sort(new Comparator<Weapon>() {
            @Override
            public int compare(Weapon o1, Weapon o2) {
                return Integer.compare(o1.getDamage(), o2.getDamage());
            }
        });

        // lambda for interface Comparator
        list.sort((w1, w2) -> Integer.compare(w1.getDamage(), w2.getDamage()));

        list.clear();

        // reads a file in one line using lambdas
        list = Files.lines(new File("weapons.csv").toPath())
                    .skip(1)
                    .map(s -> s.split(";"))
                    .map(s -> new Weapon(
                            s[0],
                            CombatType.valueOf(s[1]),
                            DamageType.valueOf(s[2]),
                            Integer.parseInt(s[3]),
                            Integer.parseInt(s[4]),
                            Integer.parseInt(s[5]),
                            Integer.parseInt(s[6])
                    ))
                    .collect(Collectors.toList());


        // lambda for functional interfaces
        Function<Double, Integer> f = x -> x.intValue();
        System.out.println("Function:       " + f.apply(47.11));
        BinaryOperator<Integer> m = (x, y) -> x % y;
        System.out.println("BinaryOperator: " + m.apply(5, 2));
        Supplier<Integer> s = () -> (int) (Math.random() * 10);
        System.out.println("Supplier:       " + s.get());
        Predicate<Integer> p = x -> x < 10;
        System.out.println("Predicate:      " + p.test(5));

        // method reference for functional interface Consumer (alternative: lambda)
        Consumer c = System.out::println; // (x) -> System.out.println(x);
        c.accept("hello");

        // lambda for own functional interfaces
        Printable printable = w -> System.out.println(w.getName() + " [" + w.getDamageType() + " = " + w.getDamage() + "]");
        printable.print(list.get(0));
    }

}