package step3;

import java.util.function.*;

public class Foo {
    public static void main(String[] args) {
//        Plus10 plus10 = new Plus10();
        UnaryOperator<Integer> plus10 = i -> i + 10;
        System.out.println(plus10.apply(1));

        Function<Integer, Integer> multiply2 = i -> i * 2;
        System.out.println(multiply2.apply(10));

        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2)); // 14

        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(multiply2);
        System.out.println(plus10AndMultiply2.apply(10)); // 40

        BiFunction<Integer, String, String> combineIntegerString = (i, s) -> i + s;
        System.out.println(combineIntegerString.apply(1, "입니다"));

        Consumer<Integer> consumer = i -> System.out.println(i + " Consume");
        consumer.accept(10);

        Supplier<Integer> supplier = () -> 10;
        System.out.println(supplier.get());

        Predicate<String> startWithYomni = s -> s.startsWith("yomni");
        System.out.println(startWithYomni.test("yomni2123123")); // true
    }
}
