# 🚀 왜 React인가? (Why React? & Front-End Evolution History)

> 본 문서는 프론트엔드 역사의 흐름(jQuery → MVC/양방향 바인딩 → React)에 따른 문제점과 해결책을 살펴보고, React가 웹 패러다임을 어떻게 바꾸었는지 코드 중심으로 다루는 학습 자료입니다.

---

## 📌 목차
1. [웹 프론트엔드의 역사와 한계 (Before React)](#1-웹-프론트엔드의-역사와-한계-before-react)
   - 1.1 정적 문서와 AJAX, 그리고 jQuery 시대
   - 1.2 jQuery 방식의 근본적 문제점 (Spaghetti Code & Mutable DOM)
   - 1.3 1세대 SPA와 양방향 바인딩 (AngularJS, Backbone)
2. [React의 등장과 패러다임의 혁신 (Why React?)](#2-react의-등장과-패러다임의-혁신-why-react)
   - 2.1 UI = f(state) : 선언형 프로그래밍 (Declarative UI)
   - 2.2 단방향 데이터 흐름 (Unidirectional Data Flow)
   - 2.3 Virtual DOM과 Reconciliation (재조정)
3. [React의 주요 발전사 (Evolution of React)](#3-react의-주요-발전사-evolution-of-react)
   - 3.1 클래스형 컴포넌트 시대 (Class Component & Lifecycle)
   - 3.2 React Hooks의 혁명 (React 16.8+)
   - 3.3 Concurrent React & Automatic Batching (React 18)
   - 3.4 React Server Components & React 19
4. [프론트엔드 기술 세대별 핵심 비교](#4-프론트엔드-기술-세대별-핵심-비교)
5. [프로젝트 내 실습 코드 가이드](#5-프로젝트-내-실습-코드-가이드)

---

## 1. 웹 프론트엔드의 역사와 한계 (Before React)

### 1.1 정적 문서와 AJAX, 그리고 jQuery 시대

초기의 웹은 서버에서 완결된 HTML을 받아 단순히 뿌려주는 **정적 문서 웹(Static Document Web)**이었습니다.
이후 `XMLHttpRequest` (AJAX)의 등장으로 페이지 전체를 새로고침하지 않고 일부 데이터만 서버에서 받아와 동적으로 DOM을 업데이트하는 **웹 애플리케이션(Web Application)**의 형태가 갖추어졌습니다.

이 시기를 지배한 대표적인 도구가 **jQuery**였습니다. jQuery는 파편화된 크로스 브라우저 DOM API를 통합하고 간결한 셀렉터(Selector) API를 제공했습니다.

```javascript
// 💡 jQuery 스타일: DOM을 직접 탐색하고 변경하는 명령형(Imperative) 방식
$(document).ready(function() {
  let count = 0;

  $('#increment-btn').on('click', function() {
    count++;
    // DOM을 직접 조작하여 텍스트 갱신
    $('#count-display').text('현재 카운트: ' + count);
    
    if (count >= 10) {
      $('#warning-msg').removeClass('hidden').text('카운터가 10 이상입니다!');
    }
  });
});
```

---

### 1.2 jQuery 방식의 근본적 문제점 (Spaghetti Code & Mutable DOM)

jQuery 방식은 앱이 커질수록 다음과 같은 치명적인 문제를 일으켰습니다.

1. **상태(State)와 뷰(View/DOM)의 결합**: 
   - 데이터(값)의 진실의 근원(Source of Truth)이 JS 변수와 DOM 요소 텍스트 양쪽에 파편화되었습니다.
   - 예: 특정 UI의 현재 상태를 알기 위해 `$('#user-status').hasClass('active')`처럼 **DOM 자체를 상태 저장소로 사용**하게 됨.
2. **명령형 UI(Imperative UI)의 복잡도 급증**:
   - 상태 변화가 일어날 때마다 **어떤 DOM 요소를, 어떻게 추가/삭제/변경해야 하는지 모든 절차를 개발자가 직접 작성**해야 했습니다.
   - 조건이 추가될 때마다 DOM 변경 분기 로직이 기하급수적으로 늘어나 스파게티 코드가 됩니다.
3. **성능 문제 (Direct DOM Reflow / Repaint)**:
   - DOM 조작은 브라우저 엔진에서 가장 비용이 큰 연산 중 하나입니다. 불필요한 DOM 조작이 반복되면 프레임 드랍이 발생합니다.

---

### 1.3 1세대 SPA와 양방향 바인딩 (AngularJS, Backbone)

jQuery의 한계를 극복하기 위해 등장한 1세대 SPA 프레임워크(AngularJS, Backbone.js 등)는 **MV* 구조**와 **양방향 데이터 바인딩(Two-Way Data Binding)**을 도입했습니다.

```html
<!-- 💡 AngularJS 1.x 양방향 바인딩 예시 -->
<div ng-app="myApp" ng-controller="CountController">
  <!-- input의 입력이 $scope.name을 변경하고, $scope.name 변경이 p 태그를 자동 갱신 -->
  <input type="text" ng-model="name">
  <p>안녕하세요, {{ name }}님!</p>
</div>
```

#### 🚨 양방향 데이터 바인딩의 부작용
- **데이터 흐름의 추적 난해성 (Cascade Updates)**: Model이 View를 변경하고, 그 View의 변화가 또 다른 Model을 변경하는 **핑퐁 현상**이 일어났습니다.
- 대규모 애플리케이션에서 특정 값이 왜 변경되었는지 디버깅하기가 지옥에 가까웠습니다 (Facebook의 알림 카운터 버그 사건).

---

## 2. React의 등장과 패러다임의 혁신 (Why React?)

2013년, Facebook(현 Meta)은 **"상태가 바뀌면 기존 DOM을 전부 버리고 새로 렌더링하면 어떨까?"**라는 파격적인 아이디어에서 React를 공개했습니다.

---

### 2.1 UI = f(state) : 선언형 프로그래밍 (Declarative UI)

React의 핵심 개념은 **UI는 상태(State)의 함수**라는 공식입니다.

$$\text{UI} = f(\text{State})$$

개발자는 "DOM을 어떻게 바꿀지(How)" 명령하지 않고, **"어떤 상태일 때 UI가 어떻게 보여야 하는지(What)"를 선언**하기만 합니다.

```tsx
// 💡 React 방식: 선언형(Declarative) 방식
function Counter() {
  const [count, setCount] = useState(0);

  // 개발자는 DOM 수정 방식을 작성하지 않음.
  // 오직 state(count)에 따른 결과 UI만을 선언!
  return (
    <div>
      <p>현재 카운트: {count}</p>
      {count >= 10 && <p className="warning">카운터가 10 이상입니다!</p>}
      <button onClick={() => setCount(count + 1)}>증가</button>
    </div>
  );
}
```

---

### 2.2 단방향 데이터 흐름 (Unidirectional Data Flow)

React는 양방향 데이터 바인딩을 버리고 **단방향 데이터 흐름**을 채택했습니다.

```
 [ Action / Event ]  ──>  [ State (상태 변경) ]  ──>  [ UI / View (재렌더링) ]
        ▲                                                      │
        └──────────────────────────────────────────────────────┘
```

- **Props Down, Events Up**: 데이터는 부모에서 자식으로만 흐르고(Props), 자식은 이벤트를 호출하여 부모의 상태 변경을 요청합니다.
- 데이터의 예측 가능성(Predictability)이 획기적으로 향상되고 디버깅이 쉬워졌습니다.

---

### 2.3 Virtual DOM과 Reconciliation (재조정)

"상태가 바뀔 때마다 전체 UI를 다시 그리면 브라우저가 너무 느려지지 않을까?"라는 문제를 해결한 것이 **Virtual DOM(가상 DOM)**입니다.

1. **In-Memory DOM Tree**: 상태 변경 시 React는 메모리 상에 가벼운 JS 객체 형태의 Virtual DOM 트리를 새로 만듭니다.
2. **Diffing Algorithm**: 이전 Virtual DOM과 새 Virtual DOM을 빠르게 비교($O(N)$ 알고리즘)하여 **변경된 부분(Delta)만 탐지**합니다.
3. **Batching & Reconciliate**: 변경된 부분만 실제 DOM에 최소한의 조작(Reflow/Repaint 최소화)으로 일괄 반영(Patch)합니다.

---

## 3. React의 주요 발전사 (Evolution of React)

React는 지난 10여 년간 웹 표준과 요구사항에 맞추어 끊임없이 발전해 왔습니다.

---

### 3.1 클래스형 컴포넌트 시대 (Class Component & Lifecycle)

초기 React는 ES6 `Class` 기반 컴포넌트를 사용하였으며, 컴포넌트 생명주기(Lifecycle) 메소드로 비동기 처리 및 상태 관리를 수행했습니다.

```tsx
// 💡 React 15 이전 클래스형 컴포넌트 예시
import React, { Component } from 'react';

interface Props { title: string; }
interface State { count: number; data: string | null; }

class LegacyCounter extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = { count: 0, data: null };
  }

  componentDidMount() {
    // 마운트 시 데이터 패칭
    fetchData().then(res => this.setState({ data: res }));
  }

  componentDidUpdate(prevProps: Props, prevState: State) {
    // 카운트 변경 추적
    if (prevState.count !== this.state.count) {
      console.log(`Count changed to ${this.state.count}`);
    }
  }

  render() {
    return (
      <div>
        <h1>{this.props.title}</h1>
        <p>Count: {this.state.count}</p>
        <button onClick={() => this.setState({ count: this.state.count + 1 })}>
          Increment
        </button>
      </div>
    );
  }
}
```

#### ⚠️ 클래스형 컴포넌트의 한계
- **로직 재사용의 어려움**: 상태 로직을 공유하기 위해 HOC(Higher-Order Component)나 Render Props 패턴을 남발하다가 **Wrapper Hell (중첩 지옥)**에 빠짐.
- **관심사 분리의 실패**: `componentDidMount` 하나 안에 이벤트 리스너 등록, 데이터 패칭, 타이머 설정 등 연관 없는 로직이 섞임.
- **`this` 바인딩 이슈** 및 문법적 보일러플레이트 증가.

---

### 3.2 React Hooks의 혁명 (React 16.8 / 2019년)

React 16.8에서 도입된 **Hooks**는 클래스 없이 함수형 컴포넌트에서 상태와 생명주기 기능을 사용할 수 있게 만든 대혁신이었습니다.

```tsx
// 💡 React Hooks를 활용한 동일한 로직 구현 & Custom Hook 분리
import { useState, useEffect } from 'react';

// Custom Hook: 로직을 완벽하게 재사용 가능!
function useFetchData() {
  const [data, setData] = useState<string | null>(null);
  useEffect(() => {
    fetchData().then(setData);
  }, []);
  return data;
}

export function ModernCounter({ title }: { title: string }) {
  const [count, setCount] = useState(0);
  const data = useFetchData();

  useEffect(() => {
    console.log(`Count changed to ${count}`);
  }, [count]);

  return (
    <div>
      <h1>{title}</h1>
      <p>Count: {count}</p>
      <p>Data: {data}</p>
      <button onClick={() => setCount(prev => prev + 1)}>Increment</button>
    </div>
  );
}
```

#### ✨ Hooks가 가져온 이점
1. **상태 로직의 완벽한 재사용 (Custom Hooks)**: HOC 없이 로직만 함수로 추출하여 공유 가능.
2. **관심사의 명확한 분리**: 연관된 로직끼리 하나의 `useEffect` 내에 모아서 관리 가능.
3. **가볍고 간결한 함수형 컴포넌트 코딩**.

---

### 3.3 Concurrent React & Automatic Batching (React 18 / 2022년)

React 18은 동기적 렌더링(Synchronous Rendering)의 블로킹 한계를 극복하고 **동시성(Concurrency)** 메커니즘을 도입했습니다.

- **Automatic Batching**: Promise, setTimeout 내부에서도 상태 변경을 하나로 묶어(Batch) 렌der 회수를 획기적으로 줄임.
- **`useTransition` / `useDeferredValue`**: 긴박한 UI 업데이트(키보드 입력 등)와 무거운 렌더링 작업(대규모 리스트 필터링)의 우선순위를 나누어 메인 스레드가 멈추지 않도록 조절.

```tsx
import { useState, useTransition } from 'react';

function SearchFilter() {
  const [query, setQuery] = useState('');
  const [list, setList] = useState<string[]>([]);
  const [isPending, startTransition] = useTransition();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    // 1. 긴급한 업데이트: 타이핑 반응
    setQuery(e.target.value);

    // 2. 비긴급 업데이트: 무거운 리스트 필터링은 우선순위를 낮춤 (UI 블로킹 방지)
    startTransition(() => {
      setList(filterHugeList(e.target.value));
    });
  };

  return (
    <div>
      <input type="text" value={query} onChange={handleChange} />
      {isPending ? <p>리스트 로딩 중...</p> : <List items={list} />}
    </div>
  );
}
```

---

### 3.4 React Server Components (RSC) & React 19 (현대)

현대의 React는 CSR(Client-Side Rendering)의 한계를 넘어 서버와 클라이언트의 이점을 결합하는 방향으로 확장되었습니다.

- **React Server Components (RSC)**: 번들 사이즈에 포함되지 않고 서버에서 미리 실행되어 Zero-Bundle-Size 패칭을 가능케 함.
- **React 19**: `useActionState`, `useFormStatus`, `useOptimistic` 등 서버 액션과 폼 관리를 위한 파워풀한 내장 Hook 도입.

---

## 4. 프론트엔드 기술 세대별 핵심 비교

| 구분 | 1세대: jQuery 시대 | 2세대: AngularJS / Backbone | 3세대: React (Hooks ~ 현재) |
| :--- | :--- | :--- | :--- |
| **패러다임** | 명령형 (Imperative) | 양방향 바인딩 (MV*) | 선언형 (Declarative) + 단방향 흐름 |
| **DOM 조작** | 개발자가 직접 DOM 조작 (`$('#id').html()`) | 양방향 바인딩 + Watcher | Virtual DOM + Diffing & Reconciliation |
| **상태 관리** | DOM 자체 또는 전역 JS 변수 | `$scope` 데이터 모델 | Component State, Context, Redux, Zustand 등 |
| **데이터 흐름** | 추적 어려움 (파편화) | 양방향 (Model ↔ View 핑퐁) | 단방향 (Props Down, Events Up) |
| **코드 재사용** | 스크립트 파일 분리, jQuery 플러그인 | Controller, Directive | Component & Custom Hooks |
| **주요 한계** | 대규모 앱 유지보수 불가 (스파게티) | 디버깅 난해, Digest Loop 성능 저하 | 초기 학습 곡선 및 상태 관리 설계 고려 필요 |

---

## 5. 프로젝트 내 실습 코드 가이드

본 디렉토리(`front/1.why-react`)는 Vite + React + TypeScript 환경으로 설정되어 있습니다.

### 실행 방법
```bash
# 1. 의존성 설치 (완료됨)
npm install

# 2. 개발 서버 실행
npm run dev
```

### 추천 학습 순서
1. `src/App.tsx` 파일에서 선언형 UI 작성 방식을 확인해 보세요.
2. 상태(State)가 변경될 때 React가 어떻게 필요한 부분만 재렌더링하는지 React Developer Tools(크롬 확장 프로그램)의 **Highlight updates** 옵션을 키고 관찰해 보세요.
3. 직접 Custom Hook을 작성해보면서 과거 클래스형 라이프사이클과의 코드 구조 차이를 느껴보세요!
