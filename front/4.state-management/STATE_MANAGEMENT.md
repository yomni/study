# 🧠 State Management (React 상태 관리 심화)

> 본 문서는 컴포넌트가 늘어날수록 필연적으로 마주치는 **상태 공유 문제**를 Prop Drilling → Context API → 외부
> Store(Zustand/Jotai류) 순서로 살펴보고, 마지막으로 **Client State와 Server State를 구분**해야 하는 이유를
> 코드 중심으로 다루는 학습 자료입니다.

---

## 📌 목차
1. [상태 관리가 필요한 이유: Prop Drilling](#1-상태-관리가-필요한-이유-prop-drilling)
2. [Context API와 숨겨진 리렌더링 함정](#2-context-api와-숨겨진-리렌더링-함정)
3. [외부 Store 패턴 (Zustand/Jotai가 동작하는 방식)](#3-외부-store-패턴-zustandjotai가-동작하는-방식)
4. [Client State vs Server State](#4-client-state-vs-server-state)
5. [상태 관리 방식 선택 가이드](#5-상태-관리-방식-선택-가이드)
6. [프로젝트 내 실습 코드 가이드](#6-프로젝트-내-실습-코드-가이드)

---

## 1. 상태 관리가 필요한 이유: Prop Drilling

React의 데이터는 [`1.why-react`](../1.why-react/WHY_REACT.md)에서 다룬 대로 **Props Down, Events Up**의
단방향 흐름을 따릅니다. 문제는 최상위 컴포넌트가 가진 상태를 5단계, 10단계 아래의 자식이 필요로 할 때
발생합니다.

```tsx
// 💡 Prop Drilling: 중간 컴포넌트들은 theme을 쓰지 않지만 계속 전달만 함
function App() {
  const [theme, setTheme] = useState<'light' | 'dark'>('dark');
  return <Page theme={theme} onToggle={() => setTheme(t => t === 'dark' ? 'light' : 'dark')} />;
}

function Page({ theme, onToggle }: ThemeProps) {
  return <Layout theme={theme} onToggle={onToggle} />; // theme 사용 안 함
}

function Layout({ theme, onToggle }: ThemeProps) {
  return <Sidebar theme={theme} onToggle={onToggle} />; // theme 사용 안 함
}

function Sidebar({ theme, onToggle }: ThemeProps) {
  return <button onClick={onToggle}>테마: {theme}</button>; // 여기서만 실제로 사용
}
```

### 🚨 Prop Drilling의 대가
- **중간 컴포넌트의 오염**: `Page`, `Layout`은 `theme`을 전혀 사용하지 않지만 타입 시그니처와 JSX에 계속
  등장해야 합니다.
- **리팩터링 비용**: 트리 중간에 컴포넌트를 하나 끼워 넣거나 순서를 바꿀 때마다 prop 배선을 다시 연결해야
  합니다.
- **응집도 저하**: "이 값을 누가 실제로 쓰는가"를 파악하기 위해 트리를 몇 단계씩 오르내려야 합니다.

---

## 2. Context API와 숨겨진 리렌더링 함정

React가 공식 제공하는 해결책은 **Context API**입니다. Provider가 감싼 트리 안에서는 깊이와 무관하게
`useContext`로 값을 바로 꺼내 쓸 수 있습니다.

```tsx
const ThemeContext = createContext<ThemeCtxValue | null>(null);

function App() {
  const [theme, setTheme] = useState<'light' | 'dark'>('dark');
  const value = useMemo(() => ({ theme, toggleTheme: () => setTheme(t => t === 'dark' ? 'light' : 'dark') }), [theme]);

  return (
    <ThemeContext.Provider value={value}>
      <Page /> {/* theme을 몰라도 됨 */}
    </ThemeContext.Provider>
  );
}

function Sidebar() {
  const { theme, toggleTheme } = useContext(ThemeContext)!;
  return <button onClick={toggleTheme}>테마: {theme}</button>;
}
```

### ⚠️ 그런데도 Context만으로는 부족한 이유

Context는 Prop Drilling을 없애주지만, **Provider의 `value`가 바뀌면 그 값을 구독하는 모든 컴포넌트가
리렌더링**됩니다. 이때 자주 저지르는 실수가 서로 무관한 여러 상태를 하나의 Context 값으로 묶는 것입니다.

```tsx
// ❌ count와 text를 하나의 Context로 묶음
const AppContext = createContext({ count: 0, text: '' });

function CountConsumer() {
  const { count } = useContext(AppContext); // count만 필요
  return <div>{count}</div>;
}

function TextConsumer() {
  const { text } = useContext(AppContext); // text만 필요
  return <div>{text}</div>;
}
```

`count`만 변경해도 `value` 객체 참조 자체가 바뀌기 때문에, **`text`만 구독하는 `TextConsumer`까지 함께
리렌더링**됩니다. React는 Context 값을 얕은 비교(`Object.is`)로만 판단하고, "이 값의 어느 필드가
바뀌었는지"는 알지 못하기 때문입니다.

#### ✅ 완화 방법: 상태 단위로 Context 분리

```tsx
const CountContext = createContext(0);
const TextContext = createContext('');
```

각 상태를 별도의 Context로 나누면 해당 값을 실제로 구독하는 컴포넌트만 리렌더링됩니다. 하지만 상태 종류가
많아질수록 Provider가 계속 중첩되는 **"Provider Hell"** 문제가 새로 생깁니다.

```tsx
<AuthContext.Provider value={auth}>
  <ThemeContext.Provider value={theme}>
    <CartContext.Provider value={cart}>
      <App />
    </CartContext.Provider>
  </ThemeContext.Provider>
</AuthContext.Provider>
```

---

## 3. 외부 Store 패턴 (Zustand/Jotai가 동작하는 방식)

Zustand, Jotai, Valtio 같은 라이브러리는 React Context 트리에 얹혀가는 대신, **모듈 스코프에 별도의
Store**를 두고 React 18의 `useSyncExternalStore`로 필요한 슬라이스만 선택(select)해 구독합니다.

```tsx
// 💡 Zustand가 내부적으로 하는 일과 본질적으로 같은 미니 Store
function createStore<T extends object>(initial: T) {
  let state = initial;
  const listeners = new Set<() => void>();

  return {
    getState: () => state,
    setState: (partial: Partial<T>) => {
      state = { ...state, ...partial };
      listeners.forEach(listener => listener());
    },
    subscribe: (listener: () => void) => {
      listeners.add(listener);
      return () => listeners.delete(listener);
    },
  };
}

const store = createStore({ count: 0, name: 'Zustand' });

function useStoreSelector<T extends object, S>(store: Store<T>, selector: (s: T) => S) {
  return useSyncExternalStore(store.subscribe, () => selector(store.getState()));
}

// 사용부: count가 바뀌어도 name만 select한 컴포넌트는 리렌더링되지 않음
const count = useStoreSelector(store, s => s.count);
```

### 🆚 Context vs 외부 Store 비교

| 구분 | Context API | 외부 Store (Zustand/Jotai 계열) |
| :--- | :--- | :--- |
| **상태 위치** | React 컴포넌트 트리 내부 (Provider) | 모듈 스코프 (React 트리 외부) |
| **리렌더링 단위** | Provider `value` 전체 (얕은 비교) | `selector`로 지정한 필드 단위 |
| **Provider Hell** | 상태 종류가 늘어날수록 중첩 심화 | Provider 불필요 (또는 최소화) |
| **React 트리 밖 접근** | 불가능 (Provider 내부에서만) | 가능 (이벤트 핸들러, 유틸 함수 등에서도 `getState()`) |
| **적합한 용도** | 낮은 빈도로 바뀌는 전역 값 (테마, 로케일, 인증 여부) | 자주 바뀌는 값, 선택적 구독이 중요한 경우 |

---

## 4. Client State vs Server State

상태 관리 라이브러리를 고를 때 가장 자주 놓치는 구분은 **"이 상태가 서버에서 온 데이터의 로컬 사본인가,
아니면 순수하게 이 화면만을 위한 값인가"** 입니다.

| 구분 | Client State | Server State |
| :--- | :--- | :--- |
| **소유권** | 클라이언트(UI)가 유일한 진실의 소스 | 서버가 진실의 소스, 클라이언트는 캐시된 사본을 보유 |
| **예시** | 모달 열림 여부, 사이드바 접힘, 폼 입력값 | 유저 목록, 게시글, 로그인 정보 |
| **필요한 기능** | 단순 `useState`/`useReducer`로 충분 | 캐싱, 로딩/에러 상태, 재요청(refetch), 만료(stale) 판정 |
| **동시성 이슈** | 거의 없음 | 여러 요청이 겹칠 때 **경쟁 상태(Race Condition)** 발생 가능 |

### 🚨 Server State를 `useEffect` + `useState`로만 다룰 때의 함정

```tsx
useEffect(() => {
  fetchUser(userId).then(setUser); // ❌ cleanup 없음
}, [userId]);
```

`userId`가 빠르게 여러 번 바뀌면(예: 1 → 2), 먼저 보낸 요청(`id=1`)의 응답이 나중에 도착할 수 있습니다.
이 경우 **더 오래된 응답이 최신 상태를 덮어써** 화면에 잘못된 데이터가 표시됩니다.

```tsx
useEffect(() => {
  const requestId = ++latestRequestRef.current;

  fetchUser(userId).then(data => {
    if (latestRequestRef.current !== requestId) return; // ✅ 최신 요청이 아니면 무시
    setUser(data);
  });
}, [userId]);
```

React Query, SWR 같은 라이브러리는 이런 **요청 취소/최신성 검증 + 캐싱 + 재검증(revalidation)**을 표준화된
API로 대신 처리해줍니다. 즉, 상태 관리 라이브러리(Zustand/Jotai/Redux)와 서버 상태 라이브러리(React
Query/SWR)는 서로 대체재가 아니라 **역할이 다른 도구**입니다.

---

## 5. 상태 관리 방식 선택 가이드

| 상황 | 추천 방식 |
| :--- | :--- |
| 부모-자식 1~2단계 사이의 짧은 데이터 전달 | Props |
| 낮은 빈도로 바뀌는 전역 값 (테마, 로케일, 인증 정보) | Context API |
| 자주 바뀌는 전역 값, 성능이 중요한 경우 | 외부 Store (Zustand, Jotai, Redux Toolkit) |
| 서버에서 가져온 데이터, 캐싱/재검증이 필요한 경우 | React Query, SWR |
| 컴포넌트 자신만 아는 로컬 UI 상태 | `useState` / `useReducer` |

---

## 6. 프로젝트 내 실습 코드 가이드

본 디렉토리(`front/4.state-management`)는 Vite + React + TypeScript 환경으로 설정되어 있습니다.

### 실행 방법
```bash
# 1. 의존성 설치
npm install

# 2. 개발 서버 실행
npm run dev
```

### 실습 애플리케이션 주요 데모 코너
1. **Prop Drilling vs Context API**: 중첩 깊이를 조절하며 두 방식의 코드 구조 차이를 직접 비교합니다.
2. **Context 리렌더링 실험실**: 하나로 뭉친 Context와 분리된 Context의 리렌더링 배지 숫자를 비교합니다.
3. **useSyncExternalStore 미니 Store**: Zustand류 라이브러리가 선택적 구독을 구현하는 원리를 직접 만들어봅니다.
4. **Client vs Server State**: 응답 지연이 다른 두 요청을 빠르게 연달아 보내 경쟁 상태 버그를 재현하고, 캐시와
   요청 가드로 해결하는 과정을 관찰합니다.
