# 🧩 React Component & Architecture (React 컴포넌트 깊이 읽기)

> 본 문서는 React 애플리케이션의 기본 구성 요소인 **컴포넌트(Component)**의 개념과 내부 메커니즘(JSX 트랜스파일링, Render Phase vs Commit Phase), 데이터 흐름(Props, State, Children), 패러다임 변화(Class vs Function), 그리고 현대 실무에서 자주 사용되는 고급 설계 패턴(Compound Component, Controlled/Uncontrolled)을 깊이 있게 학습하기 위해 작성되었습니다.

---

## 📌 목차
1. [React Component의 본질과 선언적 UI](#1-react-component의-본질과-선언적-ui)
   - 1.1 컴포넌트란 무엇인가?
   - 1.2 단일 책임 원칙(SRP)과 컴포넌트 분리 기준
   - 1.3 $UI = f(\text{Props}, \text{State})$ 의 공식
2. [JSX / TSX의 메커니즘과 동작 원리](#2-jsx--tsx의-메커니즘과-동작-원리)
   - 2.1 JSX는 HTML이 아니다 (`React.createElement`)
   - 2.2 React 17+ Automatic JSX Transform (`jsx-runtime`)
   - 2.3 JSX 문법 규칙과 주의점
3. [Props vs State vs Children (컴포넌트 데이터 트라이앵글)](#3-props-vs-state-vs-children-컴포넌트-데이터-트라이앵글)
   - 3.1 Props: 단방향 불변 데이터 (Read-Only)
   - 3.2 State: 캡슐화된 가변 데이터와 불변성(Immutability) 원칙
   - 3.3 Children & Slot Pattern (컴포지션 패턴)
4. [컴포넌트 유형과 패러다임 변화 (Class vs Function)](#4-컴포넌트-유형과-패러다임-변화-class-vs-function)
   - 4.1 클래스형 컴포넌트(Class Component)와 라이프사이클
   - 4.2 함수형 컴포넌트(Function Component)와 Hooks
   - 4.3 클래스 vs 함수형 생명주기 매핑 및 비교
5. [실무 컴포넌트 설계 패턴 (Design Patterns)](#5-실무-컴포넌트-설계-패턴-design-patterns)
   - 5.1 Container - Presentational 패턴
   - 5.2 Custom Hook 분리 패턴
   - 5.3 Compound Components 패턴 (유연한 UI 조합)
   - 5.4 Controlled vs Uncontrolled Components (제어 vs 비제어 컴포넌트)
6. [렌더링 흐름과 Performance Optimization](#6-렌더링-흐름과-performance-optimization)
   - 6.1 React의 3단계 렌더링 과정 (Trigger → Render → Commit)
   - 6.2 불필요한 리렌더링 방지 (`React.memo`, `useCallback`, `useMemo`)
   - 6.3 `key` Props의 중요성과 Reconciliation 과정
7. [컴포넌트 패턴 대분류 요약 비교표](#7-컴포넌트-패턴-대분류-요약-비교표)
8. [프로젝트 내 실습 코드 가이드](#8-프로젝트-내-실습-코드-가이드)

---

## 1. React Component의 본질과 선언적 UI

### 1.1 컴포넌트란 무엇인가?
React에서 **컴포넌트(Component)**는 독립적이고 재사용 가능한 UI 단위이며, 애플리케이션 화면을 구성하는 "레고 블록"과 같습니다.

- **캡슐화 (Encapsulation)**: 컴포넌트는 자신의 마크업(HTML/JSX), 스타일, 그리고 내부 로직(State)을 하나의 단위로 감쌉니다.
- **재사용성 (Reusability)**: 한번 작성한 컴포넌트는 입력값(Props)만 다르게 주어 어디서든 수십, 수백 번 재사용할 수 있습니다.
- **조합성 (Composability)**: 작은 컴포넌트들을 조합하여 더 큰 컴포넌트나 전체 페이지를 구축합니다.

---

### 1.2 단일 책임 원칙(SRP)과 컴포넌트 분리 기준

SOLID 원칙 중 **단일 책임 원칙(Single Responsibility Principle)**은 컴포넌트 설계 시 매우 중요합니다.

> "하나의 컴포넌트는 오직 하나의 이유로만 변경되어야 한다 (Do one thing well)."

#### 🚩 잘못된 예: 비대해진 컴포넌트 (God Component)
하나의 컴포넌트가 API 패칭, Form 유효성 검사, 복잡한 비즈니스 로직, 모달 팝업 UI까지 모두 포함하는 경우 디버깅과 테스트가 불가능해집니다.

#### ✅ 올바른 예: 역할에 따른 컴포넌트 분리
```
[ UserProfilePage ] (페이지 레이아웃 및 라우팅)
  ├── [ UserHeader ] (유저 정보 디스플레이)
  ├── [ UserPostListContainer ] (게시글 데이터 패칭 및 상태 관리)
  │     └── [ UserPostCard ] (단일 게시글 UI 렌더링)
  └── [ EditProfileModal ] (프로필 수정 모달)
```

---

### 1.3 $UI = f(\text{Props}, \text{State})$ 의 공식

React 컴포넌트의 작동은 수학적 함수로 표현할 수 있습니다.

$$\text{UI} = f(\text{Props}, \text{State})$$

- **입력 (Input)**: 부모로부터 받은 `Props`와 내부에서 가지는 `State`.
- **출력 (Output)**: 해당 시점의 데이터 상태를 반영하는 **JSX (Virtual DOM)**.
- 이 공식에 의해 동일한 Props와 State가 주어지면 컴포넌트는 항상 동일한 UI를 반환해야 합니다 (**순수성 / Purity**).

---

## 2. JSX / TSX의 메커니즘과 동작 원리

### 2.1 JSX는 HTML이 아니다 (`React.createElement`)

개발자가 작성하는 JSX(JavaScript XML)는 브라우저가 직접 이해할 수 있는 표준 HTML이 아닙니다. 자바스크립트의 확장 문법일 뿐이며, 빌드 도구(Babel, SWC, Oxc)에 의해 순수 자바스크립트 객체 생성 함수로 트랜스파일링됩니다.

#### 💡 과거 방식 (React 16 이하)
```tsx
// 우리가 작성한 JSX
const element = <h1 className="title">Hello World</h1>;

// 트랜스파일링 결과
const element = React.createElement(
  'h1',
  { className: 'title' },
  'Hello World'
);
```

`React.createElement()` 함수가 실행되면 브라우저는 다음과 같은 **Virtual DOM 노드 객체(React Element)**를 리턴합니다.

```javascript
// React Element 객체 구조
{
  $$typeof: Symbol(react.element),
  type: 'h1',
  key: null,
  ref: null,
  props: {
    className: 'title',
    children: 'Hello World'
  }
}
```

---

### 2.2 React 17+ Automatic JSX Transform (`jsx-runtime`)

React 17부터는 `import React from 'react'`를 파일 상단에 명시하지 않아도 JSX를 사용할 수 있게 되었습니다. 컴파일러가 자동으로 `react/jsx-runtime` 패키지의 함수를 호출하도록 변환해주기 때문입니다.

```tsx
// React 17+ 트랜스파일링 결과
import { jsx as _jsx } from 'react/jsx-runtime';

const element = _jsx('h1', { className: 'title', children: 'Hello World' });
```

---

### 2.3 JSX 문법 규칙과 주의점

1. **반드시 하나의 단일 루트 엘리먼트로 감싸야 함**:
   - React가 Virtual DOM 트리를 Diffing할 때 단일 루트가 필요하기 때문입니다.
   - 불필요한 `<div>` 래퍼 태그 작성을 피하기 위해 **React Fragment (`<>...</>`)**를 사용합니다.
2. **닫는 태그 필수**:
   - `<input />`, `<img />`와 같은 단일 태그도 반드시 셀프 클로징(`/`)을 해주어야 합니다.
3. **JS 표현식은 중괄호 `{}` 내에 작성**:
   - 변수, 함수 호출, 삼항 연산자 등을 사용 가능 (단, `if`, `for` 문은 문(Statement)이므로 표현식 안에서 직가 사용할 수 없어 삼항 연산자나 `map()` 사용).
4. **카멜케이스(camelCase) 속성 사용**:
   - `class` → `className`
   - `for` → `htmlFor`
   - `onclick` → `onClick`

---

## 3. Props vs State vs Children (컴포넌트 데이터 트라이앵글)

```
        ┌──────────────────────────────────┐
        │          Component Data          │
        └──────────────────────────────────┘
                 /         │         \
                /          │          \
         [ Props ]     [ State ]    [ Children ]
       (부모 수신/불변) (내부 소유/가변) (컴포지션 슬롯)
```

---

### 3.1 Props: 단방향 불변 데이터 (Read-Only)

`Props` (Properties)는 부모 컴포넌트가 자식 컴포넌트에 전달하는 데이터입니다.

- **단방향 데이터 흐름**: 데이터는 항상 부모 → 자식 방향으로 전달됩니다.
- **읽기 전용 (Immutable)**: 자식 컴포넌트는 자신이 전달받은 Props를 직접 수정할 수 없습니다. (`props.color = 'red'` 금지!)
- **Default Props & TypeScript Interface**:
  ```tsx
  interface ButtonProps {
    label: string;
    variant?: 'primary' | 'secondary'; // 선택적 프로퍼티
    onClick: () => void;
  }

  export function CustomButton({ label, variant = 'primary', onClick }: ButtonProps) {
    return (
      <button className={`btn btn-${variant}`} onClick={onClick}>
        {label}
      </button>
    );
  }
  ```

---

### 3.2 State: 캡슐화된 가변 데이터와 불변성(Immutability) 원칙

`State`는 컴포넌트 내부에서 생성되고 관리되며, 시간에 따라 변경될 수 있는 동적 데이터입니다.

#### ⚠️ 상태 변경 시 불변성(Immutability)을 지켜야 하는 이유
React는 가상 DOM을 비교(Diffing)할 때 얕은 비교(Shallow Comparison, `Object.is`)를 수행합니다. 객체나 배열 내부의 값을 직접 수정(Mutation)하면 메모리 참조 주소가 변하지 않으므로 React는 상태가 변경되었다는 사실을 감지하지 못해 렌더링이 발생하지 않습니다!

```tsx
// ❌ 잘못된 예: 불변성 파괴 (직접 수정)
const [user, setUser] = useState({ name: 'Kim', age: 20 });
user.age = 21; // 기존 객체 직접 수정
setUser(user); // 참조 주소가 동일하므로 재렌더링 안 됨!

// ✅ 올바른 예: 새로운 객체 생성 (불변성 준수)
setUser({
  ...user,
  age: 21
});
```

---

### 3.3 Children & Slot Pattern (컴포지션 패턴)

`props.children`은 컴포넌트 여는 태그와 닫는 태그 사이에 전달된 모든 요소(텍스트, JSX, 다른 컴포넌트)를 수신할 수 있게 해줍니다.

```tsx
// 💡 Card 컴포넌트 (Container 역활)
interface CardProps {
  title: string;
  children: React.ReactNode; // React의 모든 렌더링 가능 요소
}

export function Card({ title, children }: CardProps) {
  return (
    <div className="card">
      <div className="card-header"><h3>{title}</h3></div>
      <div className="card-body">{children}</div>
    </div>
  );
}

// 💡 사용부 (원하는 UI를 내부에 삽입)
<Card title="프로필 설정">
  <p>이름: 홍길동</p>
  <button>수정하기</button>
</Card>
```

---

## 4. 컴포넌트 유형과 패러다임 변화 (Class vs Function)

### 4.1 클래스형 컴포넌트(Class Component)와 라이프사이클

React 16.8 이전에는 상태나 라이프사이클 메서드를 사용하기 위해 ES6 Class를 작성해야 했습니다.

```tsx
import React, { Component } from 'react';

interface Props { step: number; }
interface State { count: number; }

export class ClassCounter extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = { count: 0 };
    // this 바인딩
    this.handleIncrement = this.handleIncrement.bind(this);
  }

  componentDidMount() {
    console.log('컴포넌트가 마운트됨');
  }

  componentDidUpdate(prevProps: Props, prevState: State) {
    if (prevState.count !== this.state.count) {
      console.log(`카운트 변경: ${this.state.count}`);
    }
  }

  componentWillUnmount() {
    console.log('컴포넌트가 언마운트됨');
  }

  handleIncrement() {
    this.setState(prev => ({ count: prev.count + this.props.step }));
  }

  render() {
    return (
      <div>
        <p>Class Count: {this.state.count}</p>
        <button onClick={this.handleIncrement}>증가</button>
      </div>
    );
  }
}
```

---

### 4.2 함수형 컴포넌트(Function Component)와 Hooks

현대 React는 함수형 컴포넌트와 **Hooks**가 표준입니다. 함수형 컴포넌트는 작성하기 쉽고 메모리 효율적이며, 비즈니스 로직을 커스텀 훅으로 자유롭게 추출할 수 있습니다.

```tsx
import { useState, useEffect } from 'react';

interface Props { step: number; }

export function FunctionCounter({ step }: Props) {
  const [count, setCount] = useState(0);

  // 마운트 + 업데이트 처리
  useEffect(() => {
    console.log(`카운트 변경: ${count}`);

    // 언마운트 클린업 함수
    return () => console.log('클린업/언마운트');
  }, [count]);

  return (
    <div>
      <p>Function Count: {count}</p>
      <button onClick={() => setCount(prev => prev + step)}>증가</button>
    </div>
  );
}
```

---

### 4.3 클래스 vs 함수형 생명주기 매핑 및 비교

| 생명주기 이벤트 | 클래스형 컴포넌트 (Class) | 함수형 컴포넌트 (Function + Hooks) |
| :--- | :--- | :--- |
| **마운트 (Mount)** | `componentDidMount()` | `useEffect(() => { ... }, [])` |
| **업데이트 (Update)** | `componentDidUpdate(prevProps, prevState)` | `useEffect(() => { ... }, [dep1, dep2])` |
| **언마운트 (Unmount)** | `componentWillUnmount()` | `useEffect(() => { return () => { ... } }, [])` |
| **상태 관리 (State)** | `this.state`, `this.setState()` | `useState()`, `useReducer()` |
| **인스턴스 참조 (Ref)** | `React.createRef()`, `this.myRef` | `useRef()` |
| **코드 재사용 메커니즘** | HOC (Higher-Order Component), Render Props | Custom Hooks (`useXxx`) |

---

## 5. 실무 컴포넌트 설계 패턴 (Design Patterns)

### 5.1 Container - Presentational 패턴

과거부터 널리 쓰여온 패턴으로, **데이터 패칭 및 상태 관리(Container)**와 **순수 UI 렌더링(Presentational)**을 분리하는 기법입니다.

- **Presentational Component**: Props만 받아서 UI를 렌더링하는 순수 함수 컴포넌트 (상태 최소화).
- **Container Component**: API 패칭, 상태 관리를 맡고 Presentational 컴포넌트에 Props 전달.

---

### 5.2 Custom Hook 분리 패턴

현대 React에서는 Container 컴포넌트 대신 **Custom Hook**을 작성하여 상태 관리 로직을 추출하는 것이 표준입니다.

```tsx
// 💡 Custom Hook (로직 분리)
function useUserFetcher(userId: string) {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchUser(userId).then(data => {
      setUser(data);
      setLoading(false);
    });
  }, [userId]);

  return { user, loading };
}

// 💡 Presentational Component (UI 집중)
function UserProfile({ userId }: { userId: string }) {
  const { user, loading } = useUserFetcher(userId);

  if (loading) return <div>로딩 중...</div>;
  return <div>안녕하세요, {user?.name}님!</div>;
}
```

---

### 5.3 Compound Components 패턴 (유연한 UI 조합)

`<Select>`, `<Tabs>`, `<Accordion>`처럼 여러 연관 컴포넌트들이 암묵적인 상태를 공유하며 함께 일하는 고급 설계 패턴입니다. 사용자가 JSX 구조를 자유롭게 구성할 수 있습니다.

```tsx
// 💡 Compound Component 사용 예시
<Accordion defaultOpen="item-1">
  <Accordion.Item id="item-1">
    <Accordion.Header>1. 컴포넌트란 무엇인가요?</Accordion.Header>
    <Accordion.Content>UI를 구성하는 재사용 가능한 기본 단위입니다.</Accordion.Content>
  </Accordion.Item>
  <Accordion.Item id="item-2">
    <Accordion.Header>2. Props와 State의 차이는?</Accordion.Header>
    <Accordion.Content>Props는 부모에게 전달받는 불변 데이터이고 State는 내부 가변 데이터입니다.</Accordion.Content>
  </Accordion.Item>
</Accordion>
```

#### 🛠️ 구현 핵심 (React Context 활용)
부모 `Accordion`이 상태(`openId`, `toggle`)를 Context로 관리하고, 자식 서브 컴포넌트들(`Header`, `Content`)이 `useContext`를 통해 해당 상태를 구독합니다.

---

### 5.4 Controlled vs Uncontrolled Components (제어 vs 비제어 컴포넌트)

폼(Form) 요소를 다룰 때 상태를 관리하는 2가지 방식입니다.

| 구분 | 제어 컴포넌트 (Controlled) | 비제어 컴포넌트 (Uncontrolled) |
| :--- | :--- | :--- |
| **상태 저장소** | React Component State (`useState`) | DOM 노드 내부 자체 상태 |
| **값 접근 방식** | `value={state}` + `onChange={(e) => setState(...)}` | `ref.current.value` |
| **장점** | 즉각적인 유효성 검사, 실시간 입력 포맷팅, 상태 동기화 용이 | 리렌더링 최소화, 기존 DOM 라이브러리 연동 용이, 단순 폼 구현에 빠름 |
| **단점** | 키 입력마다 컴포넌트 재렌더링 발생 | 즉각적 반응형 유효성 검사 구현이 상대적으로 번거로움 |

---

## 6. 렌더링 흐름과 Performance Optimization

### 6.1 React의 3단계 렌더링 과정 (Trigger → Render → Commit)

```
 [ 1. Trigger Phase ] ──> [ 2. Render Phase ] ──> [ 3. Commit Phase ]
 (상태 변경 이벤트 발생)   (가상 DOM 생성 & Diffing)   (실제 DOM 조작 및 화면 표시)
```

1. **Trigger Phase (렌더링 유발)**:
   - initial render(초기 마운트) 또는 `setState()`, `useReducer()` 호출 등으로 렌더링이 예약됨.
2. **Render Phase (렌더 단계)**:
   - React가 컴포넌트를 호출하여 새로운 Virtual DOM 트리를 계산.
   - 이전 Virtual DOM 트리와 새로운 Virtual DOM 트리를 **Diffing**하여 변경 사항(Reconciliation) 추적. (이 단계에서는 브라우저 DOM 조작이 안 일어남!)
3. **Commit Phase (커밋 단계)**:
   - React가 계산된 변경사항을 실제 DOM에 적용 (`appendChild`, `setAttribute` 등).
   - 브라우저가 화면을 새로 그림 (Reflow / Repaint).

---

### 6.2 불필요한 리렌더링 방지 (`React.memo`, `useCallback`, `useMemo`)

1. **`React.memo`**: 컴포넌트의 Props가 변경되지 않았다면 이전 렌더 결과를 재사용(Memoization)합니다.
2. **`useMemo`**: 복잡한 계산 결과값을 기억하여 재계산을 방지합니다.
3. **`useCallback`**: 함수 객체의 생성 및 재할당을 방지하여 자식 컴포넌트에 새로운 함수 참조가 전달되는 것을 막습니다.

```tsx
// 자식 컴포넌트 Memoization
const ExpensiveChild = React.memo(({ onItemClick }: { onItemClick: (id: number) => void }) => {
  console.log('자식 렌더링됨!');
  return <button onClick={() => onItemClick(1)}>아이템 클릭</button>;
});

// 부모 컴포넌트
function Parent() {
  const [count, setCount] = useState(0);

  // useCallback을 써야 Parent가 재렌더링되어도 onItemClick 함수 참조값이 유지됨!
  const handleClick = useCallback((id: number) => {
    console.log('클릭됨', id);
  }, []);

  return (
    <div>
      <button onClick={() => setCount(count + 1)}>Parent Counter: {count}</button>
      <ExpensiveChild onItemClick={handleClick} />
    </div>
  );
}
```

---

### 6.3 `key` Props의 중요성과 Reconciliation 과정

리스트를 렌더링할 때 React는 각 요소에 **고유한 `key`**를 요구합니다.

#### ❌ `key`로 배열 인덱스(`index`)를 사용할 때 발생하는 문제
배열의 요소가 중간에 추가/삭제/순서변경될 경우 인덱스가 밀리게 되어 React가 어떤 요소가 변경되었는지 착각하고 잘못된 DOM을 재사용하거나 전체 리스트를 재렌더링하는 심각한 성능/버그 문제를 유발합니다.

#### ✅ 반드시 고유한 데이터 ID (`item.id`)를 `key`로 지정해야 합니다!

---

## 7. 컴포넌트 패턴 대분류 요약 비교표

| 패턴 이름 | 주요 목적 및 특징 | 추천 유스케이스 |
| :--- | :--- | :--- |
| **Container-Presenter** | 로직과 UI 분리 (과거 패턴) | 복잡한 도메인 로직과 순수 UI 컴포넌트 명확 분리 필요 시 |
| **Custom Hooks Pattern** | 로직의 캡슐화 및 함수 단위 재사용 | API 데이터 패칭, 폼 처리, 미디어 쿼리 상태 공유 |
| **Compound Components** | 부모-자식 컴포넌트 간 암묵적 상태 공유 | `<Tabs>`, `<Accordion>`, `<Modal>`, `<Select>` UI 키트 |
| **Controlled Components** | React State 기반 Form 관리 | 실시간 폼 유효성 검사, 입력값 포맷팅 필터 |
| **Uncontrolled Components** | DOM Ref 기반 Form 관리 | 단순 제출 폼, 대규모 폼 필드 성능 최적화 |

---

## 8. 프로젝트 내 실습 코드 가이드

본 디렉토리(`front/2.react-component`)에는 위 이론들을 직접 조작하며 체득할 수 있는 대화형 React 애플리케이션이 포함되어 있습니다.

### 실행 방법
```bash
# 1. 의존성 설치
npm install

# 2. 개발 서버 실행
npm run dev
```

### 실습 애플리케이션 주요 데모 코너
1. **Class vs Function Component 카운터 비교 대시보드**
   - 클래스형 생명주기 이벤트 로그와 함수형 `useEffect` 실행 순서를 실시간 시각화합니다.
2. **Props & State 불변성(Immutability) 실험실**
   - 불변성을 지켰을 때와 파괴했을 때의 렌더링 유무 차이를 직접 버튼 클릭으로 실험합니다.
3. **Compound Components (Accordion) 데모**
   - Context API를 통해 암묵적으로 상태를 공유하는 복합 컴포넌트의 동작 구조를 조작해봅니다.
4. **Controlled vs Uncontrolled Form 비교**
   - 실시간 State 바인딩 Form과 `useRef` 기반 Form의 렌더링 횟수 차이를 카운터 배지로 확인합니다.
