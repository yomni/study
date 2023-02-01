package step4.lambda;

import java.util.function.*;

public class Foo {
    public static void main(String[] args) {
        Supplier<Integer> get10 = () -> {
            return 10;
        };

        // 한줄인 경우 축약 가능
        Supplier<Integer> get11 = () -> 11;

        // 매개변수에 타입을 정의해줘도 됨
        BinaryOperator<Integer> sumWithType = (Integer a, Integer b) -> a + b;

        // 타입정의하지 않아도 됨(컴파일러가 충분히 추론 가능)
        BinaryOperator<Integer> sumWithoutType = (a, b) -> a + b;

        Foo foo = new Foo();
        foo.run();
    }

    private void run() {
        int baseNumber = 10;

        // 로컬클래스
        class LocalClass {
            void printBaseNumber() {
                // Shadowing : 바깥 scope 에 있던 변수가 가려짐
                int baseNumber = 200;
                System.out.println(baseNumber);
            }
        }

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                // Shadowing : 바깥 scope 에 있던 변수가 가려짐
                int baseNumber = 200;
                System.out.println(baseNumber);
            }
        };

        IntConsumer printInt = (i) -> {
            // No Shadowing : 람다에선 Shadowing 이 안됨
//            int baseNumber = 200;
            System.out.println(i + baseNumber);
        };
    }
}
