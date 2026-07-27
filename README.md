# 🚀 Full-Stack & DevOps Personal Study Repository

혼자서 백엔드(Back-end), 프론트엔드(Front-end), 데브옵스(DevOps) 전반의 현대적인 기술 스택을 직접 실습하고 학습하기 위한 개인 스터디 모노레포(Monorepo) 성격의 저장소입니다.

---

## 🛠 Tech Stack & Key Focus Areas

| 영역 | 주요 기술 스택 | 핵심 학습 목표 |
| :--- | :--- | :--- |
| **Backend** (`/back`) | **Kotlin**, **Spring Boot** | • Kotlin Idiomatic Code 작성<br>• Spring Boot 3.x RESTful API 구축 및 아키텍처 설계<br>• JPA / RDB & NoSQL 연동 및 테스트 코드 작성 |
| **Frontend** (`/front`) | **React**, **TypeScript** | • React 최신 컴포넌트 패턴 및 State Management 학습<br>• 모던 UI/UX 스타일링 및 성능 최적화<br>• API 연동 및 클라이언트 상태 관리 |
| **DevOps** (`/devops`) | **Kubernetes**, **Docker** | • 컨테이너화 및 Docker Image 빌드 최적화<br>• 쿠버네티스 Manifest(Deployment, Service, Ingress 등) 작성 및 클러스터 배포<br>• CI/CD 파이프라인 및 인프라 자동화 구성 |

---

## 📁 Directory Structure

```text
study/
├── back/        # Kotlin + Spring Boot 중심 백엔드 프로젝트 및 학습 코드
├── front/       # React 중심 프론트엔드 프로젝트 및 학습 코드
└── devops/      # Kubernetes(kube) Manifest, Helm 차트, Docker 및 인프라 설정
```

---

## 🎯 학습 및 운영 규칙 (Rules & Workflows)

1. **도메인 단위 학습**: 각 영역(`back`, `front`, `devops`)은 필요에 따라 독립적인 서브 프로젝트나 모듈 형태로 확장합니다.
2. **코드 품질 & 문서화**: 새로 학습하거나 구현한 중요 개념은 코드 주석 및 소디렉터리 내부 README로 정리합니다.
3. **AI 어시스턴트 협업**: 프로젝트 내 AI 개발 도 도우미 규칙은 [`GEMINI.md`](./GEMINI.md)에 기술되어 있으며, 이를 준수하여 코드를 관리합니다.
