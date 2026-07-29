import { useState } from 'react';
import { Zap, Globe, Flame, Key, BookOpen, ExternalLink } from 'lucide-react';
import { BundlerSimulator } from './components/BundlerSimulator';
import { NativeEsmVisualizer } from './components/NativeEsmVisualizer';
import { HmrDemo } from './components/HmrDemo';
import { EnvInspector } from './components/EnvInspector';

type TabType = 'simulator' | 'esm' | 'hmr' | 'env';

export default function App() {
  const [activeTab, setActiveTab] = useState<TabType>('simulator');

  return (
    <div className="container">
      {/* Header Banner */}
      <header style={{ marginBottom: '40px' }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '8px' }}>
          <span className="badge badge-vite">CHAPTER 3</span>
          <span style={{ fontSize: '0.85rem', color: 'var(--text-muted)' }}>Front-End Build Tool Evolution</span>
        </div>

        <h1 className="header-title">
          ⚡ 3. Why Vite? (왜 Vite인가?)
        </h1>

        <p style={{ color: 'var(--text-muted)', fontSize: '1.1rem', maxWidth: '800px', lineHeight: '1.7' }}>
          브라우저의 <strong>Native ESM (ES Modules)</strong>과 Go 언어 기반 <strong>Esbuild</strong>의 사전 번들링 기술을 결합하여, 기존 Webpack의 번들링 타임 병목을 극복하고 초고속 개발 경험(DX)을 제공하는 Vite의 동작 원리를 탐구합니다.
        </p>

        <div style={{ marginTop: '20px', display: 'flex', gap: '12px', flexWrap: 'wrap' }}>
          <a
            href="./WHY_VITE.md"
            target="_blank"
            rel="noopener noreferrer"
            className="btn btn-primary"
            style={{ textDecoration: 'none' }}
          >
            <BookOpen size={18} />
            상세 이론 문서 (WHY_VITE.md) 읽기
            <ExternalLink size={14} />
          </a>
        </div>
      </header>

      {/* Tab Navigation */}
      <nav className="tab-nav">
        <button
          className={`tab-btn ${activeTab === 'simulator' ? 'active' : ''}`}
          onClick={() => setActiveTab('simulator')}
        >
          <Zap size={18} />
          1. Cold Start 시뮬레이터
        </button>

        <button
          className={`tab-btn ${activeTab === 'esm' ? 'active' : ''}`}
          onClick={() => setActiveTab('esm')}
        >
          <Globe size={18} />
          2. Native ESM 시각화
        </button>

        <button
          className={`tab-btn ${activeTab === 'hmr' ? 'active' : ''}`}
          onClick={() => setActiveTab('hmr')}
        >
          <Flame size={18} />
          3. HMR 핫 리로드 실습
        </button>

        <button
          className={`tab-btn ${activeTab === 'env' ? 'active' : ''}`}
          onClick={() => setActiveTab('env')}
        >
          <Key size={18} />
          4. import.meta.env 인스펙터
        </button>
      </nav>

      {/* Main Tab Content */}
      <main>
        {activeTab === 'simulator' && <BundlerSimulator />}
        {activeTab === 'esm' && <NativeEsmVisualizer />}
        {activeTab === 'hmr' && <HmrDemo />}
        {activeTab === 'env' && <EnvInspector />}
      </main>

      {/* Footer Info */}
      <footer style={{ marginTop: '60px', paddingTop: '24px', borderTop: '1px solid var(--border-color)', textAlign: 'center', color: 'var(--text-dim)', fontSize: '0.85rem' }}>
        <p>Frontend Architecture Study • 3.why-vite Chapter</p>
      </footer>
    </div>
  );
}
