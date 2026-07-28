# 🧩 2. React Component & Architecture

본 디렉토리는 React의 가장 핵심 단위인 **컴포넌트(Component)**의 개념, 동작 원리(JSX, Virtual DOM 렌더링 과정), 클래스형 vs 함수형 컴포넌트의 발전 과정, 데이터 다루기(Props, State, Children), 그리고 실무 컴포넌트 설계 패턴(Compound Component, Controlled/Uncontrolled, Custom Hooks 패턴)을 학습하고 실습하는 프로젝트입니다.

---

## 📚 학습 가이드 문서

상세한 이론 설명과 코드는 [`REACT_COMPONENT.md`](./REACT_COMPONENT.md) 문서에서 제공됩니다.

### 목차 요약
1. **React Component의 본질과 선언적 UI**
2. **JSX / TSX의 메커니즘과 동작 원리 (`React.createElement`)**
3. **Props vs State vs Children (컴포넌트 데이터 트라이앵글)**
4. **컴포넌트 유형 패러다임 (Class Component vs Function Component & Hooks)**
5. **컴포넌트 설계 패턴 (Compound Components, Container-Presenter, Controlled vs Uncontrolled)**
6. **렌더링 흐름과 최적화 (Trigger -> Render -> Commit & Memoization)**
7. **컴포넌트 패턴 비교 및 정리**

---

## 💻 실습 앱 실행 방법

```bash
# 1. 의존성 패키지 설치
npm install

# 2. 로컬 개발 서버 실행
npm run dev

# 3. 코드 린트 검사 (Oxlint)
npm run lint
```

실습 애플리케이션에서는 컴포넌트 패턴별 동작 방식, 렌더링 횟수 시각화, 불변성 변경 테스트, 복합 컴포넌트(Compound Component)의 대화형 데모를 직접 경험해 볼 수 있습니다.
