package io.yomni.programmers.level1.id12933

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.system.measureTimeMillis

@DisplayName("SolutionTest")
class SolutionTest {

    companion object {
        @JvmStatic
        fun argumentsProvider() = listOf(
            Arguments.of(12345, 54321),
            Arguments.of(118372, 873211),
            Arguments.of(1234567891234, 9876544332211), // 대규모 테스트 추가
            Arguments.of(1000000000, 1000000000)  // 대규모 테스트 추가
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(input: Long, expected: Long) {
        val solution = Solution()
        assertThat(solution.solution(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solutionShort(input: Long, expected: Long) {
        val solution = Solution()
        assertThat(solution.solutionShort(input)).isEqualTo(expected)
    }

    @Test
    fun performanceTest() {
        val largeNumber = 123456789123456789 // 큰 숫자

        val solution = Solution()

        // Stream 방법 성능 측정
        val timeWithStream = measureTimeMillis {
            solution.solution(largeNumber)
        }

        // 반복문 방법 성능 측정
        val timeWithLoop = measureTimeMillis {
            solution.solutionShort(largeNumber)
        }

        println("Time with Stream: $timeWithStream ms")
        println("Time with Loop: $timeWithLoop ms")

        assertThat(timeWithLoop).isLessThan(timeWithStream) // 반복문이 더 빠르다는 것을 테스트
    }
}
