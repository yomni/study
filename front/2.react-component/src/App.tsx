import { useState } from 'react'
import { ClassVsFunctionDemo } from './components/ClassVsFunctionDemo'
import { ImmutabilityLab } from './components/ImmutabilityLab'
import { CompoundAccordionDemo } from './components/CompoundAccordionDemo'
import { FormComparisonDemo } from './components/FormComparisonDemo'
import { Layers, Cpu, ShieldAlert, Sliders, BookOpen, ExternalLink } from 'lucide-react'

type TabType = 'lifecycle' | 'immutability' | 'compound' | 'form'

export default function App() {
  const [activeTab, setActiveTab] = useState<TabType>('lifecycle')

  return (
    <div className="container">
      {/* Header Banner */}
      <header className="app-header">
        <div style={{ display: 'flex', justifyContent: 'center', gap: '0.5rem', marginBottom: '0.75rem' }}>
          <span className="badge badge-cyan">Front-End Study Series #2</span>
          <span className="badge badge-indigo">React 19 &amp; TSX</span>
        </div>
        <h1 className="gradient-title">React Component &amp; Architecture</h1>
        <p className="app-subtitle">
          React 컴포넌트의 메커니즘, 라이프사이클 패러다임, 불변성 원칙, 그리고 복합 컴포넌트(Compound Component) 패턴을 대화형 실습으로 학습합니다.
        </p>
      </header>

      {/* Main Tab Navigation */}
      <nav className="tabs-nav">
        <button
          className={`tab-btn ${activeTab === 'lifecycle' ? 'active' : ''}`}
          onClick={() => setActiveTab('lifecycle')}
        >
          <Cpu size={18} /> 1. Class vs Function
        </button>
        <button
          className={`tab-btn ${activeTab === 'immutability' ? 'active' : ''}`}
          onClick={() => setActiveTab('immutability')}
        >
          <ShieldAlert size={18} /> 2. State 불변성 실험
        </button>
        <button
          className={`tab-btn ${activeTab === 'compound' ? 'active' : ''}`}
          onClick={() => setActiveTab('compound')}
        >
          <Layers size={18} /> 3. Compound Component
        </button>
        <button
          className={`tab-btn ${activeTab === 'form' ? 'active' : ''}`}
          onClick={() => setActiveTab('form')}
        >
          <Sliders size={18} /> 4. Form 제어 vs 비제어
        </button>
      </nav>

      {/* Active Tab View */}
      <main style={{ minHeight: '480px' }}>
        {activeTab === 'lifecycle' && <ClassVsFunctionDemo />}
        {activeTab === 'immutability' && <ImmutabilityLab />}
        {activeTab === 'compound' && <CompoundAccordionDemo />}
        {activeTab === 'form' && <FormComparisonDemo />}
      </main>

      {/* Footer Info Box */}
      <footer
        style={{
          marginTop: '3rem',
          paddingTop: '1.5rem',
          borderTop: '1px solid var(--border-color)',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          color: 'var(--text-secondary)',
          fontSize: '0.9rem',
          flexWrap: 'wrap',
          gap: '1rem',
        }}
      >
        <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <BookOpen size={16} /> 상세 이론 및 아키텍처 설명은 <code>REACT_COMPONENT.md</code> 문서를 참고하세요.
        </div>
        <div style={{ display: 'flex', gap: '1rem' }}>
          <a
            href="https://react.dev/learn/your-first-component"
            target="_blank"
            rel="noreferrer"
            style={{ color: 'var(--accent-cyan)', textDecoration: 'none', display: 'flex', alignItems: 'center', gap: '0.25rem' }}
          >
            React Docs <ExternalLink size={14} />
          </a>
        </div>
      </footer>
    </div>
  )
}
