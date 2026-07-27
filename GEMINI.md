# 🤖 GEMINI.md - Project-Specific Guidelines

이 파일은 `study` 저장소에서 개발 작업 및 코딩 보조를 수행할 때 준수해야 하는 규칙과 가이드라인을 정의합니다.

---

## 📌 1. 영역별 기술 컨벤션 및 개발 지침

### 🟢 Backend (`/back`) - Kotlin & Spring Boot
- **언어 및 프레임워크**: Kotlin, Spring Boot 3.x
- **코딩 스타일**:
  - Kotlin Idiomatic 패턴 적용 (가변 변수 `var` 축소 및 `val` 적극 사용, 불변성 보장).
  - Scope functions (`let`, `apply`, `run`, `also`, `takeIf`)을 명확한 의도로 사용.
  - Spring DI 사용 시 생성자 주입(Constructor Injection) 원칙 준수.
- **아키텍처 & 설계**:
  - 계층형 아키텍처(Controller - Service - Repository/Domain) 분리.
  - 예외 처리는 `@RestControllerAdvice` 및 커스텀 예외 클래스로 중앙 집중화.
  - 단위/통합 테스트 코드 작성 (JUnit 5, Mockk / Kotest 활용).

---

### 🔵 Frontend (`/front`) - React
- **언어 및 프레임워크**: React (with TypeScript/Modern JavaScript)
- **코딩 스타일**:
  - 함수형 컴포넌트(Functional Component) 및 React Hooks 사용.
  - UI 컴포넌트 분리 및 재사용 가능한 설계 적용.
  - 직관적이고 세련된 스타일링(Modern CSS / CSS Modules / Tailwind 등) 적용.
- **품질 관리**:
  - Props 타입 검증 및 불변성 관리.
  - 불필요한 리렌더링 방지 (`useMemo`, `useCallback` 적절 활용).

---

### 🟠 DevOps (`/devops`) - Kubernetes & Infrastructure
- **주요 스택**: Kubernetes (`/devops/kube`), Docker, Helm, CI/CD
- **매니페스트 & 설정 작성 규칙**:
  - 쿠버네티스 YAML 작성 시 명확한 `namespace`, `labels`, `annotations` 정의.
  - Resource Limit 및 Request 설정 포함 (`resources.limits`, `resources.requests`).
  - 환경변수 및 비밀 정보는 `ConfigMap` 및 `Secret`으로 분리.
  - Declarative (선언적) 방식의 YAML 관리.

---

## ⚙️ 2. 공통 코드 작성 & 커뮤니케이션 규칙

1. **안전한 코드 변경**: 기존의 작업 코드를 변경하거나 수정할 때는 기존 코드의 의도와 주석을 보존합니다.
2. **테스트 및 검증**: 코드 작성 후 가능한 경우 빌드 및 테스트 커맨드를 실행하여 정상 작동 여부를 검증합니다.
3. **간결한 문서화**: 새로 생성되는 모듈이나 주요 기능은 README 및 관련 문서를 통해 간결하게 기술합니다.

---

## 📝 3. Git 커밋 메시지 컨벤션 (Git Commit Convention)

본 프로젝트는 Udacity Style 기반의 Git 커밋 메시지 컨벤션을 준수합니다.

### 1) 커밋 메시지 구조
```text
<type>: <subject>

<body>

<footer>
```

### 2) 커밋 타입 (`type`)
- **표준 타입**:
  - `feat`: 새로운 기능 추가
  - `fix`: 버그 수정
  - `docs`: 문서 수정 (README 등 문서 파일 수정, 코드 주석 제외)
  - `style`: 코드 포맷팅, 세미콜론 누락 등 (코드 로직 변화 없음)
  - `refactor`: 기능 변화 없이 코드 구조 개선
  - `test`: 테스트 코드 추가 및 수정
  - `chore`: 빌드/패키지 설정 등 코드 기능과 직접 관련 없는 작업
- **확장 타입**:
  - `design`: UI/UX, CSS 스타일 및 디자인 수정
  - `comment`: 주석 추가 및 수정
  - `rename`: 파일 또는 폴더 이름 변경 / 이동
  - `remove`: 파일 또는 폴더 삭제

### 3) 작성 규칙
- **제목 (`subject`)**:
  - 50자 이내로 간결하고 명확하게 작성.
  - 첫 글자는 소문자로 시작.
  - 끝에 마침표(`.`)를 붙이지 않음.
- **본문 (`body`)**:
  - 제목만으로 설명이 부족할 때 (무엇을, 왜 변경했는지) 상세히 작성 (선택 사항).
  - 제목과 본문 사이에 한 줄 공백 추가.
- **꼬리표 (`footer`)**:
  - 이슈 번호나 참고 커밋 ID 등 언급 (선택 사항, 예: `Fixes: #123`).
  - 본문과 꼬리표 사이에 한 줄 공백 추가.

### 4) 커밋 기본 원칙
1. **의미 있는 단위로 커밋**: 지나치게 많은 변경 사항을 하나의 커밋에 담지 않음.
2. **명확하게 작성**: 커밋 메시지 제목만 보고도 변경 요점을 파악할 수 있도록 작성.
3. **협업 정보 포함**: 코드 변화로 영향을 받는 모듈, 관련 이슈 등을 명시.

