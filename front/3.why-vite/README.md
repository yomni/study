# ⚡ 3. Why Vite? - 차세대 프론트엔드 번들러 & 빌드 도구

본 디렉토리는 기존 모듈 번들러(Webpack, Rollup, Parcel 등)의 한계와 웹 발전사를 이해하고, **Vite가 어떻게 브라우저의 Native ESM과 Go 기반 Esbuild를 활용하여 번들링 속도 혁신을 이루었는지** 체계적으로 학습하고 실습하는 프로젝트입니다.

---

## 📚 학습 가이드 문서

상세한 이론 설명과 아키텍처 비교는 [`WHY_VITE.md`](./WHY_VITE.md) 문서에서 제공됩니다.

### 목차 요약
1. **웹 모듈화의 역사 (IIFE → CommonJS → AMD/UMD → Native ESM)**
2. **기존 번들러(Webpack)의 번들 기반 개발 환경과 한계**
3. **Vite의 등장 및 핵심 작동 메커니즘**
   - 브라우저 Native ESM 기반의 On-demand 로딩
   - Esbuild를 이용한 초고속 사전 번들링 (Pre-bundling)
   - $O(1)$ 수준의 Instant HMR (Hot Module Replacement)
   - Production Build에서의 Rollup (Rolldown) 전략
4. **Webpack vs Vite 심층 구획 비교 (Cold Start, HMR, Config, Ecosystem)**
5. **Vite 핵심 아키텍처 및 고급 기능 (import.meta, Env, CSS, Plugins)**
6. **프로젝트 실습 애플리케이션 가이드**

---

## 💻 실습 앱 실행 방법

```bash
# 1. 의존성 패키지 설치
npm install

# 2. 로컬 개발 서버 실행 (Vite Native ESM Dev Server)
npm run dev

# 3. 코드 린트 검사 (Oxlint)
npm run lint

# 4. 프로덕션 빌드 (Rollup + TS 검증)
npm run build
```

실습 애플리케이션에서는 Webpack과 Vite의 개발 서버 동작 시뮬레이션, Native ESM의 네트워크 요청 시각화, HMR 생명주기 및 `import.meta.env` 환경변수 처리 인터랙티브 데모를 직접 체험할 수 있습니다.
