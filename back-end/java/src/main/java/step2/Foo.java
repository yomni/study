package step2;

public class Foo {

    public static void main(String[] args) {

        // 순수 함수
        RunSomething pureFunction = number -> number + 10;
        // 동일한 입력값에 대한 동일한 결과 값을 기대
        System.out.println(pureFunction.doIt(1));
        System.out.println(pureFunction.doIt(1));
        System.out.println(pureFunction.doIt(1));
        System.out.println(pureFunction.doIt(1));

        // 순수 함수가 아님(외부 요인에 의해 값이 변경)
        RunSomething notPureFunction = new RunSomething() {
            int baseNumber = 10;
            @Override
            public int doIt(int number) {
                baseNumber++;
                return number + baseNumber;
            }
        };
        System.out.println(notPureFunction.doIt(1));
        System.out.println(notPureFunction.doIt(1));
        System.out.println(notPureFunction.doIt(1));
        System.out.println(notPureFunction.doIt(1));
        System.out.println(notPureFunction.doIt(1));
    }
}
