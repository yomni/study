# Java 8의 주요 기능들에 대한 학습

## 함수형 인터페이스와 람다 표현식
함수형 인터페이스(Functional Interface)
- 추상 메소드를 딱 하나만 가지고 있는 인터페이스(SAM ; Single Abstract Method)
- @FunctionalInterface 애노테이션을 포함한 인터페이스

## 람다 표현식(Lambda Expressions)
- 함수형 인터페이스의 인스턴스를 만드는 방법으로 쓰일 수 있다
- 코드를 줄일 수 있다
- 메소드의 매개변수, 리턴 타입, 변수로 만들어 사용할 수 있다

```java

// 아래 두 표현식은 동일한 표현식

// 익명 내부 클래스 anonymous inner class
RunSomething runSomething = new RunSomething() {
    @Override
    public void doIt() {
        System.out.println("yomni");
    }
};

// 람다 표현식
RunSomething runSomethingLambda = () -> System.out.println("yomni");
```

## 자바에서 함수형 프로그래밍
- 함수를 일급객체(First class object)로 사용할 수 있다
- 순수 함수(Pure Function)
  - 사이드 이펙트가 없는 함수(함수 밖에 있는 값을 변경하지 않는다)
  - 상태가 없다(함수 밖에 있는 값을 사용하지 않는다)
- 고차 함수(Higher-Order Function)
  - 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수 있다
- 불변성
```java
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
```

> ### 생각해보자  
> 수학에서의 함수의 정의는 `정의역의 모든 값에 대해 공역이 유일하게 결정될 때` 이것을 함수라고 정의한다  
> 순수 함수란 수학에서의 함수의 정의와 일맥상통한다. 즉, `모든 입력값에 대해 동일한 결과값을 보장`해야만  
> 함수형 프로그래밍이라 할 수 있다.
