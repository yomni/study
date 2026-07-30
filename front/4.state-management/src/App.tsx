import { useState } from 'react'
import { GitBranch, Boxes, Database, Layers, BookOpen, ExternalLink } from 'lucide-react'
import { PropDrillingDemo } from './components/PropDrillingDemo'
import { ContextRerenderLab } from './components/ContextRerenderLab'
import { ExternalStoreDemo } from './components/ExternalStoreDemo'
import { ClientServerStateDemo } from './components/ClientServerStateDemo'

type TabType = 'drilling' | 'context' | 'store' | 'server'

export default function App() {
  const [activeTab, setActiveTab] = useState<TabType>('drilling')

  return (
    <div className="container">
      {/* Header Banner */}
      <header className="app-header">
        <div style={{ display: 'flex', justifyContent: 'center', gap: '0.5rem', marginBottom: '0.75rem' }}>
          <span className="badge badge-amber">Front-End Study Series #4</span>
          <span className="badge badge-violet">Context · Store · Server State</span>
        </div>
        <h1 className="gradient-title">State Management</h1>
        <p className="app-subtitle">
          Prop Drilling 문제에서 시작해 Context API의 리렌더링 함정, Zustand/Jotai류 외부 Store가 이를 해결하는
          방식, 그리고 Client State와 Server State의 근본적인 차이를 대화형 실습으로 학습합니다.
        </p>
      </header>

      {/* Main Tab Navigation */}
      <nav className="tabs-nav">
        <button className={`tab-btn ${activeTab === 'drilling' ? 'active' : ''}`} onClick={() => setActiveTab('drilling')}>
          <GitBranch size={18} /> 1. Prop Drilling
        </button>
        <button className={`tab-btn ${activeTab === 'context' ? 'active' : ''}`} onClick={() => setActiveTab('context')}>
          <Layers size={18} /> 2. Context 리렌더링
        </button>
        <button className={`tab-btn ${activeTab === 'store' ? 'active' : ''}`} onClick={() => setActiveTab('store')}>
          <Boxes size={18} /> 3. 외부 Store 패턴
        </button>
        <button className={`tab-btn ${activeTab === 'server' ? 'active' : ''}`} onClick={() => setActiveTab('server')}>
          <Database size={18} /> 4. Client vs Server State
        </button>
      </nav>

      {/* Active Tab View */}
      <main style={{ minHeight: '480px' }}>
        {activeTab === 'drilling' && <PropDrillingDemo />}
        {activeTab === 'context' && <ContextRerenderLab />}
        {activeTab === 'store' && <ExternalStoreDemo />}
        {activeTab === 'server' && <ClientServerStateDemo />}
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
          <BookOpen size={16} /> 상세 이론 및 아키텍처 설명은 <code>STATE_MANAGEMENT.md</code> 문서를 참고하세요.
        </div>
        <div style={{ display: 'flex', gap: '1rem' }}>
          <a
            href="https://react.dev/learn/passing-data-deeply-with-context"
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
