import React, { useState } from 'react';
import { Globe, FileCode, CheckCircle2, Network } from 'lucide-react';

interface EsmModuleNode {
  id: string;
  name: string;
  type: 'html' | 'entry' | 'component' | 'dep';
  status: 'idle' | 'fetching' | 'loaded';
  responseHeader?: string;
  codeSnippet: string;
}

export const NativeEsmVisualizer: React.FC = () => {
  const [activeModule, setActiveModule] = useState<string>('index.html');
  const [requestLogs] = useState<EsmModuleNode[]>([
    {
      id: 'index.html',
      name: 'index.html',
      type: 'html',
      status: 'loaded',
      responseHeader: '200 OK (text/html)',
      codeSnippet: `<!DOCTYPE html>
<html>
  <body>
    <div id="root"></div>
    <!-- 브라우저 Native ESM 로딩 스크립트 -->
    <script type="module" src="/src/main.tsx"></script>
  </body>
</html>`
    },
    {
      id: 'main.tsx',
      name: '/src/main.tsx',
      type: 'entry',
      status: 'loaded',
      responseHeader: '200 OK (application/javascript; charset=utf-8)',
      codeSnippet: `import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'

ReactDOM.createRoot(document.getElementById('root')!).render(<App />)`
    },
    {
      id: 'App.tsx',
      name: '/src/App.tsx',
      type: 'component',
      status: 'loaded',
      responseHeader: '200 OK (application/javascript)',
      codeSnippet: `import { NativeEsmVisualizer } from './components/NativeEsmVisualizer'

export default function App() {
  return <div>Why Vite Chapter</div>
}`
    },
    {
      id: 'react',
      name: '/node_modules/.vite/deps/react.js',
      type: 'dep',
      status: 'loaded',
      responseHeader: '200 OK (Cache-Control: max-age=31536000, immutable)',
      codeSnippet: `// Esbuild로 사전 번들링(Pre-bundled)된 ESM 호환 모듈
export default React;
export { useState, useEffect, useCallback };`
    }
  ]);

  const selectedNode = requestLogs.find((node) => node.id === activeModule) || requestLogs[0];

  return (
    <div style={{ display: 'flex', flexDirection: 'column', gap: '24px' }}>
      <div className="glass-card">
        <h3 style={{ fontSize: '1.4rem', fontWeight: 700, marginBottom: '6px', display: 'flex', alignItems: 'center', gap: '8px' }}>
          <Globe size={24} style={{ color: 'var(--accent-cyan)' }} />
          브라우저 Native ESM 네트워크 요청 시각화
        </h3>
        <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
          Vite는 소스 코드를 번들 파일 하나로 합치지 않고 브라우저의 <code>&lt;script type="module"&gt;</code> 요청에 응답하여 개별 모듈 파일로 즉각 전달합니다.
        </p>
      </div>

      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '20px' }}>
        {/* Request Graph Visualizer */}
        <div className="glass-card">
          <h4 style={{ fontSize: '1.05rem', fontWeight: 600, marginBottom: '16px', display: 'flex', alignItems: 'center', gap: '8px' }}>
            <Network size={18} style={{ color: 'var(--accent-vite)' }} />
            모듈 의존성 요청 캐스케이드 (On-demand GET)
          </h4>

          <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
            {requestLogs.map((node) => {
              const isActive = activeModule === node.id;
              return (
                <div
                  key={node.id}
                  onClick={() => setActiveModule(node.id)}
                  style={{
                    padding: '12px 16px',
                    borderRadius: '10px',
                    background: isActive ? 'rgba(189, 52, 254, 0.15)' : '#0d1117',
                    border: `1px solid ${isActive ? 'var(--accent-vite)' : 'rgba(255, 255, 255, 0.08)'}`,
                    cursor: 'pointer',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'space-between',
                    transition: 'all 0.2s ease'
                  }}
                >
                  <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                    <FileCode size={18} style={{ color: node.type === 'dep' ? 'var(--accent-amber)' : 'var(--accent-cyan)' }} />
                    <span style={{ fontSize: '0.9rem', fontWeight: isActive ? 700 : 500 }}>{node.name}</span>
                  </div>

                  <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                    {node.type === 'dep' && <span className="badge badge-vite">Pre-bundled</span>}
                    <CheckCircle2 size={16} style={{ color: 'var(--accent-emerald)' }} />
                  </div>
                </div>
              );
            })}
          </div>
        </div>

        {/* Code & HTTP Header Inspector */}
        <div className="glass-card">
          <h4 style={{ fontSize: '1.05rem', fontWeight: 600, marginBottom: '16px' }}>
            🔍 모듈 응답 및 코드 인스펙터
          </h4>

          <div style={{ background: '#0d1117', padding: '12px 16px', borderRadius: '8px', marginBottom: '12px', borderLeft: '4px solid var(--accent-vite)' }}>
            <div style={{ fontSize: '0.75rem', color: 'var(--text-muted)' }}>HTTP Response Header:</div>
            <div style={{ fontSize: '0.85rem', fontWeight: 600, color: 'var(--accent-emerald)', fontFamily: 'var(--font-mono)' }}>
              {selectedNode.responseHeader}
            </div>
          </div>

          <div className="code-block" style={{ height: '240px' }}>
            <pre style={{ margin: 0 }}>{selectedNode.codeSnippet}</pre>
          </div>
        </div>
      </div>
    </div>
  );
};
