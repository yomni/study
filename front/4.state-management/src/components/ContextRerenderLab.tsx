import { createContext, useContext, useMemo, useState } from 'react'
import { Plus, Type, AlertTriangle, CheckCircle2 } from 'lucide-react'
import { RenderCounterBadge } from './RenderCounterBadge'

interface CombinedValue {
  count: number
  text: string
}

// 문제 상황: count와 text를 하나의 Context 값으로 묶은 경우
const CombinedContext = createContext<CombinedValue>({ count: 0, text: '' })

function CountConsumerA() {
  const { count } = useContext(CombinedContext)
  return (
    <div className="drill-level">
      <div>
        count만 구독: <strong>{count}</strong>
      </div>
      <RenderCounterBadge label="CountConsumer" />
    </div>
  )
}

function TextConsumerA() {
  const { text } = useContext(CombinedContext)
  return (
    <div className="drill-level">
      <div>
        text만 구독: <strong>{text}</strong>
      </div>
      <RenderCounterBadge label="TextConsumer" />
    </div>
  )
}

// 해결: 상태 단위로 Context를 분리한 경우
const CountOnlyContext = createContext(0)
const TextOnlyContext = createContext('')

function CountConsumerB() {
  const count = useContext(CountOnlyContext)
  return (
    <div className="drill-level">
      <div>
        count만 구독: <strong>{count}</strong>
      </div>
      <RenderCounterBadge label="CountConsumer" />
    </div>
  )
}

function TextConsumerB() {
  const text = useContext(TextOnlyContext)
  return (
    <div className="drill-level">
      <div>
        text만 구독: <strong>{text}</strong>
      </div>
      <RenderCounterBadge label="TextConsumer" />
    </div>
  )
}

const SAMPLE_WORDS = ['React', 'Zustand', 'Redux', 'Jotai', 'Recoil']

function nextWord(current: string) {
  return SAMPLE_WORDS[(SAMPLE_WORDS.indexOf(current) + 1) % SAMPLE_WORDS.length]
}

export function ContextRerenderLab() {
  const [countA, setCountA] = useState(0)
  const [textA, setTextA] = useState(SAMPLE_WORDS[0])
  const [countB, setCountB] = useState(0)
  const [textB, setTextB] = useState(SAMPLE_WORDS[0])

  const combinedValue = useMemo<CombinedValue>(() => ({ count: countA, text: textA }), [countA, textA])

  return (
    <div className="demo-section">
      <div className="glass-card">
        <h2 style={{ marginBottom: '0.5rem' }}>2. Context 리렌더링 실험실</h2>
        <p style={{ color: 'var(--text-secondary)', marginBottom: '1.5rem' }}>
          같은 종류의 버튼을 눌러도 Context를 어떻게 나누느냐에 따라 관련 없는 컴포넌트까지 함께 리렌더링되는지가
          달라집니다. 각 컨슈머의 렌더링 배지 숫자를 비교해 보세요.
        </p>

        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '1.5rem' }}>
          <div style={{ background: '#0f172a', border: '1px solid rgba(244, 63, 94, 0.3)', borderRadius: '12px', padding: '1.25rem' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '1rem', color: '#fb7185' }}>
              <AlertTriangle size={18} />
              <strong>단일 Context (count + text 뭉침)</strong>
            </div>
            <div style={{ display: 'flex', gap: '0.5rem', marginBottom: '1rem', flexWrap: 'wrap' }}>
              <button className="btn btn-rose" onClick={() => setCountA((c) => c + 1)}>
                <Plus size={14} /> count A 증가
              </button>
              <button className="btn btn-outline" onClick={() => setTextA((t) => nextWord(t))}>
                <Type size={14} /> text A 변경
              </button>
            </div>
            <CombinedContext.Provider value={combinedValue}>
              <div style={{ display: 'grid', gap: '0.75rem' }}>
                <CountConsumerA />
                <TextConsumerA />
              </div>
            </CombinedContext.Provider>
            <p style={{ fontSize: '0.8rem', color: 'var(--text-secondary)', marginTop: '0.75rem' }}>
              → count A만 증가시켜도 text만 구독하는 컴포넌트의 렌더링 배지가 함께 올라갑니다.
            </p>
          </div>

          <div style={{ background: '#0f172a', border: '1px solid rgba(16, 185, 129, 0.3)', borderRadius: '12px', padding: '1.25rem' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '1rem', color: '#34d399' }}>
              <CheckCircle2 size={18} />
              <strong>분리된 Context (Count / Text 독립)</strong>
            </div>
            <div style={{ display: 'flex', gap: '0.5rem', marginBottom: '1rem', flexWrap: 'wrap' }}>
              <button className="btn btn-emerald" onClick={() => setCountB((c) => c + 1)}>
                <Plus size={14} /> count B 증가
              </button>
              <button className="btn btn-outline" onClick={() => setTextB((t) => nextWord(t))}>
                <Type size={14} /> text B 변경
              </button>
            </div>
            <CountOnlyContext.Provider value={countB}>
              <TextOnlyContext.Provider value={textB}>
                <div style={{ display: 'grid', gap: '0.75rem' }}>
                  <CountConsumerB />
                  <TextConsumerB />
                </div>
              </TextOnlyContext.Provider>
            </CountOnlyContext.Provider>
            <p style={{ fontSize: '0.8rem', color: 'var(--text-secondary)', marginTop: '0.75rem' }}>
              → count B를 증가시켜도 text만 구독하는 컴포넌트의 렌더링 배지는 그대로입니다.
            </p>
          </div>
        </div>
      </div>
    </div>
  )
}
