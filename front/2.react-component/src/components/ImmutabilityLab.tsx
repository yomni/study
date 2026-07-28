import { useState } from 'react'
import { RenderCounterBadge } from './RenderCounterBadge'
import { AlertCircle, CheckCircle, AlertTriangle, RefreshCw } from 'lucide-react'

interface UserProfile {
  name: string
  skills: string[]
  level: number
}

export function ImmutabilityLab() {
  const [profile, setProfile] = useState<UserProfile>({
    name: '김개발',
    skills: ['React', 'TypeScript'],
    level: 1,
  })

  const [lastActionStatus, setLastActionStatus] = useState<{
    msg: string
    success: boolean
  } | null>(null)

  // ❌ 1. Direct Mutation (Incorrect)
  const handleMutateDirectly = () => {
    profile.level += 1
    profile.skills.push('Direct Mutated Skill')
    // setUser state with same reference
    setProfile(profile)

    setLastActionStatus({
      msg: `❌ 객체 직접 수정 (profile.level = ${profile.level}): 참조 주소가 동일하므로 React가 변경을 감지하지 못해 UI 재렌더링이 발생하지 않았습니다!`,
      success: false,
    })
  }

  // ✅ 2. Immutable Update (Correct)
  const handleImmutableUpdate = () => {
    const newSkills = [...profile.skills, `New Skill #${profile.skills.length + 1}`]
    const updatedProfile: UserProfile = {
      ...profile,
      level: profile.level + 1,
      skills: newSkills,
    }
    setProfile(updatedProfile)

    setLastActionStatus({
      msg: `✅ 불변성 유지 업데이트 완료: 새로운 객체 참조({ ...profile })를 생성하여 React가 렌더링을 정상 트리거했습니다.`,
      success: true,
    })
  }

  return (
    <div className="demo-section">
      <div className="glass-card">
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start', marginBottom: '1rem' }}>
          <div>
            <h2 style={{ marginBottom: '0.5rem' }}>2. State 불변성(Immutability) 실험실</h2>
            <p style={{ color: 'var(--text-secondary)' }}>
              React 상태 업데이트 시 객체를 직접 수정하는 방식(Direct Mutation)과 불변성을 유지하는 방식(Spread / New Object)의 렌더링 결과 차이를 실험합니다.
            </p>
          </div>
          <RenderCounterBadge label="Parent Component Renders" />
        </div>

        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(320px, 1fr))', gap: '1.5rem', margin: '1.5rem 0' }}>
          {/* Left Action Box */}
          <div style={{ background: '#0f172a', padding: '1.25rem', borderRadius: '12px', border: '1px solid var(--border-color)' }}>
            <h3 style={{ fontSize: '1rem', color: '#38bdf8', marginBottom: '1rem' }}>실험 버튼 조작</h3>
            
            <div style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
              <button className="btn btn-rose" onClick={handleMutateDirectly}>
                <AlertTriangle size={16} /> ❌ 직접 수정 (Direct Mutation)
              </button>
              <button className="btn btn-emerald" onClick={handleImmutableUpdate}>
                <CheckCircle size={16} /> ✅ 불변성 준수 업데이트 (Immutable)
              </button>
              <button
                className="btn btn-outline"
                onClick={() =>
                  setProfile({ name: '김개발', skills: ['React', 'TypeScript'], level: 1 })
                }
              >
                <RefreshCw size={16} /> 프로필 초기화
              </button>
            </div>
          </div>

          {/* Right State View Box */}
          <div style={{ background: '#0f172a', padding: '1.25rem', borderRadius: '12px', border: '1px solid var(--border-color)' }}>
            <h3 style={{ fontSize: '1rem', color: '#818cf8', marginBottom: '0.75rem' }}>
              현재 Rendered State View
            </h3>
            <div className="code-box" style={{ marginBottom: '0.75rem' }}>
              <pre>{JSON.stringify(profile, null, 2)}</pre>
            </div>
            <div style={{ fontSize: '0.85rem', color: '#94a3b8' }}>
              스킬 개수: <strong style={{ color: '#38bdf8' }}>{profile.skills.length}개</strong>
            </div>
          </div>
        </div>

        {/* Action Status Output Banner */}
        {lastActionStatus && (
          <div
            style={{
              padding: '1rem',
              borderRadius: '10px',
              display: 'flex',
              alignItems: 'center',
              gap: '0.75rem',
              background: lastActionStatus.success ? 'rgba(16, 185, 129, 0.15)' : 'rgba(244, 63, 94, 0.15)',
              border: `1px solid ${lastActionStatus.success ? 'rgba(16, 185, 129, 0.3)' : 'rgba(244, 63, 94, 0.3)'}`,
              color: lastActionStatus.success ? '#34d399' : '#fb7185',
              fontSize: '0.9rem',
            }}
          >
            {lastActionStatus.success ? <CheckCircle size={20} /> : <AlertCircle size={20} />}
            <div>{lastActionStatus.msg}</div>
          </div>
        )}
      </div>
    </div>
  )
}
