import { useRef, useSyncExternalStore } from 'react'
import { Plus, Shuffle } from 'lucide-react'
import { RenderCounterBadge } from './RenderCounterBadge'

interface StoreState {
  count: number
  name: string
}

type Listener = () => void

interface Store<T> {
  getState: () => T
  setState: (partial: Partial<T>) => void
  subscribe: (listener: Listener) => () => void
}

function createStore<T extends object>(initial: T): Store<T> {
  let state = initial
  const listeners = new Set<Listener>()

  return {
    getState: () => state,
    setState: (partial) => {
      state = { ...state, ...partial }
      listeners.forEach((listener) => listener())
    },
    subscribe: (listener) => {
      listeners.add(listener)
      return () => {
        listeners.delete(listener)
      }
    },
  }
}

// Zustand, Jotai 등이 내부적으로 쓰는 것과 같은 모듈 스코프 store
const store = createStore<StoreState>({ count: 0, name: 'Zustand' })

function useStoreSelector<T extends object, Selected>(
  targetStore: Store<T>,
  selector: (state: T) => Selected,
): Selected {
  return useSyncExternalStore(targetStore.subscribe, () => selector(targetStore.getState()))
}

const SAMPLE_NAMES = ['Zustand', 'Jotai', 'Recoil', 'Valtio']

function CountView() {
  const count = useStoreSelector(store, (s) => s.count)
  return (
    <div className="drill-level">
      <div>
        count 셀렉터 구독: <strong>{count}</strong>
      </div>
      <RenderCounterBadge label="CountView" />
    </div>
  )
}

function NameView() {
  const name = useStoreSelector(store, (s) => s.name)
  return (
    <div className="drill-level">
      <div>
        name 셀렉터 구독: <strong>{name}</strong>
      </div>
      <RenderCounterBadge label="NameView" />
    </div>
  )
}

export function ExternalStoreDemo() {
  const nameIndexRef = useRef(0)

  return (
    <div className="demo-section">
      <div className="glass-card">
        <h2 style={{ marginBottom: '0.5rem' }}>3. useSyncExternalStore로 만든 미니 Store</h2>
        <p style={{ color: 'var(--text-secondary)', marginBottom: '1.5rem' }}>
          Zustand, Jotai 같은 라이브러리는 React Context 없이 모듈 스코프의 store를 두고{' '}
          <code>useSyncExternalStore</code>로 필요한 값만 선택(select)해서 구독합니다. count를 바꿔도 name만
          구독하는 컴포넌트는 리렌더링되지 않습니다.
        </p>

        <div style={{ display: 'flex', gap: '0.75rem', marginBottom: '1.5rem', flexWrap: 'wrap' }}>
          <button className="btn btn-primary" onClick={() => store.setState({ count: store.getState().count + 1 })}>
            <Plus size={14} /> count 증가
          </button>
          <button
            className="btn btn-violet"
            onClick={() => {
              nameIndexRef.current = (nameIndexRef.current + 1) % SAMPLE_NAMES.length
              store.setState({ name: SAMPLE_NAMES[nameIndexRef.current] })
            }}
          >
            <Shuffle size={14} /> name 변경
          </button>
        </div>

        <div style={{ display: 'grid', gap: '0.75rem' }}>
          <CountView />
          <NameView />
        </div>

        <div className="code-box" style={{ marginTop: '1.5rem' }}>
          <pre>{`function useStoreSelector(store, selector) {\n  return useSyncExternalStore(\n    store.subscribe,\n    () => selector(store.getState()),\n  )\n}\n\nconst count = useStoreSelector(store, (s) => s.count) // count 변경 시에만 리렌더링`}</pre>
        </div>
      </div>
    </div>
  )
}
