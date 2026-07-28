import React, { createContext, useContext, useState } from 'react'
import { RenderCounterBadge } from './RenderCounterBadge'
import { ChevronDown, Layers, Code2 } from 'lucide-react'

// 1. Context 생성
interface AccordionContextType {
  openId: string | null
  toggle: (id: string) => void
}

const AccordionContext = createContext<AccordionContextType | undefined>(undefined)

function useAccordionContext() {
  const context = useContext(AccordionContext)
  if (!context) {
    throw new Error('Accordion 서브 컴포넌트는 반드시 Accordion 내부에 위치해야 합니다.')
  }
  return context
}

// 2. Parent Accordion Component
interface AccordionProps {
  children: React.ReactNode
  defaultOpenId?: string | null
}

export function Accordion({ children, defaultOpenId = null }: AccordionProps) {
  const [openId, setOpenId] = useState<string | null>(defaultOpenId)

  const toggle = (id: string) => {
    setOpenId((prev) => (prev === id ? null : id))
  }

  return (
    <AccordionContext.Provider value={{ openId, toggle }}>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>{children}</div>
    </AccordionContext.Provider>
  )
}

// 3. Sub Components
interface ItemProps {
  id: string
  children: React.ReactNode
}

const AccordionItemContext = createContext<string | undefined>(undefined)

function AccordionItem({ id, children }: ItemProps) {
  return (
    <AccordionItemContext.Provider value={id}>
      <div
        style={{
          background: 'rgba(15, 23, 42, 0.7)',
          border: '1px solid var(--border-color)',
          borderRadius: '12px',
          overflow: 'hidden',
        }}
      >
        {children}
      </div>
    </AccordionItemContext.Provider>
  )
}

function AccordionHeader({ children }: { children: React.ReactNode }) {
  const { openId, toggle } = useAccordionContext()
  const itemId = useContext(AccordionItemContext)
  if (!itemId) throw new Error('AccordionHeader must be used inside AccordionItem')

  const isOpen = openId === itemId

  return (
    <button
      onClick={() => toggle(itemId)}
      style={{
        width: '100%',
        padding: '1rem 1.25rem',
        background: 'transparent',
        border: 'none',
        color: 'var(--text-primary)',
        fontWeight: 600,
        fontSize: '0.95rem',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        cursor: 'pointer',
        textAlign: 'left',
      }}
    >
      <span>{children}</span>
      <ChevronDown
        size={18}
        style={{
          transform: isOpen ? 'rotate(180deg)' : 'rotate(0deg)',
          transition: 'transform 0.2s ease',
          color: isOpen ? 'var(--accent-cyan)' : 'var(--text-secondary)',
        }}
      />
    </button>
  )
}

function AccordionContent({ children }: { children: React.ReactNode }) {
  const { openId } = useAccordionContext()
  const itemId = useContext(AccordionItemContext)
  if (!itemId) throw new Error('AccordionContent must be used inside AccordionItem')

  const isOpen = openId === itemId

  if (!isOpen) return null

  return (
    <div
      style={{
        padding: '1rem 1.25rem',
        borderTop: '1px solid rgba(255, 255, 255, 0.05)',
        background: 'rgba(30, 41, 59, 0.4)',
        color: 'var(--text-secondary)',
        fontSize: '0.9rem',
        lineHeight: 1.6,
      }}
    >
      {children}
    </div>
  )
}

// Sub component attach
Accordion.Item = AccordionItem
Accordion.Header = AccordionHeader
Accordion.Content = AccordionContent

// ==========================================
// Demo Showcase Component
// ==========================================
export function CompoundAccordionDemo() {
  return (
    <div className="demo-section">
      <div className="glass-card">
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
          <div>
            <h2 style={{ marginBottom: '0.5rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
              <Layers size={22} color="var(--accent-cyan)" /> 3. Compound Components 패턴 실습
            </h2>
            <p style={{ color: 'var(--text-secondary)' }}>
              부모-자식 간 Context를 통해 상태를 암묵적으로 공유하여 자유로운 JSX 레이아웃을 작성할 수 있는 패턴입니다.
            </p>
          </div>
          <RenderCounterBadge />
        </div>

        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '1.5rem', margin: '1.5rem 0' }}>
          {/* Live Interactive Accordion */}
          <div>
            <h4 style={{ marginBottom: '1rem', color: '#38bdf8' }}>실행 중인 Accordion UI</h4>
            <Accordion defaultOpenId="acc-1">
              <Accordion.Item id="acc-1">
                <Accordion.Header>🧩 Compound Component의 핵심 장점은 무엇인가요?</Accordion.Header>
                <Accordion.Content>
                  부모 컴포넌트가 상태를 소유하고 자식 서브 컴포넌트가 Context로 구독하므로, 사용자가 JSX 상에서 순서나 구조를 자유롭게 구성할 수 있습니다 (Prop Drilling 완벽 제거!).
                </Accordion.Content>
              </Accordion.Item>

              <Accordion.Item id="acc-2">
                <Accordion.Header>⚡ Props vs State vs Context 차이점</Accordion.Header>
                <Accordion.Content>
                  <strong>Props</strong>: 부모-&gt;자식 단방향 전달 불변 데이터.<br />
                  <strong>State</strong>: 컴포넌트 내부 가변 데이터.<br />
                  <strong>Context</strong>: 컴포넌트 트리 전체에 상태를 방송(Broadcast)하는 통로.
                </Accordion.Content>
              </Accordion.Item>

              <Accordion.Item id="acc-3">
                <Accordion.Header>🛠️ 실무에서 언제 사용되나요?</Accordion.Header>
                <Accordion.Content>
                  <code>&lt;Select&gt;&lt;Select.Option /&gt;&lt;/Select&gt;</code>, <code>&lt;Tabs&gt;&lt;Tab.List /&gt;&lt;/Tabs&gt;</code>, <code>&lt;Modal&gt;&lt;Modal.Header /&gt;&lt;/Modal&gt;</code> 등의 UI 라이브러리 컴포넌트 개발 시 필수 패턴입니다.
                </Accordion.Content>
              </Accordion.Item>
            </Accordion>
          </div>

          {/* JSX Usage Code Preview */}
          <div>
            <h4 style={{ marginBottom: '1rem', color: '#818cf8', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
              <Code2 size={16} /> 작성된 JSX 코드 표현
            </h4>
            <div className="code-box" style={{ fontSize: '0.8rem' }}>
              <pre>{`<Accordion defaultOpenId="acc-1">
  <Accordion.Item id="acc-1">
    <Accordion.Header>
      제목 1
    </Accordion.Header>
    <Accordion.Content>
      내용 1
    </Accordion.Content>
  </Accordion.Item>
  <Accordion.Item id="acc-2">
    <Accordion.Header>
      제목 2
    </Accordion.Header>
    <Accordion.Content>
      내용 2
    </Accordion.Content>
  </Accordion.Item>
</Accordion>`}</pre>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
