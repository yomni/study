import { useEffect, useRef, useState } from 'react'
import { Server, MonitorSmartphone, RefreshCcw } from 'lucide-react'

interface UserData {
  id: number
  name: string
  bio: string
}

const MOCK_USERS: Record<number, UserData> = {
  1: { id: 1, name: '김개발', bio: 'Frontend Engineer' },
  2: { id: 2, name: '이코딩', bio: 'Backend Engineer' },
  3: { id: 3, name: '박풀스택', bio: 'Full-stack Engineer' },
  4: { id: 4, name: '최디자인', bio: 'Product Designer' },
  5: { id: 5, name: '정데브옵스', bio: 'DevOps Engineer' },
}

// id별 응답 지연을 의도적으로 다르게 두어 경쟁 상태(Race Condition)를 재현합니다.
const MOCK_DELAY: Record<number, number> = { 1: 1600, 2: 400, 3: 900, 4: 250, 5: 1200 }

function fetchUserMock(id: number): Promise<UserData> {
  return new Promise((resolve) => {
    setTimeout(() => resolve(MOCK_USERS[id]), MOCK_DELAY[id] ?? 600)
  })
}

function UserPanel({
  title,
  tone,
  userId,
  onSelect,
  user,
  loading,
  cachedIds,
}: {
  title: string
  tone: 'rose' | 'emerald'
  userId: number
  onSelect: (id: number) => void
  user: UserData | null
  loading: boolean
  cachedIds?: number[]
}) {
  const activeClass = tone === 'rose' ? 'btn-rose' : 'btn-emerald'
  const borderColor = tone === 'rose' ? 'rgba(244, 63, 94, 0.3)' : 'rgba(16, 185, 129, 0.3)'
  const titleColor = tone === 'rose' ? '#fb7185' : '#34d399'

  return (
    <div style={{ background: '#0f172a', border: `1px solid ${borderColor}`, borderRadius: '12px', padding: '1.25rem' }}>
      <strong style={{ color: titleColor, display: 'block', marginBottom: '0.75rem' }}>{title}</strong>
      <div style={{ display: 'flex', gap: '0.4rem', marginBottom: '1rem', flexWrap: 'wrap' }}>
        {Object.keys(MOCK_USERS).map((idStr) => {
          const id = Number(idStr)
          return (
            <button
              key={id}
              className={`btn ${id === userId ? activeClass : 'btn-outline'}`}
              style={{ padding: '0.4rem 0.7rem', fontSize: '0.8rem' }}
              onClick={() => onSelect(id)}
            >
              id {id} ({MOCK_DELAY[id]}ms)
            </button>
          )
        })}
      </div>
      <div className="code-box" style={{ minHeight: '70px' }}>
        {loading ? (
          <span style={{ color: 'var(--text-secondary)' }}>불러오는 중...</span>
        ) : user ? (
          <pre>{JSON.stringify(user, null, 2)}</pre>
        ) : (
          <span style={{ color: 'var(--text-secondary)' }}>-</span>
        )}
      </div>
      {cachedIds && (
        <p style={{ fontSize: '0.75rem', color: 'var(--text-secondary)', marginTop: '0.5rem' }}>
          캐시된 id: {cachedIds.length ? cachedIds.join(', ') : '없음'}
        </p>
      )}
    </div>
  )
}

function NaiveFetcher() {
  const [userId, setUserId] = useState(1)
  const [user, setUser] = useState<UserData | null>(null)
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    setLoading(true)
    // ❌ cleanup이 없어 응답 순서가 뒤바뀌면 늦게 도착한 응답이 최신 상태를 덮어씁니다.
    fetchUserMock(userId).then((data) => {
      setUser(data)
      setLoading(false)
    })
  }, [userId])

  return (
    <UserPanel title="❌ Naive useEffect (경쟁 상태 취약)" tone="rose" userId={userId} onSelect={setUserId} user={user} loading={loading} />
  )
}

function FixedFetcher() {
  const [userId, setUserId] = useState(1)
  const [user, setUser] = useState<UserData | null>(null)
  const [loading, setLoading] = useState(false)
  const cacheRef = useRef(new Map<number, UserData>())
  const latestRequestRef = useRef(0)

  useEffect(() => {
    const requestId = ++latestRequestRef.current
    const cached = cacheRef.current.get(userId)

    if (cached) {
      setUser(cached)
      setLoading(false)
      return
    }

    setLoading(true)
    fetchUserMock(userId).then((data) => {
      // ✅ 응답이 도착한 시점에 더 최신 요청이 없었는지 확인 후에만 반영
      if (latestRequestRef.current !== requestId) return
      cacheRef.current.set(userId, data)
      setUser(data)
      setLoading(false)
    })
  }, [userId])

  return (
    <UserPanel
      title="✅ 캐시 + 최신 요청 가드 (Server State 패턴)"
      tone="emerald"
      userId={userId}
      onSelect={setUserId}
      user={user}
      loading={loading}
      cachedIds={[...cacheRef.current.keys()]}
    />
  )
}

function ClientStateExample() {
  const [collapsed, setCollapsed] = useState(false)

  return (
    <div style={{ background: '#0f172a', border: '1px solid var(--border-color)', borderRadius: '12px', padding: '1.25rem' }}>
      <strong style={{ display: 'block', marginBottom: '0.75rem' }}>💻 Client State 예시: 사이드바 접힘 상태</strong>
      <p style={{ fontSize: '0.85rem', color: 'var(--text-secondary)', marginBottom: '0.75rem' }}>
        서버와 무관하게 오직 이 화면만을 위한 상태입니다. 네트워크 요청, 캐시, 만료(Stale) 같은 개념이 필요 없습니다.
      </p>
      <button className="btn btn-outline" onClick={() => setCollapsed((c) => !c)}>
        <RefreshCcw size={14} /> 사이드바 {collapsed ? '펼치기' : '접기'}
      </button>
      <div style={{ marginTop: '0.75rem', fontSize: '0.85rem' }}>
        현재 상태: <strong>{collapsed ? '접힘' : '펼침'}</strong>
      </div>
    </div>
  )
}

export function ClientServerStateDemo() {
  return (
    <div className="demo-section">
      <div className="glass-card">
        <h2 style={{ marginBottom: '0.5rem' }}>4. Client State vs Server State</h2>
        <p style={{ color: 'var(--text-secondary)', marginBottom: '1.5rem' }}>
          id 1(응답 1600ms)을 클릭한 뒤 바로 id 2(응답 400ms)를 클릭해보세요. 왼쪽(Naive) 패널에서는 늦게 도착한 id
          1의 응답이 화면을 덮어써 잘못된 유저가 표시되는 경쟁 상태(Race Condition) 버그가 발생합니다.
        </p>

        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: '1.5rem', marginBottom: '1.5rem' }}>
          <NaiveFetcher />
          <FixedFetcher />
        </div>

        <ClientStateExample />

        <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginTop: '1.5rem', fontSize: '0.85rem', color: 'var(--text-secondary)', flexWrap: 'wrap' }}>
          <Server size={16} /> Server State는 캐싱 · 최신 요청 검증 · 로딩/에러 상태 관리가 필요합니다.
          <MonitorSmartphone size={16} /> 이런 반복 작업을 대신 해주는 라이브러리가 React Query, SWR입니다.
        </div>
      </div>
    </div>
  )
}
