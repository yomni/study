# 🧠 4. State Management - React 상태 관리 심화

본 디렉토리는 컴포넌트 트리가 커질 때 발생하는 **Prop Drilling** 문제를 시작으로, **Context API의 리렌더링
함정**, **Zustand/Jotai류 외부 Store가 이를 해결하는 방식(`useSyncExternalStore`)**, 그리고 **Client
State와 Server State의 근본적인 차이**를 체계적으로 학습하고 실습하는 프로젝트입니다.

---

## 📚 학습 가이드 문서

상세한 이론 설명과 코드 비교는 [`STATE_MANAGEMENT.md`](./STATE_MANAGEMENT.md) 문서에서 제공됩니다.

### 목차 요약
1. **상태 관리가 필요한 이유 (Prop Drilling)**
2. **Context API와 숨겨진 리렌더링 함정** (단일 Context vs 분리된 Context)
3. **외부 Store 패턴** (`useSyncExternalStore`로 만든 미니 Store, Zustand/Jotai 동작 원리)
4. **Client State vs Server State** (캐싱, 경쟁 상태, React Query/SWR이 필요한 이유)
5. **상태 관리 방식 선택 가이드**
6. **프로젝트 실습 애플리케이션 가이드**

---

## 💻 실습 앱 실행 방법

```bash
# 1. 의존성 패키지 설치
npm install

# 2. 로컬 개발 서버 실행
npm run dev

# 3. 코드 린트 검사 (Oxlint)
npm run lint

# 4. 프로덕션 빌드
npm run build
```

실습 애플리케이션에서는 Prop Drilling과 Context의 코드 구조 비교, Context 분리 여부에 따른 리렌더링 배지
변화, 직접 구현한 외부 Store의 선택적 구독 동작, 그리고 응답 지연이 다른 요청 두 개로 재현하는 경쟁 상태
(Race Condition) 버그와 그 해결 과정을 인터랙티브하게 체험할 수 있습니다.
