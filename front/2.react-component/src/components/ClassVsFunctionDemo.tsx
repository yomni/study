import { Component, useState, useEffect } from 'react'
import { RenderCounterBadge } from './RenderCounterBadge'
import { Terminal, Plus, Trash2, Cpu, Zap } from 'lucide-react'

interface LogItem {
  id: string
  time: string
  source: 'Class' | 'Function'
  message: string
}

// ==========================================
// 1. Class Component (Legacy Paradigm)
// ==========================================
interface ClassProps {
  onLog: (msg: string) => void
}

interface ClassState {
  count: number
}

class ClassCounterCard extends Component<ClassProps, ClassState> {
  constructor(props: ClassProps) {
    super(props)
    this.state = { count: 0 }
    this.handleIncrement = this.handleIncrement.bind(this)
  }

  componentDidMount() {
    this.props.onLog('componentDidMount() [Class 마운트 완료]')
  }

  componentDidUpdate(_prevProps: ClassProps, prevState: ClassState) {
    if (prevState.count !== this.state.count) {
      this.props.onLog(`componentDidUpdate() [Class 카운트 변경: ${this.state.count}]`)
    }
  }

  componentWillUnmount() {
    this.props.onLog('componentWillUnmount() [Class 언마운트/클린업]')
  }

  handleIncrement() {
    this.setState((prev) => ({ count: prev.count + 1 }))
  }

  render() {
    return (
      <div style={{ background: 'rgba(99, 102, 241, 0.1)', padding: '1.25rem', borderRadius: '12px', border: '1px solid rgba(99, 102, 241, 0.3)' }}>
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
          <h3 style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', color: '#818cf8', fontSize: '1.1rem' }}>
            <Cpu size={18} /> Class Component
          </h3>
          <RenderCounterBadge />
        </div>
        <p style={{ fontSize: '0.9rem', color: '#94a3b8', marginBottom: '1rem' }}>
          <code>this.state</code> 및 라이프사이클 메서드 사용
        </p>
        <div style={{ fontSize: '1.5rem', fontWeight: 700, marginBottom: '1rem' }}>
          Count: {this.state.count}
        </div>
        <button className="btn btn-primary" onClick={this.handleIncrement}>
          <Plus size={16} /> Count 증가
        </button>
      </div>
    )
  }
}

// ==========================================
// 2. Function Component (Modern Hooks Paradigm)
// ==========================================
function FunctionCounterCard({ onLog }: { onLog: (msg: string) => void }) {
  const [count, setCount] = useState(0)

  useEffect(() => {
    onLog(`useEffect() [Function 렌더링/업데이트: count=${count}]`)
    return () => {
      onLog(`useEffect Cleanup() [Function 클린업: count=${count}]`)
    }
  }, [count, onLog])

  return (
    <div style={{ background: 'rgba(6, 182, 212, 0.1)', padding: '1.25rem', borderRadius: '12px', border: '1px solid rgba(6, 182, 212, 0.3)' }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
        <h3 style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', color: '#38bdf8', fontSize: '1.1rem' }}>
          <Zap size={18} /> Function Component
        </h3>
        <RenderCounterBadge />
      </div>
      <p style={{ fontSize: '0.9rem', color: '#94a3b8', marginBottom: '1rem' }}>
        <code>useState</code> &amp; <code>useEffect</code> Hooks 사용
      </p>
      <div style={{ fontSize: '1.5rem', fontWeight: 700, marginBottom: '1rem' }}>
        Count: {count}
      </div>
      <button className="btn btn-emerald" onClick={() => setCount((prev) => prev + 1)}>
        <Plus size={16} /> Count 증가
      </button>
    </div>
  )
}

// ==========================================
// Main Demo Container
// ==========================================
export function ClassVsFunctionDemo() {
  const [showClass, setShowClass] = useState(true)
  const [showFunction, setShowFunction] = useState(true)
  const [logs, setLogs] = useState<LogItem[]>([])

  const addClassLog = (msg: string) => {
    const newLog: LogItem = {
      id: Math.random().toString(),
      time: new Date().toLocaleTimeString(),
      source: 'Class',
      message: msg,
    }
    setLogs((prev) => [newLog, ...prev.slice(0, 24)])
  }

  const addFunctionLog = (msg: string) => {
    const newLog: LogItem = {
      id: Math.random().toString(),
      time: new Date().toLocaleTimeString(),
      source: 'Function',
      message: msg,
    }
    setLogs((prev) => [newLog, ...prev.slice(0, 24)])
  }

  return (
    <div className="demo-section">
      <div className="glass-card">
        <h2 style={{ marginBottom: '0.5rem' }}>1. Class vs Function Component 라이프사이클 비교</h2>
        <p style={{ color: 'var(--text-secondary)', marginBottom: '1.5rem' }}>
          클래스형 컴포넌트의 생명주기 메서드(`componentDidMount`, `componentDidUpdate`)와 함수형 컴포넌트의 `useEffect` 실행 시점을 실시간 로그로 관찰해보세요.
        </p>

        {/* Toggle Controls */}
        <div style={{ display: 'flex', gap: '1rem', marginBottom: '1.5rem', flexWrap: 'wrap' }}>
          <button
            className={`btn ${showClass ? 'btn-primary' : 'btn-outline'}`}
            onClick={() => setShowClass(!showClass)}
          >
            Class Component {showClass ? '마운트 중' : '언마운트됨'}
          </button>
          <button
            className={`btn ${showFunction ? 'btn-emerald' : 'btn-outline'}`}
            onClick={() => setShowFunction(!showFunction)}
          >
            Function Component {showFunction ? '마운트 중' : '언마운트됨'}
          </button>
          <button className="btn btn-outline" onClick={() => setLogs([])} style={{ marginLeft: 'auto' }}>
            <Trash2 size={16} /> 로그 지우기
          </button>
        </div>

        {/* Components Layout */}
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: '1.5rem', marginBottom: '2rem' }}>
          {showClass ? (
            <ClassCounterCard onLog={addClassLog} />
          ) : (
            <div style={{ padding: '2rem', textAlign: 'center', border: '1px dashed var(--border-color)', borderRadius: '12px', color: 'var(--text-secondary)' }}>
              Class Component 언마운트됨
            </div>
          )}

          {showFunction ? (
            <FunctionCounterCard onLog={addFunctionLog} />
          ) : (
            <div style={{ padding: '2rem', textAlign: 'center', border: '1px dashed var(--border-color)', borderRadius: '12px', color: 'var(--text-secondary)' }}>
              Function Component 언마운트됨
            </div>
          )}
        </div>

        {/* Real-time Event Logger */}
        <div>
          <h4 style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '0.75rem', color: '#94a3b8' }}>
            <Terminal size={16} /> 실시간 생명주기 라이브 이벤트 로그
          </h4>
          <div className="log-box">
            {logs.length === 0 ? (
              <div style={{ color: '#475569', fontStyle: 'italic' }}>버튼을 클릭하여 이벤트 로그를 발생시켜보세요.</div>
            ) : (
              logs.map((log) => (
                <div key={log.id} className="log-entry">
                  <span className="log-time">[{log.time}]</span>
                  <span className={`badge ${log.source === 'Class' ? 'badge-indigo' : 'badge-cyan'}`} style={{ fontSize: '0.7rem', padding: '0.1rem 0.4rem' }}>
                    {log.source}
                  </span>
                  <span>{log.message}</span>
                </div>
              ))
            )}
          </div>
        </div>
      </div>
    </div>
  )
}
