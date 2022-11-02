package step1;

public class Foo {

    public static void main(String[] args) {
        // 익명 내부 클래스 anonymous inner class
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("yomni");
            }
        };

        // 람다표현식으로 함수형 인터페이스 구현
        RunSomething runSomethingLambda1 = () -> System.out.println("yomni");
        RunSomething runSomethingLambda2 = () -> {
            // 여러 줄 표현
            System.out.println("yomni1");
            System.out.println("yomni2");
        };
    }
}
