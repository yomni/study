import React, { useState, useEffect } from 'react';
import { Flame, Code } from 'lucide-react';

export const HmrDemo: React.FC = () => {
  const [persistentCounter, setPersistentCounter] = useState<number>(0);
  const [textState, setTextState] = useState<string>('Vite HMR 테스트 중...');
  const [reloadLog, setReloadLog] = useState<string[]>([]);

  useEffect(() => {
    // HMR 핫 리로드 시 상태 및 타이머 생명주기 관리 데모
    const timestamp = new Date().toLocaleTimeString();
    setReloadLog((prev) => [
      ...prev,
      `🔥 [HMR Update] 모듈 렌더링 완료: ${timestamp}`
    ]);
  }, []);

  return (
    <div style={{ display: 'flex', flexDirection: 'column', gap: '24px' }}>
      <div className="glass-card">
        <h3 style={{ fontSize: '1.4rem', fontWeight: 700, marginBottom: '6px', display: 'flex', alignItems: 'center', gap: '8px' }}>
          <Flame size={24} style={{ color: 'var(--accent-rose)' }} />
          Lightning-Fast HMR (Hot Module Replacement) 실습
        </h3>
        <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
          소스 코드를 수정하더라도 전체 페이지 새로고침(Full Reload) 없이 변경된 컴포넌트만 교체되므로 기존 컴포넌트의 <strong>State가 그대로 유지</strong>됩니다.
        </p>
      </div>

      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '20px' }}>
        {/* Interactive State Card */}
        <div className="glass-card">
          <h4 style={{ fontSize: '1.1rem', fontWeight: 600, marginBottom: '16px' }}>
            🧪 컴포넌트 렌더링 상태 보존 테스트
          </h4>

          <div style={{ display: 'flex', flexDirection: 'column', gap: '16px' }}>
            <div>
              <label style={{ display: 'block', fontSize: '0.85rem', color: 'var(--text-muted)', marginBottom: '6px' }}>
                상태 카운터 (HMR 수정을 수행해도 유지됨)
              </label>
              <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
                <span style={{ fontSize: '1.8rem', fontWeight: 800, color: 'var(--accent-vite)' }}>
                  {persistentCounter}
                </span>
                <button
                  className="btn btn-secondary"
                  onClick={() => setPersistentCounter((c) => c + 1)}
                >
                  +1 증가
                </button>
              </div>
            </div>

            <div>
              <label style={{ display: 'block', fontSize: '0.85rem', color: 'var(--text-muted)', marginBottom: '6px' }}>
                사용자 입력 폼 텍스트 (Form State)
              </label>
              <input
                type="text"
                value={textState}
                onChange={(e) => setTextState(e.target.value)}
                style={{
                  width: '100%',
                  padding: '10px 14px',
                  borderRadius: '8px',
                  background: '#0d1117',
                  border: '1px solid var(--border-color)',
                  color: 'var(--text-main)',
                  outline: 'none'
                }}
              />
            </div>
          </div>
        </div>

        {/* HMR Logs & Code Snippet */}
        <div className="glass-card">
          <h4 style={{ fontSize: '1.1rem', fontWeight: 600, marginBottom: '16px', display: 'flex', alignItems: 'center', gap: '8px' }}>
            <Code size={18} style={{ color: 'var(--accent-cyan)' }} />
            `import.meta.hot` HMR API 코드
          </h4>

          <div className="code-block" style={{ marginBottom: '16px', fontSize: '0.8rem' }}>
            <pre>{`if (import.meta.hot) {
  import.meta.hot.accept((newModule) => {
    // 핫 모듈 교체 이벤트 처리
    console.log('Updated module:', newModule);
  });

  import.meta.hot.dispose(() => {
    // 이전 모듈 정리 (타이머, 이벤트 리스너 제거)
  });
}`}</pre>
          </div>

          <div style={{ fontSize: '0.8rem', color: 'var(--text-muted)' }}>
            <div style={{ fontWeight: 600, color: 'var(--text-main)', marginBottom: '6px' }}>HMR 업데이트 히스토리:</div>
            {reloadLog.map((log, idx) => (
              <div key={idx} style={{ color: 'var(--accent-emerald)', fontFamily: 'var(--font-mono)' }}>
                {log}
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};
