import React from 'react';
import { ShieldCheck, ShieldAlert, Key, Terminal } from 'lucide-react';

export const EnvInspector: React.FC = () => {
  const envData = {
    MODE: import.meta.env.MODE,
    BASE_URL: import.meta.env.BASE_URL,
    PROD: import.meta.env.PROD ? 'true' : 'false',
    DEV: import.meta.env.DEV ? 'true' : 'false',
    SSR: import.meta.env.SSR ? 'true' : 'false',
    VITE_APP_TITLE: import.meta.env.VITE_APP_TITLE || 'Why Vite Study App',
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'column', gap: '24px' }}>
      <div className="glass-card">
        <h3 style={{ fontSize: '1.4rem', fontWeight: 700, marginBottom: '6px', display: 'flex', alignItems: 'center', gap: '8px' }}>
          <Key size={24} style={{ color: 'var(--accent-amber)' }} />
          `import.meta.env` 환경변수 인스펙터
        </h3>
        <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
          Vite는 Node.js의 <code>process.env</code> 대신 ES 모듈 메타데이터 표준인 <code>import.meta.env</code> 객체를 사용하며, 보안을 위해 <code>VITE_</code> 접두사가 붙은 변수만 클라이언트에 노출합니다.
        </p>
      </div>

      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '20px' }}>
        {/* Active Environment Variables Table */}
        <div className="glass-card">
          <h4 style={{ fontSize: '1.1rem', fontWeight: 600, marginBottom: '16px', display: 'flex', alignItems: 'center', gap: '8px' }}>
            <Terminal size={18} style={{ color: 'var(--accent-cyan)' }} />
            현재 런타임에 주입된 `import.meta.env`
          </h4>

          <div style={{ background: '#0d1117', borderRadius: '10px', padding: '16px', border: '1px solid rgba(255,255,255,0.08)' }}>
            <table style={{ width: '100%', borderCollapse: 'collapse', fontSize: '0.85rem' }}>
              <thead>
                <tr style={{ borderBottom: '1px solid #1f2937', color: 'var(--text-muted)', textAlign: 'left' }}>
                  <th style={{ padding: '8px' }}>Property</th>
                  <th style={{ padding: '8px' }}>Evaluated Value</th>
                </tr>
              </thead>
              <tbody>
                {Object.entries(envData).map(([key, val]) => (
                  <tr key={key} style={{ borderBottom: '1px solid rgba(255,255,255,0.04)' }}>
                    <td style={{ padding: '8px', fontFamily: 'var(--font-mono)', color: 'var(--accent-cyan)' }}>
                      import.meta.env.{key}
                    </td>
                    <td style={{ padding: '8px', fontFamily: 'var(--font-mono)', color: 'var(--accent-emerald)' }}>
                      "{val}"
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

        {/* Security Rule Explanation */}
        <div className="glass-card">
          <h4 style={{ fontSize: '1.1rem', fontWeight: 600, marginBottom: '16px' }}>
            🔒 환경 변수 보안 샌드박싱 규칙
          </h4>

          <div style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>
            <div style={{ padding: '12px', background: 'rgba(16, 185, 129, 0.1)', borderRadius: '8px', border: '1px solid rgba(16, 185, 129, 0.3)', display: 'flex', gap: '10px' }}>
              <ShieldCheck size={20} style={{ color: 'var(--accent-emerald)', flexShrink: 0 }} />
              <div style={{ fontSize: '0.85rem' }}>
                <strong style={{ color: 'var(--accent-emerald)' }}>VITE_ 접두사 지정 (안전)</strong>
                <p style={{ color: 'var(--text-muted)', marginTop: '2px' }}>
                  <code>.env</code> 파일에 <code>VITE_API_URL=https://...</code> 와 같이 정의해야 번들링 시 클라이언트로 번들 주입됩니다.
                </p>
              </div>
            </div>

            <div style={{ padding: '12px', background: 'rgba(244, 63, 94, 0.1)', borderRadius: '8px', border: '1px solid rgba(244, 63, 94, 0.3)', display: 'flex', gap: '10px' }}>
              <ShieldAlert size={20} style={{ color: 'var(--accent-rose)', flexShrink: 0 }} />
              <div style={{ fontSize: '0.85rem' }}>
                <strong style={{ color: 'var(--accent-rose)' }}>일반 변수 (접두사 없음 - 차단)</strong>
                <p style={{ color: 'var(--text-muted)', marginTop: '2px' }}>
                  <code>DB_PASSWORD=secret</code> 와 같은 일반 시스템/Node 환경변수는 브라우저 번들 노출을 방지하기 위해 자동 차단(undefined)됩니다.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
