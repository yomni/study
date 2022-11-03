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

### 자바에서 제공하는 함수형 인터페이스
- java.lang.function 패키지

#### Function<T, R>
- T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
  - R apply(T t)
- 함수 조합용 메소드
  - andThen()
    - Function 함수A.andThen(함수B) : 함수A의 결과를 함수B의 입력으로 받는 함수형 인터페이스 반환
  - compose()
    - Function 함수A.compose(함수B) : 함수B의 결과를 함수A의 입력으로 받는 함수형 인터페이스 반환

#### BiFunction<T, U, R>
- 두 개의 값(T, U)를 받아 R타입을 리턴하는 함수 인터페이스
  - R apply(T t, U u)
- 함수 조합용 메소드
  - andThen()

#### Consumer<T>
- T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
  - void accept(T t)
- 함수 조합용 메소드
  - andThen()

#### Supplier<T>
- T 타입의 값을 제공하는 함수 인터페이스
  - T get()

#### Predicate<T>
- T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
  - boolean test(T t)
- 함수 조합용 메소드
  - and()
  - or()
  - negate()

#### UnaryOperator<T>
- Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
```java
UnaryOperator<Integer> plus10 = i -> i + 10;
System.out.println(plus10.apply(1));
```

#### BinaryOperator<T>
- BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입력값 두개를 받아 리턴하는 함수 인터페이스

### 람다 표현식
#### 람다(Lambda) 
- (매개변수 리스트) -> { 구현바디 }

#### 인자 리스트
- () : 인자가 없을 때
- (one) 또는 one : 인자가 한개 일 때
- (one, two) : 인자가 여러개 일 때
- 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다.
```java
Supplier<Integer> get10 = () -> {
    return 10;
};

// 한줄인 경우 축약 가능
Supplier<Integer> get11 = () -> 11;

// 매개변수에 타입을 정의해줘도 됨
BinaryOperator<Integer> sumWithType = (Integer a, Integer b) -> a + b;

// 타입정의하지 않아도 됨(컴파일러가 충분히 추론 가능)
BinaryOperator<Integer> sumWithoutType = (a, b) -> a + b;
```

#### 바디
- 화살표 오른쪽에 함수 본문을 정의한다.
- 여러 줄인 경우에 `{}`를 사용해서 묶는다.
- 한 줄인 경우에 생략 가능, return도 생략 가능

#### 변수 캡처(Variable Capture)
- 로컬변수 캡처
  - final 이거나 effective final(사실상 final인 경우) 경우에만 참조할 수 있다
  - 그렇지 않을 경우 **`concurrency 문제가 생길 수 있어서`** 컴파일러가 방지함
- 람다는 익명클래스와 달리 `쉐도잉 : 내부 scope의 동일한 이름의 변수가 상위 scope의 변수를 무시하는 경우`하지 않는다
```java
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
```

### 메소드 레퍼런스
람다가 하는 일이 기존 메소드를 호출하거나, 생성자를 호출하는 것이라면  
람다 표현식에 메소드 레퍼런스를 사용하여 간결하게 표현 가능
1. 스태틱 메소드 참조 - 타입::스태틱 메소드명
2. 특정 객체의 인스턴스 메소드 참조 - 객체 레퍼런스::인스턴스 메소드명
3. 임의 객체의 인스턴스 메소드 참조 - 타입::인스턴스 메소드명
4. 생성자 참조 - 타입::new

- 메소드 또는 생성자의 매개변수로 람다의 입력값을 받는다
- 리턴값 또는 생성한 객체는 람다의 리턴값이다


