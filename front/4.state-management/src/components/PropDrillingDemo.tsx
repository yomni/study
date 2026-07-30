import { createContext, useContext, useMemo, useState } from 'react'
import { Layers, ArrowDownToLine, Sparkles } from 'lucide-react'

type Theme = 'light' | 'dark'

interface ThemeCtxValue {
  theme: Theme
  toggleTheme: () => void
}

const ThemeContext = createContext<ThemeCtxValue | null>(null)

function useThemeContext() {
  const ctx = useContext(ThemeContext)
  if (!ctx) throw new Error('ThemeContext Provider 내부에서만 사용할 수 있습니다')
  return ctx
}

const LEVEL_NAMES = ['Page', 'Layout', 'Sidebar', 'Panel', 'Widget', 'Leaf']

function DrillLevel({
  depth,
  max,
  theme,
  onToggle,
}: {
  depth: number
  max: number
  theme: Theme
  onToggle: () => void
}) {
  const name = LEVEL_NAMES[depth] ?? `Level${depth}`
  const isLeaf = depth === max

  return (
    <div className="drill-level" style={{ marginLeft: depth === 0 ? 0 : '1rem', borderColor: isLeaf ? 'var(--accent-amber)' : undefined }}>
      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', gap: '0.5rem', flexWrap: 'wrap' }}>
        <strong style={{ fontSize: '0.9rem' }}>
          {name} <span style={{ color: 'var(--text-secondary)', fontWeight: 400 }}>(depth {depth})</span>
        </strong>
        {!isLeaf && (
          <span style={{ fontSize: '0.75rem', color: 'var(--text-secondary)' }}>
            theme을 쓰지 않지만 props로 그대로 전달 →
          </span>
        )}
        {isLeaf && (
          <button className="btn btn-primary" style={{ padding: '0.4rem 0.8rem', fontSize: '0.8rem' }} onClick={onToggle}>
            테마 전환 ({theme})
          </button>
        )}
      </div>
      {!isLeaf && (
        <div style={{ marginTop: '0.75rem' }}>
          <DrillLevel depth={depth + 1} max={max} theme={theme} onToggle={onToggle} />
        </div>
      )}
    </div>
  )
}

function ContextLevel({ depth, max }: { depth: number; max: number }) {
  const name = LEVEL_NAMES[depth] ?? `Level${depth}`
  const isLeaf = depth === max

  return (
    <div className="drill-level" style={{ marginLeft: depth === 0 ? 0 : '1rem', borderColor: isLeaf ? 'var(--accent-violet)' : undefined }}>
      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', gap: '0.5rem', flexWrap: 'wrap' }}>
        <strong style={{ fontSize: '0.9rem' }}>
          {name} <span style={{ color: 'var(--text-secondary)', fontWeight: 400 }}>(depth {depth})</span>
        </strong>
        {!isLeaf && (
          <span style={{ fontSize: '0.75rem', color: 'var(--text-secondary)' }}>theme을 전혀 모른 채로 자식만 렌더링</span>
        )}
        {isLeaf && <LeafConsumer />}
      </div>
      {!isLeaf && (
        <div style={{ marginTop: '0.75rem' }}>
          <ContextLevel depth={depth + 1} max={max} />
        </div>
      )}
    </div>
  )
}

function LeafConsumer() {
  const { theme, toggleTheme } = useThemeContext()
  return (
    <button className="btn btn-violet" style={{ padding: '0.4rem 0.8rem', fontSize: '0.8rem' }} onClick={toggleTheme}>
      테마 전환 ({theme})
    </button>
  )
}

export function PropDrillingDemo() {
  const [mode, setMode] = useState<'drilling' | 'context'>('drilling')
  const [depth, setDepth] = useState(4)
  const [theme, setTheme] = useState<Theme>('dark')

  const toggleTheme = () => setTheme((t) => (t === 'dark' ? 'light' : 'dark'))
  const ctxValue = useMemo<ThemeCtxValue>(() => ({ theme, toggleTheme }), [theme])

  return (
    <div className="demo-section">
      <div className="glass-card">
        <h2 style={{ marginBottom: '0.5rem' }}>1. Prop Drilling vs Context API</h2>
        <p style={{ color: 'var(--text-secondary)', marginBottom: '1.5rem' }}>
          {depth}단계 아래의 리프 컴포넌트까지 테마 상태를 전달합니다. Drilling 모드에서는 테마를 전혀 사용하지 않는
          중간 컴포넌트들도 매번 props로 값을 받아 그대로 통과시켜야 합니다.
        </p>

        <div style={{ display: 'flex', gap: '0.75rem', marginBottom: '1.5rem', flexWrap: 'wrap', alignItems: 'center' }}>
          <button className={`btn ${mode === 'drilling' ? 'btn-primary' : 'btn-outline'}`} onClick={() => setMode('drilling')}>
            <Layers size={16} /> Prop Drilling 모드
          </button>
          <button className={`btn ${mode === 'context' ? 'btn-violet' : 'btn-outline'}`} onClick={() => setMode('context')}>
            <Sparkles size={16} /> Context API 모드
          </button>
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginLeft: 'auto' }}>
            <ArrowDownToLine size={16} style={{ color: 'var(--text-secondary)' }} />
            <label style={{ fontSize: '0.85rem', color: 'var(--text-secondary)' }}>중첩 깊이: {depth}</label>
            <input type="range" min={2} max={5} value={depth} onChange={(e) => setDepth(Number(e.target.value))} />
          </div>
        </div>

        {mode === 'drilling' ? (
          <DrillLevel depth={0} max={depth} theme={theme} onToggle={toggleTheme} />
        ) : (
          <ThemeContext.Provider value={ctxValue}>
            <ContextLevel depth={0} max={depth} />
          </ThemeContext.Provider>
        )}

        <div className="code-box" style={{ marginTop: '1.5rem' }}>
          <pre>
            {mode === 'drilling'
              ? `function Layout({ theme, onToggle }) {\n  // Layout은 theme을 쓰지 않지만\n  // 하위로 넘겨주기 위해 매번 매개변수로 받아야 함\n  return <Sidebar theme={theme} onToggle={onToggle} />\n}`
              : `function Layout() {\n  // Layout은 theme을 모른 채로 그냥 렌더링\n  return <Sidebar />\n}\n\nfunction Leaf() {\n  const { theme, toggleTheme } = useContext(ThemeContext)\n  // ...\n}`}
          </pre>
        </div>
      </div>
    </div>
  )
}
