import { useRef } from 'react'
import { RefreshCw } from 'lucide-react'

export function RenderCounterBadge({ label = 'Renders' }: { label?: string }) {
  const renderCount = useRef(0)
  renderCount.current += 1

  return (
    <span className="render-badge">
      <RefreshCw size={12} />
      {label}: {renderCount.current}
    </span>
  )
}
