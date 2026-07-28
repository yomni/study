import React, { useState, useRef } from 'react'
import { RenderCounterBadge } from './RenderCounterBadge'
import { Sliders, Eye, FileInput } from 'lucide-react'

export function FormComparisonDemo() {
  // 1. Controlled Component State
  const [controlledValue, setControlledValue] = useState('')

  // 2. Uncontrolled Component Ref
  const uncontrolledRef = useRef<HTMLInputElement>(null)
  const [uncontrolledSubmittedValue, setUncontrolledSubmittedValue] = useState('')

  const handleUncontrolledSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    if (uncontrolledRef.current) {
      setUncontrolledSubmittedValue(uncontrolledRef.current.value)
    }
  }

  return (
    <div className="demo-section">
      <div className="glass-card">
        <div style={{ marginBottom: '1.5rem' }}>
          <h2 style={{ marginBottom: '0.5rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
            <Sliders size={22} color="var(--accent-purple)" /> 4. Controlled vs Uncontrolled Components
          </h2>
          <p style={{ color: 'var(--text-secondary)' }}>
            키 입력 시마다 렌더링이 발생하는 제어 컴포넌트(Controlled)와 DOM Ref 기반으로 제출 시점에만 렌더링을 일으키는 비제어 컴포넌트(Uncontrolled)의 렌더링 횟수를 비교해보세요.
          </p>
        </div>

        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '1.5rem' }}>
          {/* Controlled Component Box */}
          <div style={{ background: 'rgba(99, 102, 241, 0.1)', padding: '1.25rem', borderRadius: '12px', border: '1px solid rgba(99, 102, 241, 0.3)' }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
              <h3 style={{ fontSize: '1rem', color: '#818cf8', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                <Eye size={16} /> 제어 컴포넌트 (Controlled)
              </h3>
              <RenderCounterBadge label="Controlled Card Renders" />
            </div>

            <p style={{ fontSize: '0.85rem', color: '#94a3b8', marginBottom: '1rem' }}>
              <code>value={'{controlledValue}'}</code> + <code>onChange</code> 로 키 타이핑마다 React State를 동기화합니다.
            </p>

            <input
              type="text"
              className="input-field"
              placeholder="텍스트를 입력하면 즉시 렌더링..."
              value={controlledValue}
              onChange={(e) => setControlledValue(e.target.value)}
              style={{ marginBottom: '1rem' }}
            />

            <div style={{ background: '#090d16', padding: '0.75rem', borderRadius: '8px', fontSize: '0.85rem' }}>
              현재 State 실시간 값: <strong style={{ color: '#38bdf8' }}>{controlledValue || '(비어있음)'}</strong>
            </div>
          </div>

          {/* Uncontrolled Component Box */}
          <div style={{ background: 'rgba(16, 185, 129, 0.1)', padding: '1.25rem', borderRadius: '12px', border: '1px solid rgba(16, 185, 129, 0.3)' }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
              <h3 style={{ fontSize: '1rem', color: '#34d399', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                <FileInput size={16} /> 비제어 컴포넌트 (Uncontrolled)
              </h3>
              <RenderCounterBadge label="Uncontrolled Card Renders" />
            </div>

            <p style={{ fontSize: '0.85rem', color: '#94a3b8', marginBottom: '1rem' }}>
              DOM 자체 상태를 보유하며 <code>useRef</code>를 사용하여 제출 시점에만 값을 읽습니다.
            </p>

            <form onSubmit={handleUncontrolledSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
              <input
                ref={uncontrolledRef}
                type="text"
                className="input-field"
                placeholder="입력 중에는 렌더링되지 않음..."
                defaultValue=""
              />
              <button type="submit" className="btn btn-emerald">
                제출하여 Ref 값 읽기
              </button>
            </form>

            <div style={{ background: '#090d16', padding: '0.75rem', borderRadius: '8px', fontSize: '0.85rem', marginTop: '0.75rem' }}>
              제출된 Ref 값: <strong style={{ color: '#34d399' }}>{uncontrolledSubmittedValue || '(제출되지 않음)'}</strong>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
