import React, { useState } from 'react';
import { Play, RotateCcw, Zap, Box, Layers, ArrowRight } from 'lucide-react';

export const BundlerSimulator: React.FC = () => {
  const [moduleCount, setModuleCount] = useState<number>(500);
  const [isSimulating, setIsSimulating] = useState<boolean>(false);
  const [webpackProgress, setWebpackProgress] = useState<number>(0);
  const [webpackTime, setWebpackTime] = useState<number | null>(null);
  const [viteTime, setViteTime] = useState<number | null>(null);
  const [simulationLog, setSimulationLog] = useState<string[]>([]);

  const runSimulation = () => {
    setIsSimulating(true);
    setWebpackProgress(0);
    setWebpackTime(null);
    setViteTime(null);
    setSimulationLog(['🚀 개발 서버 기동 시뮬레이션 시작...']);

    // Vite: Instant cold start (Fixed ~50ms + On-demand preparation)
    const calculatedViteTime = Math.round(30 + Math.log2(moduleCount) * 5);
    
    // Webpack: Bundle-based (Linear increase based on module count)
    // ~500 modules = 2.5s, ~2000 modules = 10s
    const calculatedWebpackTime = Math.round(moduleCount * 5.2);

    setTimeout(() => {
      setViteTime(calculatedViteTime);
      setSimulationLog((prev) => [
        ...prev,
        `⚡ [Vite] 개발 서버 기동 완료! (${calculatedViteTime}ms) - Native ESM 준비 완료 (No Bundling)`
      ]);
    }, calculatedViteTime);

    // Webpack Progress Animation
    let currentProgress = 0;
    const interval = setInterval(() => {
      currentProgress += 5;
      setWebpackProgress(Math.min(currentProgress, 100));

      if (currentProgress >= 100) {
        clearInterval(interval);
        setWebpackTime(calculatedWebpackTime);
        setIsSimulating(false);
        setSimulationLog((prev) => [
          ...prev,
          `📦 [Webpack] 전체 모듈 번들링 완료! (${(calculatedWebpackTime / 1000).toFixed(2)}s) - Memory Bundle 생성`
        ]);
      }
    }, calculatedWebpackTime / 20);
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'column', gap: '24px' }}>
      <div className="glass-card">
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '16px' }}>
          <div>
            <h3 style={{ fontSize: '1.4rem', fontWeight: 700, marginBottom: '6px' }}>
              ⚡ Cold Start 번들링 속도 시뮬레이터
            </h3>
            <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
              프로젝트 모듈 수에 따른 Webpack(전체 번들 방식)과 Vite(Native ESM On-demand 방식)의 기동 시간 차이를 테스트해보세요.
            </p>
          </div>

          <div style={{ display: 'flex', alignItems: 'center', gap: '16px' }}>
            <div style={{ display: 'flex', flexDirection: 'column', gap: '4px' }}>
              <label style={{ fontSize: '0.8rem', color: 'var(--text-muted)' }}>
                프로젝트 모듈 개수: <strong style={{ color: 'var(--accent-cyan)' }}>{moduleCount}개</strong>
              </label>
              <input
                type="range"
                min="100"
                max="3000"
                step="100"
                value={moduleCount}
                onChange={(e) => setModuleCount(Number(e.target.value))}
                disabled={isSimulating}
                style={{ width: '180px', accentColor: 'var(--accent-vite)' }}
              />
            </div>

            <button
              className="btn btn-primary"
              onClick={runSimulation}
              disabled={isSimulating}
            >
              {isSimulating ? <RotateCcw className="animate-spin" size={18} /> : <Play size={18} />}
              벤치마크 실행
            </button>
          </div>
        </div>
      </div>

      {/* Comparison Grid */}
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '20px' }}>
        {/* Vite Card */}
        <div className="glass-card" style={{ borderColor: 'rgba(189, 52, 254, 0.3)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '16px' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
              <Zap style={{ color: 'var(--accent-vite)' }} size={24} />
              <h4 style={{ fontSize: '1.2rem', fontWeight: 700 }}>Vite (Native ESM)</h4>
            </div>
            <span className="badge badge-vite">No-Bundle Dev Server</span>
          </div>

          <div style={{ background: '#0d1117', padding: '16px', borderRadius: '12px', marginBottom: '16px' }}>
            <div style={{ fontSize: '0.85rem', color: 'var(--text-muted)', marginBottom: '8px' }}>개발 서버 실행 타임</div>
            <div style={{ fontSize: '2rem', fontWeight: 800, color: 'var(--accent-vite)' }}>
              {viteTime !== null ? `${viteTime} ms` : isSimulating ? '기동 중...' : '-'}
            </div>
          </div>

          <div style={{ fontSize: '0.85rem', color: 'var(--text-muted)', lineHeight: '1.6' }}>
            💡 <strong>핵심 매커니즘:</strong> 번들링 단계를 완전히 스킵합니다. 브라우저의 <code>&lt;script type="module"&gt;</code> 요청 시점에 필요한 파일만 On-demand 변환하여 즉시 전달합니다.
          </div>
        </div>

        {/* Webpack Card */}
        <div className="glass-card" style={{ borderColor: 'rgba(65, 209, 255, 0.3)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '16px' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
              <Box style={{ color: 'var(--accent-cyan)' }} size={24} />
              <h4 style={{ fontSize: '1.2rem', fontWeight: 700 }}>Webpack 5 (Bundle-based)</h4>
            </div>
            <span className="badge badge-webpack">Bundle Dev Server</span>
          </div>

          <div style={{ background: '#0d1117', padding: '16px', borderRadius: '12px', marginBottom: '16px' }}>
            <div style={{ fontSize: '0.85rem', color: 'var(--text-muted)', marginBottom: '8px' }}>개발 서버 번들링 진행률</div>
            <div style={{ fontSize: '2rem', fontWeight: 800, color: 'var(--accent-cyan)', marginBottom: '8px' }}>
              {webpackTime !== null ? `${(webpackTime / 1000).toFixed(2)} s` : `${webpackProgress}%`}
            </div>
            {/* Progress bar */}
            <div style={{ width: '100%', height: '6px', background: '#1f2937', borderRadius: '3px', overflow: 'hidden' }}>
              <div style={{ width: `${webpackProgress}%`, height: '100%', background: 'var(--accent-cyan)', transition: 'width 0.1s linear' }} />
            </div>
          </div>

          <div style={{ fontSize: '0.85rem', color: 'var(--text-muted)', lineHeight: '1.6' }}>
            🚨 <strong>핵심 매커니즘:</strong> 서버를 켜기 전에 모든 모듈(JS, CSS, Assets)의 의존성 트리를 구성하고 메모리에 전체 번들을 구축해야 서버 준비가 완료됩니다.
          </div>
        </div>
      </div>

      {/* Real-time Simulation Logs */}
      {simulationLog.length > 0 && (
        <div className="glass-card">
          <h4 style={{ fontSize: '0.95rem', fontWeight: 600, color: 'var(--text-muted)', marginBottom: '10px' }}>
            📋 실행 로그 출력
          </h4>
          <div className="code-block" style={{ maxHeight: '140px' }}>
            {simulationLog.map((log, index) => (
              <div key={index} style={{ marginBottom: '4px' }}>{log}</div>
            ))}
          </div>
        </div>
      )}

      {/* Architecture Flow Comparison */}
      <div className="glass-card">
        <h4 style={{ fontSize: '1.1rem', fontWeight: 700, marginBottom: '16px', display: 'flex', alignItems: 'center', gap: '8px' }}>
          <Layers size={20} style={{ color: 'var(--accent-vite)' }} />
          개발 서버 구조 다이어그램 비교
        </h4>

        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: '20px' }}>
          <div style={{ background: '#0d1117', padding: '16px', borderRadius: '12px', border: '1px solid rgba(255,255,255,0.05)' }}>
            <div style={{ fontWeight: 600, color: 'var(--accent-cyan)', marginBottom: '10px' }}>📦 Webpack (Bundle-based)</div>
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px', fontSize: '0.8rem', color: 'var(--text-muted)' }}>
              <span>[Entry Point]</span>
              <ArrowRight size={14} />
              <span>[Bundle All Modules]</span>
              <ArrowRight size={14} />
              <span style={{ color: 'var(--accent-emerald)', fontWeight: 600 }}>[Dev Server Ready]</span>
            </div>
          </div>

          <div style={{ background: '#0d1117', padding: '16px', borderRadius: '12px', border: '1px solid rgba(255,255,255,0.05)' }}>
            <div style={{ fontWeight: 600, color: 'var(--accent-vite)', marginBottom: '10px' }}>⚡ Vite (Native ESM)</div>
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px', fontSize: '0.8rem', color: 'var(--text-muted)' }}>
              <span style={{ color: 'var(--accent-emerald)', fontWeight: 600 }}>[Dev Server Ready]</span>
              <ArrowRight size={14} />
              <span>[HTTP Request]</span>
              <ArrowRight size={14} />
              <span>[On-demand Compile]</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
